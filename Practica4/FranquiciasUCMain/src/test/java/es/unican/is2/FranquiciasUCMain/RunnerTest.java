package es.unican.is2.FranquiciasUCMain;

import es.unican.is2.FranquiciasUCBusiness.*;
import es.unican.is2.FranquiciasUCDAO.*;
import es.unican.is2.FranquiciasUCGUI.*;


import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

public class RunnerTest {

    TiendasDAO tiendasDAO = new TiendasDAO();
    EmpleadosDAO empleadosDAO = new EmpleadosDAO();

    GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
    GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO,empleadosDAO);

    private FrameFixture demo;
    private VistaGerente vista;


    @BeforeEach
    void setUp(){
        vista = new VistaGerente(gTiendas, gEmpleados);
        demo = new FrameFixture(vista);
        vista.setVisible(true);
    }

    @Test
    void testConsultaTienda(){

        // tienda con empleados
        demo.textBox("txtNombreTienda").setText(null);
        demo.textBox("txtNombreTienda").enterText("Tienda A");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda A");
        demo.list("listNombreEmpleados").requireItemCount(3);
        demo.textBox("txtTotalSueldos").requireText("4362.5");

        // tienda con empleado
        demo.textBox("txtNombreTienda").setText(null);
        demo.textBox("txtNombreTienda").enterText("Tienda B");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda B");
        demo.list("listNombreEmpleados").requireItemCount(1);
        demo.textBox("txtTotalSueldos").requireText("2100.0");

        // tienda sin empleados
        demo.textBox("txtNombreTienda").setText(null);
        demo.textBox("txtNombreTienda").enterText("Tienda C");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda C");
        demo.list("listNombreEmpleados").requireItemCount(0);
        demo.textBox("txtTotalSueldos").requireText("0.0");

        // tienda no existe
        demo.textBox("txtNombreTienda").setText(null);
        demo.textBox("txtNombreTienda").enterText("Tienda D");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
    }

}

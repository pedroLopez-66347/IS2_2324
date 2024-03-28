package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TiendaITest {

    private Tienda tienda;

    //Constructor
    @Test
    void testTienda(){

        //==============================================================
        //||                 Casos de prueba validos (1)              ||
        //==============================================================
        tienda = new Tienda("Tienda G", "Calle G");
        assertEquals("Tienda G", tienda.getNombre());
        assertEquals("Calle G", tienda.getDireccion());

        //==============================================================
        //||            Casos de prueba NO validos (4)                ||
        //==============================================================
        assertThrows(DatoNoValidoException.class, () -> new Tienda(null, "Calle G"));
        assertThrows(DatoNoValidoException.class, () -> new Tienda("", "Calle G"));
        assertThrows(DatoNoValidoException.class, () -> new Tienda("Tienda G", null));
        assertThrows(DatoNoValidoException.class, () -> new Tienda("Tienda G", ""));
    }

    @Test
    void testGastoMensualSueldos(){
        Empleado empleado1 = new Empleado("12345678B", "Pedro", Categoria.ENCARGADO, LocalDate.now());
        Empleado empleado2 = new Empleado("12345678B", "Pedro", Categoria.VENDEDOR, LocalDate.now());
        Empleado empleado3 = new Empleado("12345678B", "Pedro", Categoria.AUXILIAR, LocalDate.now());

        tienda = new Tienda("Tienda F", "Calle F");
        List<Empleado> empleados = tienda.getEmpleados();

        //==============================================================
        //||                    Casos de prueba                       ||
        //==============================================================
        assertEquals(0.0, tienda.gastoMensualSueldos());

        empleados.add(empleado1);
        assertEquals(2000, tienda.gastoMensualSueldos());

        empleados.add(empleado2);
        empleados.add(empleado3);
        assertEquals(4500, tienda.gastoMensualSueldos());

    }
}

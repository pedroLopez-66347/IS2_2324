package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmpleadoTest {

    private Empleado empleado;

    //Constructor
    @Test
    void testEmpleado(){

        //==============================================================
        //||                 Casos de prueba validos (3)              ||
        //==============================================================
        empleado = new Empleado("12345678F", "Pedro", Categoria.ENCARGADO, LocalDate.now());
        assertEquals("12345678F", empleado.getDNI());
        assertEquals("Pedro", empleado.getNombre());
        assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
        assertEquals(LocalDate.now(), empleado.getFechaContratacion());
        assertFalse(empleado.getBaja());

        empleado = new Empleado("87654321L", "Jesus", Categoria.AUXILIAR, LocalDate.now().minusDays(1));
        assertEquals("87654321L", empleado.getDNI());
        assertEquals("Jesus", empleado.getNombre());
        assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
        assertEquals(LocalDate.now().minusDays(1), empleado.getFechaContratacion());
        assertFalse(empleado.getBaja());

        empleado = new Empleado("11223344P", "Maria", Categoria.VENDEDOR, LocalDate.now().minusDays(30));
        assertEquals("11223344P", empleado.getDNI());
        assertEquals("Maria", empleado.getNombre());
        assertEquals(Categoria.VENDEDOR, empleado.getCategoria());
        assertEquals(LocalDate.now().minusDays(30), empleado.getFechaContratacion());
        assertFalse(empleado.getBaja());


        //==============================================================
        //||            Casos de prueba NO validos (8)                ||
        //==============================================================
        //DNI = null
        assertThrows(DatoNoValidoException.class, () -> new Empleado(null, "Sofia", Categoria.VENDEDOR, LocalDate.now()));

        //DNI = ""
        assertThrows(DatoNoValidoException.class, () -> new Empleado("", "Luis", Categoria.VENDEDOR, LocalDate.now()));

        //nombre = null
        assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", null, Categoria.VENDEDOR, LocalDate.now()));

        //nombre = ""
        assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", "", Categoria.VENDEDOR, LocalDate.now()));

        //Categoria = null
        assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", "Manuel", null, LocalDate.now()));

        //fechaActual < fechaContratacion
        assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", "Manuel", Categoria.VENDEDOR, LocalDate.now().plusDays(1)));

        //fecha = null
        assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", "Manuel", Categoria.VENDEDOR, null));

        /*
          Caso de prueba NO valido cuando se asigna una categoría que no pertenece al enum => != {ENCARGADO, VENDEDOR, AUXILIAR}
          Este caso no puede implementarse en Java

          assertThrows(DatoNoValidoException.class, () -> new Empleado("12345678A", "Manuel", Categoria.MESERO, LocalDate.now()));
        */
    }

    //Método sueldoBruto()
    @Test
    void testSueldoBruto(){

        //==============================================================
        //||               Casos de prueba validos (11)               ||
        //==============================================================
        empleado = new Empleado("12345678A", "Pedro", Categoria.ENCARGADO, LocalDate.now());

        empleado.setCategoria(Categoria.ENCARGADO);
        empleado.setBaja(true);
        empleado.setFechaContratacion(LocalDate.now());
        assertEquals(1500, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.ENCARGADO);
        empleado.setBaja(true);
        empleado.setFechaContratacion(LocalDate.now().minusYears(11));
        assertEquals(1575, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.ENCARGADO);
        empleado.setBaja(false);
        empleado.setFechaContratacion(LocalDate.now().minusYears(6));
        assertEquals(2050, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.ENCARGADO);
        empleado.darDeAlta();
        empleado.setFechaContratacion(LocalDate.now().minusYears(21));
        assertEquals(2200, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.VENDEDOR);
        empleado.setBaja(true);
        empleado.setFechaContratacion(LocalDate.now().minusYears(7));
        assertEquals(1162.5, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.VENDEDOR);
        empleado.setBaja(false);
        empleado.setFechaContratacion(LocalDate.now().minusYears(1));
        assertEquals(1500, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.VENDEDOR);
        empleado.setBaja(false);
        empleado.setFechaContratacion(LocalDate.now().minusYears(15));
        assertEquals(1600, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.VENDEDOR);
        empleado.darDeBaja();
        empleado.setFechaContratacion(LocalDate.now().minusYears(30));
        assertEquals(1275, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.AUXILIAR);
        empleado.setBaja(true);
        empleado.setFechaContratacion(LocalDate.now().minusYears(5));
        assertEquals(750, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.AUXILIAR);
        empleado.setBaja(false);
        empleado.setFechaContratacion(LocalDate.now().minusYears(10));
        assertEquals(1050, empleado.sueldoBruto());

        empleado.setCategoria(Categoria.AUXILIAR);
        empleado.setBaja(true);
        empleado.setFechaContratacion(LocalDate.now().minusYears(20));
        assertEquals(825, empleado.sueldoBruto());


        //==============================================================
        //||               Casos de prueba NO validos                 ||
        //==============================================================

        //Los casos de prueba no validos ya fueron validados en la creación del objeto
        //a través del método constructor. En caso de ocurrir algun error se arrojaría
        //una excepcion la cual ya fue verificada.

    }

}

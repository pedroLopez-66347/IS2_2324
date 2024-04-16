package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestListaOrdenadaAcotada {

    ListaOrdenadaAcotada<Integer> lista1;
    ListaOrdenadaAcotada<Integer> lista2;
    ListaOrdenadaAcotada<Integer> lista3;

    @BeforeEach
    public void setUp() throws Exception {
        lista1 = new ListaOrdenadaAcotada<>(3);
        lista2 = new ListaOrdenadaAcotada<>(3);
        lista3 = new ListaOrdenadaAcotada<>(3);

        //Lista1 = [4]
        lista1.add(4);

        //Lista2 = [1,7,2]
        lista2.add(1);
        lista2.add(7);
        lista2.add(2);

        //Lista3 = []
    }

    @Test
    void testGet(){
        assertDoesNotThrow(() ->{
                assertEquals(lista1.get(0), 4); // (1) = Lista con un elemento
                assertEquals(lista2.get(1), 2); // (2) = Lista con elementos
        });

        assertThrows(IndexOutOfBoundsException.class, () -> lista3.get(0));  // (15) = Lista vacia
        assertThrows(IndexOutOfBoundsException.class, () -> lista2.get(3));  // (16) = Fuera de rango
        assertThrows(IndexOutOfBoundsException.class, () -> lista1.get(-1)); // (17) = Indice negativo
    }

    @Test
    void testAddValidos(){
        assertDoesNotThrow(() -> {
            // (3) = Lista vacia
            lista3.add(6);
            assertEquals(lista3.get(0), 6);

            // (4) = Lista con un elemento
            lista1.add(1);
            assertEquals(lista1.get(0), 4);
            assertEquals(lista1.get(1), 1);

            // (5) = Lista con elementos
            lista2.add(9);
            assertEquals(lista2.get(0), 1); // Falla aqui
            assertEquals(lista2.get(1), 7);
            assertEquals(lista2.get(2), 2);
            assertEquals(lista2.get(3), 9);
        });
    }

    @Test
    void testRemove(){
        assertDoesNotThrow(() -> {
            // (6) = Lista con un elemento
            assertEquals(4, lista1.remove(0));
            assertEquals(0, lista1.size());

            // (7) = Lista con elementos
            assertEquals(7, lista2.remove(1)); //Falla aqui
            assertEquals(2, lista2.size());
            assertEquals(lista2.get(0), 1);
            assertEquals(lista2.get(1), 2);
        });

        // Casos no válidos
        assertThrows (IndexOutOfBoundsException.class, () -> lista3.remove(0));  // (20) = Lista vacia
        assertThrows (IndexOutOfBoundsException.class, () -> lista2.remove(3));  // (21) = Fuera de rango
        assertThrows (IndexOutOfBoundsException.class, () -> lista1.remove(-1)); // (22) = Indice negativo
    }

    @Test
    void testSize(){
        assertDoesNotThrow(() -> {
            assertEquals(0, lista3.size()); // (8) = Lista vacia
            assertEquals(1, lista1.size()); // (9) = Lista con un elemento
            assertEquals(3, lista2.size()); // (10) = Lista con elementos
        });
    }

    @Test
    void testClear(){
        assertDoesNotThrow(() -> {
            // (11) = Lista vacia
            lista3.clear();
            assertEquals(0, lista3.size());

            // (12) = Lista con un elemento
            lista1.clear();
            assertEquals(0, lista1.size()); // Falla aqui

            // (13) = Lista con elementos
            lista2.clear();
            assertEquals(0, lista2.size());
        });
    }

    @Test
    void testAddNoValidos(){

        //Casos no validos
        assertThrows(IllegalStateException.class, () -> lista2.add(8));   // (18) = Exceda tamaño definido
        assertThrows(NullPointerException.class, () -> lista1.add(null)); // (19) = Elemento null
    }

    @Test
    void testConstructor(){

        //Casos válidos
        // (14) = Tamanio positivo
        ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<Integer>(3);
        assertDoesNotThrow(() -> {
            lista.add(1);
            lista.add(2);
            lista.add(3);
        });
        assertThrows(IllegalStateException.class, () -> lista.add(4));

        //Casos no válidos
        // (23) = Tamanio negativo
        assertThrows(NegativeArraySizeException.class, () -> new ListaOrdenadaAcotada<Integer>(-1)); // (23) = Tamaño megativo
    }
}

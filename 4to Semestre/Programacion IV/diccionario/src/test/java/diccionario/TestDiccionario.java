package diccionario;


import org.junit.Assert;
import org.junit.Test;

public class TestDiccionario {

    @Test
    public void testInsertarBasico(){
        Diccionario<String, Integer> diccionario = new DiccionarioTablaHash<>();

        diccionario.insertar("uno", 1);
        diccionario.insertar("dos", 2);
        diccionario.insertar("tres", 3);
        diccionario.insertar("cuatro", 4);
        diccionario.insertar("cinco", 5);

        diccionario.insertar("seis", 6);
        diccionario.insertar("siete", 7);
        diccionario.insertar("ocho", 8);
        diccionario.insertar("nueve", 9);
        diccionario.insertar("diez", 10);

        diccionario.insertar("once", 11);
        diccionario.insertar("doce", 12);
        diccionario.insertar("trece", 13);
        diccionario.insertar("catorce", 14);
        diccionario.insertar("quince", 15);

        Assert.assertEquals(diccionario.getCantidadElementos(),15);

        Assert.assertTrue(diccionario.contieneLlave("uno"));
        Assert.assertTrue(diccionario.contieneLlave("dos"));
        Assert.assertTrue(diccionario.contieneLlave("tres"));
        Assert.assertTrue(diccionario.contieneLlave("cuatro"));
        Assert.assertTrue(diccionario.contieneLlave("cinco"));

        int actual = diccionario.obtener("uno");
        Assert.assertEquals(actual, 1);
        actual = diccionario.obtener("dos");
        Assert.assertEquals(actual, 2);
        actual = diccionario.obtener("tres");
        Assert.assertEquals(actual, 3);
        actual = diccionario.obtener("cuatro");
        Assert.assertEquals(actual, 4);
        actual = diccionario.obtener("cinco");
        Assert.assertEquals(actual, 5);

    }

    @Test
    public void testInsertarActualizar(){
        Diccionario<String, Integer> diccionario = new DiccionarioTablaHash<>();

        diccionario.insertar("uno", 1);
        diccionario.insertar("dos", 22);
        diccionario.insertar("tres", 33);
        diccionario.insertar("cuatro", 4);
        diccionario.insertar("cinco", 5);


        diccionario.insertar("dos", 2);
        diccionario.insertar("tres", 3);

        Assert.assertEquals(diccionario.getCantidadElementos(),5);

        int actual = diccionario.obtener("uno");
        Assert.assertEquals(actual, 1);
        actual = diccionario.obtener("dos");
        Assert.assertEquals(actual, 2);
        actual = diccionario.obtener("tres");
        Assert.assertEquals(actual, 3);
        actual = diccionario.obtener("cuatro");
        Assert.assertEquals(actual, 4);
        actual = diccionario.obtener("cinco");
        Assert.assertEquals(actual, 5);
    }

    @Test
    public void testInsertarActualizarEliminar(){
        Diccionario<String, Integer> diccionario = new DiccionarioTablaHash<>();

        diccionario.insertar("uno", 1);
        diccionario.insertar("dos", 22);
        diccionario.insertar("tres", 33);
        diccionario.insertar("cuatro", 4);
        diccionario.insertar("cinco", 5);


        diccionario.insertar("dos", 2);
        diccionario.insertar("tres", 3);

        int expectedTres = 3;
        int actualTres = diccionario.eliminar("tres");

        int expectedCuatro = 4;
        int actualCuatro = diccionario.eliminar("cuatro");

        int expectedUno = 1;
        int actualUno = diccionario.eliminar("uno");

        int expectedCinco = 5;
        int actualCinco = diccionario.eliminar("cinco");


        Assert.assertEquals(diccionario.getCantidadElementos(),1);

        Assert.assertEquals(actualTres, expectedTres);
        Assert.assertEquals(actualCuatro, expectedCuatro);
        Assert.assertEquals(actualUno, expectedUno);
        Assert.assertEquals(actualCinco, expectedCinco);

    }
}

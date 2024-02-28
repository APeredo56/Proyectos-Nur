package diccionario;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDiccionario {

    @Test
    public void testInsertarBasico(){
        Diccionario<String, Integer> diccionario = new DiccionarioTablaHash<>();

        diccionario.insertar("uno", 1);
        diccionario.insertar("dos", 2);
        diccionario.insertar("tres", 3);
        diccionario.insertar("cuatro", 4);
        diccionario.insertar("cinco", 5);

        Assert.assertEquals(diccionario.getCantidadElementos(),5);

        Assert.assertTrue(diccionario.contieneLlave("uno"));
        Assert.assertTrue(diccionario.contieneLlave("dos"));
        Assert.assertTrue(diccionario.contieneLlave("tres"));
        Assert.assertTrue(diccionario.contieneLlave("cuatro"));
        Assert.assertTrue(diccionario.contieneLlave("cinco"));

        Assert.assertEquals(diccionario.obtener("uno"), 1);
        Assert.assertEquals(diccionario.obtener("dos"), 2);
        Assert.assertEquals(diccionario.obtener("tres"), 3);
        Assert.assertEquals(diccionario.obtener("cuatro"), 4);
        Assert.assertEquals(diccionario.obtener("cinco"), 5);

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

        Assert.assertEquals(diccionario.obtener("uno"), 1);
        Assert.assertEquals(diccionario.obtener("dos"), 2);
        Assert.assertEquals(diccionario.obtener("tres"), 3);
        Assert.assertEquals(diccionario.obtener("cuatro"), 4);
        Assert.assertEquals(diccionario.obtener("cinco"), 5);
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

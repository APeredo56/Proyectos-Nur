import abb.ArbolBinarioBusqueda;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class TestArbolRecorridoDesdeHasta {

    @Test
    public void test1(){
        Integer[] expected = {26, 38, 29, 32, 30};
        makeTest(expected, 26, 30);
    }

    @Test
    public void test2(){
        Integer[] expected = {12, 8, 10};
        makeTest(expected, 12, 10);
    }

    @Test
    public void test3(){
        Integer[] expected = {38, 29, 32, 30};
        makeTest(expected, 38, 30);
    }

    @Test
    public void test4(){
        Integer[] expected = {};
        makeTest(expected, 16, 8);
    }

    @Test
    public void test5(){
        Integer[] expected = {};
        makeTest(expected, 8, 88);
    }


    @Test
    public void test6(){
        Integer[] expected = { 26 };
        makeTest(expected, 26, 26);
    }

    @Test
    public void test7(){
        Integer[] expected = { 12};
        makeTest(expected, 12, 12);
    }


    @Test
    public void test8(){
        Integer[] expected = { 32 };
        makeTest(expected, 32, 32);
    }


    @Test
    public void test9(){
        Integer[] expected = { 45 };
        makeTest(expected, 45, 45);
    }

    @Test
    public void test10(){
        Integer[] expected = {26, 12, 18, 15};
        makeTest(expected, 26, 15);
    }

    private void makeTest(Integer[] expected, int desde, int hasta){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(26, 12, 38, 8, 18, 29 ,42, 10, 15 ,32, 30,35, 45);

        List<Integer> recorrido = arbol.getRecorridoDesdeHasta(desde, hasta);
        Integer[] actual = recorrido.toArray(new Integer[0]);

        assertArrayEquals(expected, actual);
    }
}

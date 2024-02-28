import abb.ArbolBinarioBusqueda;
import org.junit.Assert;
import org.junit.Test;

public class TestGetCantidadHojas {

    @Test
    public void test1(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(1);

        int expected = 1;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();

        int expected = 0;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void test3(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(15,10,23,20,30,18,25,40,35);

        int expected = 4;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test4(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(1,2,3,4,5,6,7,8);

        int expected = 1;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void test5(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(26, 12, 38, 8, 18, 29 ,42, 10, 15 ,32, 30,35, 45);

        int expected = 5;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test6(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(26, 12, 38);

        int expected = 2;
        int actual = arbol.getCantidadHojas();
        Assert.assertEquals(expected, actual);
    }
}

import abb.ArbolBinarioBusqueda;
import org.junit.Assert;
import org.junit.Test;

public class TestEsLleno {

    @Test
    public void test1(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(1);

        Assert.assertTrue(arbol.esLleno());
    }

    @Test
    public void test2(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(3, 2, 4);

        Assert.assertTrue(arbol.esLleno());
    }

    @Test
    public void test3(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(2, 1, 4, 3 ,5);

        Assert.assertTrue(arbol.esLleno());
    }

    @Test
    public void test4(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(4, 2, 1, 3 ,7,6);

        Assert.assertFalse(arbol.esLleno());
    }

    @Test
    public void test5(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();

        Assert.assertFalse(arbol.esLleno());
    }


    @Test
    public void test6(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(4, 2, 1, 3);

        Assert.assertFalse(arbol.esLleno());
    }

    @Test
    public void test7(){
        ArbolBinarioBusqueda<Integer> arbol = new ArbolBinarioBusqueda();
        arbol.insertar(4, 2, 1, 3, 8, 6, 9, 5, 7);

        Assert.assertTrue(arbol.esLleno());
    }
}

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class TestCambiador {

    @Test
    public void testCambiar36(){
        Cambiador cambiador = new Cambiador(20,10,5,1);
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(20, 1);
        expected.put(10,1);
        expected.put(5,1);
        expected.put(1,1);

        HashMap<Integer, Integer> actual = cambiador.cambiar(36);
        Assert.assertTrue(expected.equals(actual));

    }
    @Test
    public void testCambiar37(){
        Cambiador cambiador = new Cambiador(20,10,5,1);
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(20, 1);
        expected.put(10,1);
        expected.put(5,1);
        expected.put(1,2);

        HashMap<Integer, Integer> actual = cambiador.cambiar(37);
        Assert.assertTrue(expected.equals(actual));

    }

    @Test
    public void testCambiar50(){
        Cambiador cambiador = new Cambiador(10,5,1);
        HashMap<Integer, Integer> expected = new HashMap<>();
        expected.put(10,5);

        HashMap<Integer, Integer> actual = cambiador.cambiar(50);
        Assert.assertTrue(expected.equals(actual));

    }
}

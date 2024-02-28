package radar;

import org.junit.Test;
import org.testng.Assert;

public class TestRadar {

    @Test
    public void testearRadar(){

        Radar radar = new Radar();

        radar.registrar(new Automovil("AAA111", 100));
        radar.registrar(new Automovil("AAA111", 120));
        radar.registrar(new Automovil("AAA111", 140));

        radar.registrar(new Automovil("BBB222", 80));
        radar.registrar(new Automovil("BBB222", 150));

        Automovil query = new Automovil("AAA111");
        EstadisticaRadar estadistica = radar.getEstadistica(query);
        Assert.assertEquals(estadistica.getVelocidadPromedio(), 120);
        Assert.assertEquals(estadistica.getVeces(),3);

        query = new Automovil("BBB222");
        estadistica = radar.getEstadistica(query);
        Assert.assertEquals(estadistica.getVelocidadPromedio(), 115);
        Assert.assertEquals(estadistica.getVeces(),2);
    }
}

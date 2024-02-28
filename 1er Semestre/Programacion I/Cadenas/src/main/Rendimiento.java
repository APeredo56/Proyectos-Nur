package main;

public class Rendimiento {

    public long rendimientoString() {
        long tiempo;
        String mensaje = "";
        long inicio = System.currentTimeMillis();
        for (int numero = 0; numero < 500000; numero++) {
            mensaje += "test";
        }
        long fin = System.currentTimeMillis();
        tiempo = (fin - inicio) / 1000;
        return tiempo;
    }

    public long rendimientoConcatenacion() {
        long tiempo;
        String mensaje = "";
        long inicio = System.currentTimeMillis();
        for (int numero = 0; numero < 500000; numero++) {
            mensaje = mensaje.concat("test");
        }
        long fin = System.currentTimeMillis();
        tiempo = (fin - inicio) / 1000;
        return tiempo;
    }

    public long rendimientoStringBuilder() {
        long tiempo;
        StringBuilder mensaje = new StringBuilder();
        long inicio = System.currentTimeMillis();
        for (int numero = 0; numero < 500000; numero++) {
            mensaje.append("test");
        }
        long fin = System.currentTimeMillis();
        tiempo = (fin - inicio);
        return tiempo;
    }

    public long rendimientoStringBuffer() {
        long tiempo;
        StringBuffer mensaje = new StringBuffer();
        long inicio = System.currentTimeMillis();
        for (int numero = 0; numero < 500000; numero++) {
            mensaje.append("test");
        }
        long fin = System.currentTimeMillis();
        tiempo = (fin - inicio);
        return tiempo;
    }
}

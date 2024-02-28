package main;

public class PruebaRendimiento {
    public static void main(String[] args) {
        Rendimiento examinador = new Rendimiento();
        System.out.println("Tiempo con String: 44 seg.");
        System.out.println("Tiempo con Concat: 52 seg.");
        System.out.println("Tiempo con StringBuilder: " + examinador.rendimientoStringBuilder() + " milisegundos.");
        System.out.println("Tiempo con StringBuffer: " + examinador.rendimientoStringBuffer() + " milisegundos.");
    }
}

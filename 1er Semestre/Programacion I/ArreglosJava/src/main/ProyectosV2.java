package main;

public class ProyectosV2 {
    // Bloque de Declaraciones
    private String[] nombres;
    private String[] proyectos;
    private double[] notas;
    private int posicionActual;

    // Bloque de Instrucciones
    // public NombreClase (T.D. parametro1, T.D. parametro2,....)
    public ProyectosV2(int cantidad) {
        this.nombres = new String[cantidad];
        this.proyectos = new String[cantidad];
        this.notas = new double[cantidad];
        this.posicionActual = 0;
    }

    // ámbito Tipo de Retorno identificado(T.D. parametro, T.D parametro){}
    public String guardarDatos(String nombre, String proyecto, double nota) {
        String mensaje = "Ya no se puede almacenar más información.";
        if (this.proyectos.length != posicionActual) {
            // Como se validó la posición guardamos los elementos
            this.nombres[posicionActual] = nombre;
            this.proyectos[posicionActual] = proyecto;
            this.notas[posicionActual] = nota;
            this.posicionActual += 1;
            mensaje = "Los datos se han guardado correctamente.";
        }
        return mensaje;
    }

    public void inicializarTextos() {
        for (int posicion = 0; posicion < this.nombres.length; posicion++) {
            this.nombres[posicion] = "Por Definir";
            this.proyectos[posicion] = "Por Definir";
        }
    }

    public void recorrerProyectos() {
        System.out.println("NRO. NOMBRE COMPLETO                  PROYECTO        NOTA");
        for (int posicion = 0; posicion < this.nombres.length; posicion++) {
            System.out.println((posicion + 1) + ".-  " + this.nombres[posicion] + "   " + this.proyectos[posicion] + "   " + this.notas[posicion]);
        }
    }
}

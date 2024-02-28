package main;

public class Proyectos {
    public static void main(String[] args) {
        // Bloque de Declaraciones
        String[] nombres;
        String[] proyectos;
        double[] notas;
        // Bloque de Instrucciones
        System.out.println("--- Registro de Proyectos ----");
        nombres = new String[34];
        proyectos = new String[34];
        notas = new double[34];
        // Guardar un poco de Información
        nombres[0] = "Fabio Andrés Vargas Artunduaga";
        nombres[10] = "Arianne Ortiz Cazón";
        nombres[20] = "Oscar David Mur Rodriguez";
        System.out.println("Listado del Grupo");
        // Ahora asignemos proyectos
        proyectos[0] = "Sudoku";
        proyectos[10] = "Batalla Naval";
        proyectos[20] = "Conecta4";
        // Asignemos Notas
        notas[0] = 100;
        notas[10] = 51;
        notas[20] = 75;
        // Plantilla de Impresión
        System.out.println("NRO. NOMBRE COMPLETO                  PROYECTO        NOTA");
        for (int posicion = 0; posicion < nombres.length; posicion++) {
            System.out.println((posicion + 1) + ".-  " + nombres[posicion] + "   " + proyectos[posicion] + "   " + notas[posicion]);
        }
    }
}

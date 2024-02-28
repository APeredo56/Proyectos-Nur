package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Bloque de Declaraciones
        boolean correrPrograma;
        int cantidad;
        String nombre;
        String proyecto;
        double nota;
        Scanner scanner;
        // Bloque de Instrucciones
        System.out.println("--- Registro de Notas ---");
        scanner = new Scanner(System.in);
        correrPrograma = true;
        do {
            try {
                System.out.println("Ingrese la cantidad de alumnos para el registro:");
                String auxiliar = scanner.nextLine();
                cantidad = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("Sólo se admiten números enteros mayores a 0.");
                cantidad = 0;
            }
        } while (cantidad <= 0);
        // Teniendo la Cantidad de registros, inicializo la clase ProyectosV2
        ProyectosV2 manager = new ProyectosV2(cantidad);
        manager.inicializarTextos();
        // Inicio el bucle del programa para mantenerlo corriendo
        while (correrPrograma) {
            /* --- Bloque de Menu --- */
            System.out.println("--- MENÚ DE OPCIONES ---");
            System.out.println("1 .- Ver lista de Estudiantes");
            System.out.println("2 .- Guardar datos de un Estudiante");
            System.out.println("3 .- Salir del programa.");
            int opcion = 0;
            do {
                try {
                    System.out.println("Ingrese el número de la opción que desea ejecutar:");
                    String auxiliar = scanner.nextLine();
                    opcion = Integer.parseInt(auxiliar);
                } catch (Exception error) {
                    System.out.println("Sólo se admiten números enteros mayores a 0.");
                    opcion = 0;
                }
            } while (opcion <= 0);
            /* --- Bloque de Menu --- */
            /* --- Bloque de Opciones --- */
            switch (opcion) {
                case 1:
                    manager.recorrerProyectos();
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del estudiante:");
                    nombre = scanner.nextLine();
                    System.out.println("Ingrese el proyecto asignado al estudiante:");
                    proyecto = scanner.nextLine();
                    do {
                        try {
                            System.out.println("Ingrese la nota del estudiante:");
                            String auxiliar = scanner.nextLine();
                            nota = Double.parseDouble(auxiliar);
                        } catch (Exception error) {
                            System.out.println("Sólo se admiten números enteros mayores a 0.");
                            nota = 0;
                        }
                    } while (nota <= 0);
                    // Una vez tengo los valores del estudiante, paso la información al manager
                    System.out.println(manager.guardarDatos(nombre, proyecto, nota));
                    break;
                case 3:
                    correrPrograma = false;
                    break;
                default:
                    System.out.println("Las opciones válidas son: 1, 2 y 3.");
                    break;
            }
            /* --- Bloque de Opciones --- */
        }

        /*ProyectosV2 manager = new ProyectosV2(5);
        manager.inicializarTextos();
        manager.recorrerProyectos();
        // Ahora almacenaremos información
        System.out.println(manager.guardarDatos("Juacito Pinto", "Tres en Raya", 40));
        System.out.println(manager.guardarDatos("Mengana Sotana", "Bingo", 65));
        System.out.println(manager.guardarDatos("Juacito Pinto 1", "Tres en Raya", 40));
        System.out.println(manager.guardarDatos("Mengana Sotana 1", "Bingo", 65));
        System.out.println(manager.guardarDatos("Juacito Juanes", "Tres en Raya", 40));
        System.out.println(manager.guardarDatos("Juacito Pinto 1", "Tres en Raya", 40));
        System.out.println(manager.guardarDatos("Mengana Sotana 1", "Bingo", 65));
        System.out.println(manager.guardarDatos("Juacito Juanes", "Tres en Raya", 40));
        manager.recorrerProyectos();*/
    }
}

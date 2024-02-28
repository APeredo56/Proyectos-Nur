package main;

import java.util.Scanner;

public class EjercicioCasi25 {
    public static void main(String[] args) {
        int cantidad;
        Scanner scanner;
        System.out.println("--- Serie Gráfica Triángulo ---");
        scanner = new Scanner(System.in);
        do {
            System.out.println("Ingrese un número para la base del triángulo:");
            try {
                String auxiliar = scanner.nextLine();
                cantidad = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa sólo admite números.");
                cantidad = 0;
            }
        } while (cantidad <= 0);
        for (int filas = 1; filas <= cantidad; filas++) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                // Antes de imprimir los números, imprimo espacios
                if (columnas == 1) {
                    System.out.print(" ".repeat(cantidad - filas));
                }
                // Ahora imprimo los números
                System.out.print(columnas + " ");
            }
            System.out.println("");
        }
    }
}

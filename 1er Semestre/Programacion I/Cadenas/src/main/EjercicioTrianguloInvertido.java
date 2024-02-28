package main;

import java.util.Scanner;

public class EjercicioTrianguloInvertido {
    // Si N -> 4
    // 1 2 3 4
    //  1 2 3
    //   1 2
    //    1
    public static void main(String[] args) {
        int cantidad;
        Scanner scanner;
        System.out.println("--- Serie Gráfica Triángulo Invertido ---");
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
        for (int filas = cantidad; filas >= 1; filas--) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                // Antes de imprimir números, imprimo espacios
                if (columnas == 1) {
                    System.out.print(" ".repeat(cantidad - filas));
                }
                System.out.print(columnas + " ");
            }
            System.out.println("");
        }
    }
}

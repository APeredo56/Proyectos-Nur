package main;

import java.util.Scanner;

public class TrianguloInvertido {
    public static void main(String[] args) {
        int limite;
        Scanner lector;
        System.out.println(">>> Serie grafica triangulo invertido <<<");
        lector = new Scanner(System.in);
        do {
            System.out.println("Ingrese la cantidad de filas que desea ver:");
            try {
                String auxiliar = lector.nextLine();
                limite = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo acepta numeros enteros positivos");
                limite = 0;
            }
        } while (limite <= 0);
        for (int filas = limite; filas >= 1; filas--) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                if (columnas == 1) {
                    System.out.print(" ".repeat(limite - filas));
                }
                System.out.print(columnas + " ");

            }
            System.out.println("");
        }
    }
}


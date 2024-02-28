package main;

import java.util.Scanner;

public class Ejercicio24Practico {
    public static void main(String[] args) {
        int limite;
        Scanner scanner;
        System.out.println("--- Serie Gráfica #1 ---");
        scanner = new Scanner(System.in);
        do {
            System.out.println("Ingrese la cantidad de filas que desea ver:");
            try {
                String auxiliar = scanner.nextLine();
                limite = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa sólo acepta números");
                limite = 0;
            }
        } while (limite <= 0);
        for (int filas = 1; filas <= limite; filas++) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                System.out.print(columnas + " ");
                //System.out.println(filas + " x " + columnas + " = " + (filas * columnas));
            }
            System.out.println("");
        }
    }
}

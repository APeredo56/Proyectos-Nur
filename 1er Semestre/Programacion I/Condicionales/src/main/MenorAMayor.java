package main;

import java.util.Scanner;

public class MenorAMayor {
    public static void main(String[] args) {
        // Solicitar al usuario el ingreso de 3 números enteros, el programa debe retornar los números ingresados
        // ordenados de menor a mayor.
        // 75,12,21 --> 12 - 21 - 75
        // 75,12,21 -- 75,21,12 -- 21,75,12 -- 21,12,75 -- 12,21,75 -- 12,75,21
        int primero;
        int segundo;
        int tercero;
        Scanner scanner;
        System.out.println("--- Ordenando de menor a mayor ---");
        scanner = new Scanner(System.in);
        System.out.println("Ingrese el número 1 de 3 (Debe ser entero):");
        primero = scanner.nextInt();
        System.out.println("Ingrese el número 2 de 3 (Debe ser entero):");
        segundo = scanner.nextInt();
        System.out.println("Ingrese el número 3 de 3 (Debe ser entero):");
        tercero = scanner.nextInt();
        if (primero < segundo) {
            if (segundo < tercero) {
                System.out.println("Los números ordenados son: " + primero + " - " + segundo + " - " + tercero);
            } else {
                if (tercero < primero) {
                    System.out.println("Los números ordenados son: " + tercero + " - " + primero + " - " + segundo);
                } else {
                    System.out.println("Los números ordenados son: " + primero + " - " + tercero + " - " + segundo);
                }
            }
        } else {
            if (primero < tercero) {
                System.out.println("Los números ordenados son: " + segundo + " - " + primero + " - " + tercero);
            } else {
                if (tercero < segundo) {
                    System.out.println("Los números ordenados son: " + tercero + " - " + segundo + " - " + primero);
                } else {
                    System.out.println("Los números ordenados son: " + segundo + " - " + tercero + " - " + primero);
                }
            }
        }
    }
}

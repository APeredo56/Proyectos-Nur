package main;

import java.util.Scanner;

public class MenorAMayorMejorado {
    public static void main(String[] args) {
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
        if (primero < segundo && primero < tercero) {
            if (segundo < tercero) {
                System.out.println("Los números ordenados son: " + primero + " - " + segundo + " - " + tercero);
            } else {
                System.out.println("Los números ordenados son: " + primero + " - " + tercero + " - " + segundo);
            }
        }
        if (segundo < primero && segundo < tercero) {
            if (primero < tercero) {
                System.out.println("Los números ordenados son: " + segundo + " - " + primero + " - " + tercero);
            } else {
                System.out.println("Los números ordenados son: " + segundo + " - " + tercero + " - " + primero);
            }
        }
        if (tercero < primero && tercero < segundo) {
            if (primero < segundo) {
                System.out.println("Los números ordenados son: " + tercero + " - " + primero + " - " + segundo);
            } else {
                System.out.println("Los números ordenados son: " + tercero + " - " + segundo + " - " + primero);
            }
        }
    }
}

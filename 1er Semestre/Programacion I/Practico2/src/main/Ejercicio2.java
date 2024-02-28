package main;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        int variableA;
        int variableB;
        Scanner lector;
        System.out.println(">>> Intercambio de valores de dos variables sin auxiliar <<<");
        System.out.println("Ingrese el valor de la primera variable (Debe ser un numero entero):");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                variableA = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                variableA = 0;
            }
        } while (variableA == 0);
        System.out.println("Ingrese el valor de la segunda variable (Debe ser un numero entero):");
        do {
            try {
                String auxiliar = lector.nextLine();
                variableB = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                variableB = 0;
            }
        } while (variableB == 0);
        System.out.println("Valor de las variables antes del intercambio: A = " + variableA + ", B = " + variableB);
        variableA = variableA + variableB;
        variableB = variableA - variableB;
        variableA = variableA - variableB;
        System.out.println("Valor de las variables despues del intercambio: A = " + variableA + ", B = " + variableB);
    }
}

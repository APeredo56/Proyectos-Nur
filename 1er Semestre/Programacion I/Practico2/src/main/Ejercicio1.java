package main;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        int variableA;
        int variableB;
        int variableC;
        int auxiliar;
        Scanner lector;
        System.out.println(">>> Intercambio de valores entre variables <<<");
        System.out.println("Ingrese el valor de la variable A(Debe ser un numero entero):");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar2 = lector.nextLine();
                variableA = Integer.parseInt(auxiliar2);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                variableA = 0;
            }
        } while (variableA == 0);
        System.out.println("Ingrese el valor de la variable B(Debe ser un numero entero):");
        do {
            try {
                String auxiliar2 = lector.nextLine();
                variableB = Integer.parseInt(auxiliar2);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                variableB = 0;
            }
        } while (variableB == 0);
        System.out.println("Ingrese el valor de la variable C(Debe ser un numero entero):");
        do {
            try {
                String auxiliar2 = lector.nextLine();
                variableC = Integer.parseInt(auxiliar2);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                variableC = 0;
            }
        } while (variableC == 0);
        System.out.print("Valor de las variables antes del intercambio: A = " + variableA + ", B = " + variableB);
        System.out.println(", C = " + variableC);
        auxiliar = variableB;
        variableB = variableA;
        variableA = variableC;
        variableC = auxiliar;
        System.out.print("Valor de las variables despues del intercambio: A = " + variableA + ", B = " + variableB);
        System.out.println(", C = " + variableC);
    }
}

package main;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        int contador;
        int nlimite;
        int factorial;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println(">>>>> Factorial de un numero <<<<<");
        System.out.println("Ingresar un numero entero: ");
        nlimite = lector.nextInt();
        factorial = 1;
        contador = 0;
        for (contador, nlimite, contador += 1){
            factorial = factorial * contador;
        }
        System.out.println("El factorial de " + nlimite + " es: " + factorial);

    }

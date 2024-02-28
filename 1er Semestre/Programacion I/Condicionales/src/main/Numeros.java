package main;

import java.util.Scanner;

public class Numeros {
    public static void main(String[] args) {
        // Crear un programa que solicite al usuario dos números decimales, si el primer número es mayor al segundo
        // dividir en el orden que ingresaron. Caso contrario multiplicar los valores.
        float primerNumero;
        float segundoNumero;
        float resultado;
        Scanner scanner;
        // Bloque de Instrucciones
        System.out.println("--- Multiplicación y División ---");
        scanner = new Scanner(System.in);
        System.out.println("Ingrese un número decimal:");
        primerNumero = scanner.nextFloat();
        System.out.println("Ingrese un segundo número decimal:");
        segundoNumero = scanner.nextFloat();
        if (primerNumero > segundoNumero) {
            resultado = primerNumero / segundoNumero;
        } else {
            resultado = primerNumero * segundoNumero;
        }
        System.out.println("El resultado es: " + resultado);
    }
}

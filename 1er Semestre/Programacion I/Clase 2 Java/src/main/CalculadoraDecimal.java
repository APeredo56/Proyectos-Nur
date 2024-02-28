package main;

import java.util.Scanner;

public class CalculadoraDecimal {
    public static void main(String[] args) {
        float numero1;
        float numero2;
        float resultado;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println("Calculadora de numeros decimales");
        System.out.println("Ingrese el primer numero: ");
        numero1 = lector.nextFloat();
        System.out.println("Ingrese el segundo numero: ");
        numero2 = lector.nextFloat();
        if (numero1 > numero2) {
            resultado = numero1 / numero2;
            System.out.println("El resultado de dividir los dos numeros es: " + resultado);
        } else {
            resultado = numero1 * numero2;
            System.out.println("El resultado de multiplicar los dos numeros es: " + resultado);
        }
    }
}

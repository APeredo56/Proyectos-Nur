package repetitivos;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        // Elaborar un programa que permita el cálculo del factorial de un número ingresado por el usuario
        int limite;
        int factorial;
        Scanner scanner;
        System.out.println("--- Factorial de un número ---");
        scanner = new Scanner(System.in);
        factorial = 1;
        do {
            System.out.println("Ingrese un número entero positivo:");
            limite = scanner.nextInt();
        } while (limite <= 0);
        for (int acumulador = limite; acumulador >= 1; acumulador--) {
            factorial *= acumulador;
        }
        System.out.println("El factorial de " + limite + " es: " + factorial);
    }
}

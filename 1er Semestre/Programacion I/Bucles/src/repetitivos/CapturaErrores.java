package repetitivos;

import java.util.Scanner;

public class CapturaErrores {
    public static void main(String[] args) {
        int limite;
        int factorial;
        Scanner scanner;
        System.out.println("--- Factorial de un número ---");
        scanner = new Scanner(System.in);
        factorial = 1;
        try {
            do {
                System.out.println("Ingrese un número entero positivo:");
                limite = scanner.nextInt();
            } while (limite <= 0);
            for (int acumulador = limite; acumulador >= 1; acumulador--) {
                factorial *= acumulador;
            }
            limite = 10/0;
            System.out.println("El factorial de " + limite + " es: " + factorial);
        } catch (Exception error) {
            if (error.toString().equals("java.util.InputMismatchException")) {
                System.out.println("El programa funciona únicamente con números");
            } else {
                System.out.println("Ha ocurrido un problema, contacte con soporte.");
            }
        }
    }
}

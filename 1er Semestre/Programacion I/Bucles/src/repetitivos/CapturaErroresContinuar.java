package repetitivos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CapturaErroresContinuar {
    public static void main(String[] args) {
        int limite;
        int factorial;
        Scanner scanner;
        System.out.println("--- Factorial de un número ---");
        scanner = new Scanner(System.in);
        factorial = 1;
        do {
            try {
                System.out.println("Ingrese un número entero positivo:");
                String auxiliar = scanner.nextLine();
                limite = Integer.parseInt(auxiliar);
                // auxiliar = true + "";
                // double otro = Double.parseDouble(auxiliar);
                // float mas = Float.parseFloat():
            } catch (Exception error) {
                limite = 0;
                if (error.getClass().getName().equals("java.util.InputMismatchException")) {
                    System.out.println("El programa funciona únicamente con números, causado por texto en la toma de datos");
                }
                if (error.getClass().getName().equals("java.lang.NumberFormatException")) {
                    System.out.println("El programa funciona únicamente con números, causado por texto al convertir");
                }
            }
        } while (limite <= 0);
        for (int acumulador = limite; acumulador >= 1; acumulador--) {
            factorial *= acumulador;
        }
        // limite = 10/0;
        System.out.println("El factorial de " + limite + " es: " + factorial);
    }
}

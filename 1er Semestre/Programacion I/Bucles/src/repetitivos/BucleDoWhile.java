package repetitivos;

import java.util.Scanner;

public class BucleDoWhile {
    public static void main(String[] args) {
        int limite;
        int acumulador;
        Scanner scanner;

        System.out.println("--- Bucle do while ---");
        System.out.println("Este bucle realiza una acción inicial para luego consultar por la condición lógica.");
        System.out.println("Hay que tomar en cuenta que se realizará la operación una vez al menos.");
        acumulador = 1;
        scanner = new Scanner(System.in);
        System.out.println("Ingrese el número hasta el que desea contar:");
        limite = scanner.nextInt();
        // palabra reservada do seguido de {
        do {
            System.out.println(acumulador);
            acumulador++;
        } while (acumulador <= limite);
        // } while (condición_lógica);
    }
}

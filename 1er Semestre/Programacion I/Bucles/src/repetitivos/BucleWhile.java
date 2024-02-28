package repetitivos;

import java.util.Scanner;

public class BucleWhile {
    public static void main(String[] args) {
        int limite;
        int acumulador;
        Scanner lector;

        System.out.println("--- Bucle While ---");
        System.out.println("Este bucle realiza las acciones de su interior siempre y cuando se cumpla la condición");
        System.out.println("que se encuentra en sus paréntesis.");
        lector = new Scanner(System.in);
        acumulador = 1;
        System.out.println("Ingrese el número hasta el que desea contar:");
        limite = lector.nextInt();
        // while (condición_lógica) {}
        while (acumulador <= limite) {
            System.out.println(acumulador);
            acumulador += 1;//acumulador++;//acumulador = acumulador + 1;
        }
    }
}

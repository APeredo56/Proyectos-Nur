package main;

import java.util.Scanner;

public class Ejercicio13 {
    public static void main(String[] args) {
        Scanner lector;
        int limite;
        int contador1;
        System.out.println(">>> Serie numerica extensiva <<<");
        System.out.println("Ingresar el numero de elementos de la serie que desea ver:");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                limite = Integer.parseInt(auxiliar);
                if (limite < 0) {
                    limite = 0;
                    System.out.println("El programa solo funciona con numeros enteros positivos");
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos");
                limite = 0;
            }
        } while (limite == 0);
        for (contador1 = 1; contador1 <= limite; contador1 += 1){
            for (int contador2 = 1; contador2 <= contador1; contador2 += 1) {
                System.out.print(contador2);
            }
            System.out.print(" ");
        }
    }
}

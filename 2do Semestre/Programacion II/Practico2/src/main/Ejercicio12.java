package main;

import java.util.Scanner;

public class Ejercicio12 {
    public static void main(String[] args) {
        int contador;
        int limite;
        int elemento1;
        int elemento2;
        Scanner lector;
        System.out.println(">>> Serie de Fibonacci <<<");
        System.out.println("Ingrese la cantidad de elementos de la serie que desea ver:");
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
        elemento1 = 0;
        elemento2 = 1;
        if (limite == 1) {
            System.out.println(elemento1);
        } else {
            for (contador = 1; contador <= limite; contador += 1) {
                System.out.print(elemento1 + ", ");
                elemento1 = elemento1 + elemento2;
                contador += 1;
                if (contador <= limite) {
                    System.out.print(elemento2 + ", ");
                    elemento2 = elemento2 + elemento1;
                }

            }
        }
    }
}

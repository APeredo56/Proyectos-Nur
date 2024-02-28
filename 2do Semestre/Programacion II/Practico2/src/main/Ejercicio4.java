package main;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        int numero;
        Scanner lector;
        System.out.println(">>> Identificador de numeros <<<");
        System.out.println("Ingrese un numero entero para evaluar si es positivo, negativo o neutro:");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Integer.parseInt(auxiliar);
                if (numero == 0){
                    break;
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                 numero = 0;
            }
        } while (numero == 0);
        if (numero == 0){
            System.out.println("El numero " + numero + " es neutro");
        } else {
            if (numero > 0){
                System.out.println("El numero " + numero + " es positivo");
            } else {
                System.out.println("El numero " + numero + " es negativo");
            }
        }
    }
}

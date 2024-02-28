package main;

import java.util.Scanner;

public class Ejercicio14 {
    public static void main(String[] args) {
        int longitud;
        String cadenanum;
        long numero;
        Scanner lector;
        System.out.println(">>> Contador de digitos de un numero <<<");
        System.out.println("Ingresar un numero entero para contar sus digitos:");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Long.parseLong(auxiliar);
                if (numero == 0){
                    break;
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                numero = 0;
            }
        } while (numero == 0);
        cadenanum = numero + "";
        if (numero > 0){
            System.out.println("El numero " + numero + " tiene " + cadenanum.length() + " digitos" );
        } else {
            System.out.println("El numero " + numero + " tiene " + (cadenanum.length() - 1) + " digitos" );
        }
    }
}

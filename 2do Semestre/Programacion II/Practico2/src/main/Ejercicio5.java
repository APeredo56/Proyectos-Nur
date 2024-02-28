package main;

import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        int numero1;
        int numero2;
        Scanner lector;
        System.out.println(">>> Identificador del numero mayor <<<");
        lector = new Scanner(System.in);
        System.out.println("Ingresar el numero 1 de 6:");
        do {
            try {
                String auxiliar = lector.nextLine();
                numero1 = Integer.parseInt(auxiliar);
                if (numero1 == 0){
                    break;
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros");
                numero1 = 0;
            }
        } while (numero1 == 0);
        for (int contador = 2; contador <= 6; contador ++) {
            System.out.println("Ingresar el numero " + contador + " de 6:");
            do {
                try {
                    String auxiliar = lector.nextLine();
                    numero2 = Integer.parseInt(auxiliar);
                    if (numero2 == 0){
                        break;
                    }
                } catch (Exception error) {
                    System.out.println("El programa solo funciona con numeros");
                    numero2 = 0;
                }
                if (numero1 < numero2){
                    numero1 = numero2;
                }
            } while (numero2 == 0);

        }
        System.out.println("El numero " + numero1 + " es el mayor de los numeros ingresados");
    }
}

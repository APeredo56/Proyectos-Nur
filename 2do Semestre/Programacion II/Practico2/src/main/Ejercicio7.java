package main;

import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
        int limite;
        long multiplicacion;
        Scanner lector;
        System.out.println(">>> Multiplicador de los primeros N numeros naturales <<<");
        System.out.println("Ingrese hasta que numero desea multiplicar");
        lector = new Scanner(System.in);
        multiplicacion = 1;
        do {
            try {
                String auxiliar = lector.nextLine();
                limite = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos");
                limite = 0;
            }
        } while (limite <= 0);
        for (int contador = 1; contador <= limite; contador ++) {
            multiplicacion = multiplicacion * contador;
        }
        System.out.println("El resultado de multiplicar los primeros " + limite + " numeros es " + multiplicacion);
    }
}

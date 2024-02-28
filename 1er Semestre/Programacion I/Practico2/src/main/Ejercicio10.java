package main;

import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {
        int numero;
        int contdivisones;
        Scanner lector;
        System.out.println(">>> Verificador de numeros primos <<<");
        System.out.println("Ingrese un numero para verificar si es primo: ");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros");
                numero = 0;
            }
        } while (numero == 0);
        contdivisones = 0;
        for (int contador = 1; contador <= numero; contador += 1){
            if (numero % contador == 0){
                contdivisones += 1;
            }
        }
        if (contdivisones == 2){
            System.out.println("El numero ingresado es primo");
        } else {
            System.out.println("El numero ingresado no es primo");
        }
    }
}

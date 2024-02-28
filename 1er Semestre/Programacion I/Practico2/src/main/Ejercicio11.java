package main;

import java.util.Scanner;

public class Ejercicio11 {
    public static void main(String[] args) {
        long numero;
        String cadenanum;
        int longitud;
        int digito;
        int diferencia;
        Scanner lector;
        System.out.println(">>> Verificador de numeros capicua <<<");
        System.out.println("Ingrese un numero entero positivo de dos o mas digitos");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Integer.parseInt(auxiliar);
                if (numero < 9){
                    numero = 0;
                    System.out.println("El programa solo funciona con numeros enteros positivos de dos o mas digitos");
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros positivos de dos o mas digitos");
                numero = 0;
            }
        } while (numero == 0);
        cadenanum = numero + "";
        digito = 0;
        diferencia = 0;
        longitud = cadenanum.length();
        while (digito < longitud){
            if (!cadenanum.substring(digito,digito + 1).equals(cadenanum.substring(longitud-1,longitud))){
                diferencia += 1;
            }
            longitud -= 1;
            digito += 1;
        }
        if (diferencia == 0){
            System.out.println("El numero " + numero + " es capicua");
        } else {
            System.out.println("El numero " + numero + " no es capicua");
        }
    }
}

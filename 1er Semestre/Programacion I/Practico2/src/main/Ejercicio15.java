package main;

import java.util.Scanner;

public class Ejercicio15 {
    public static void main(String[] args) {
        long numero;
        Scanner lector;
        String cadenanum;
        String numinvertido;
        int longitud;
        int posicion;
        System.out.println(">>> Inversor de numeros <<<");
        System.out.println("Ingrese un numero entero para invertirlo:");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Long.parseLong(auxiliar);
                cadenanum = numero + "";
                longitud = cadenanum.length();
                if (numero < 0 && longitud < 3) {
                    longitud = 0;
                    numero = 0;
                    System.out.println("El programa solo funciona con numeros enteros de dos o mas digitos");
                }
                if (numero > 0 && longitud < 2) {
                    longitud = 0;
                    numero = 0;
                    System.out.println("El programa solo funciona con numeros enteros de dos o mas digitos");
                }
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros enteros entre 2 y 19 digitos");
                numero = 0;
                longitud = 0;
            }
        } while (numero == 0 && longitud == 0);
        numinvertido = "";
        posicion = 0;
        cadenanum = numero + "";
        if (numero >= 0) {
            while (posicion < longitud) {
                numinvertido = cadenanum.substring(posicion, posicion + 1) + numinvertido;
                posicion += 1;
            }
            System.out.println
                    ("Numero invertido: " + numinvertido);
        } else {
            posicion = 1;
            while (posicion < longitud) {
                numinvertido = cadenanum.substring(posicion, posicion + 1) + numinvertido;
                posicion += 1;
            }
            System.out.println("Numero invertido: -" + numinvertido);
        }

    }
}


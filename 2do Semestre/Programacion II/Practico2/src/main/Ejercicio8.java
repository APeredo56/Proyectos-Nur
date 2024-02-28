package main;

import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        long numero;
        int longitud;
        String numinvertido;
        String cadena;
        Scanner lector;
        System.out.println(">>> Inversor del primer y ultimo digito de un numero <<<");
        System.out.println("Ingrese un numero entero de dos o mas digitos: ");
        lector = new Scanner(System.in);
        longitud = 1;
        cadena = "";
        do {
            try {
                String auxiliar = lector.nextLine();
                numero = Long.parseLong(auxiliar);
                cadena = numero + "";
                longitud = cadena.length();
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
        System.out.println("Numero antes del intercambio: " + numero);
        if (numero > 0) {
            numinvertido = cadena.substring(longitud - 1, longitud);
            numinvertido = numinvertido + cadena.substring(1, longitud - 1);
            numinvertido = numinvertido + cadena.substring(0, 1);
            System.out.println("Numero despues del intercambio: " + numinvertido);
        }
        if (numero < 0) {
            numinvertido = cadena.substring(longitud - 1, longitud);
            numinvertido = numinvertido + cadena.substring(2, longitud - 1);
            numinvertido = numinvertido + cadena.substring(1, 2);
            System.out.println("Numero despues del intercambio: -" + numinvertido);
        }
        }


    }


package main;

import java.util.Scanner;

public class JuegoNombre {
    // Solicita al usuario ingrese su nombre y retornar con las posiciones incluidas:
    // Eduardo -> E1d2u3a4r5d6o7 | Juan -> J1u2a3n4
    public static void main(String[] args) {
        String nombre;
        String resultado;
        Scanner scanner;

        System.out.println("--- Juego con Nombres ---");
        scanner = new Scanner(System.in);
        resultado = "";
        System.out.println("Ingrese su nombre:");
        nombre = scanner.nextLine();
        for (int pos = 0; pos < nombre.length(); pos++) {
            //resultado += nombre.substring(pos, pos + 1) + (pos + 1);
            resultado = resultado.concat(nombre.substring(pos, pos + 1)).concat((pos + 1) + "");
        }
        System.out.println(resultado);
    }
}

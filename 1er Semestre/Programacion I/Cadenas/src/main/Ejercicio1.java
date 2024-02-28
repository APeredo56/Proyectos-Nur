package main;

import java.util.Scanner;

public class Ejercicio1 {
    // Escribir un programa que solicite al usuario ingrese una contraseña, si esta es correcta darle la
    // bienvenida, por el contrario si falla, tiene un máximo de 3 intentos antes de bloquear el acceso.
    // NUR2021!abc ->
    public static void main(String[] args) {
        String seguro;
        String contrasenia;
        Scanner scanner;
        int maximo;
        int intentos;
        System.out.println("--- Zona Segura ---");
        seguro = "NUR2021!abc";
        maximo = 3;
        intentos = 1;
        scanner = new Scanner(System.in);
        do {
            System.out.println("Intento #" + intentos);
            System.out.println("Ingrese la contraseña para acceder al sitio:");
            contrasenia = scanner.nextLine();
            intentos++;
        } while (!seguro.equals(contrasenia) && intentos <= maximo);
        //El operador AND es && y el operador OR es es ||
        if (seguro.equals(contrasenia)) {
            System.out.println("Bienvenido a la zona segura.");
        } else {
            System.out.println("Ha alcanzado el límite de intentos por hora, intente más tarde.");
        }
    }
}

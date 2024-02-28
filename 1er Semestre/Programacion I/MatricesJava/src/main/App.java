package main;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProyectosV3 manager = new ProyectosV3(3);
        int obtenido = manager.obtenerNumeroEntero(scanner);
        System.out.println("Tu número es: " + obtenido);
    }
}

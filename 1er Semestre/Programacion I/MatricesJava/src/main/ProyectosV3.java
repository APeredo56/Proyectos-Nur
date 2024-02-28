package main;

import java.util.Scanner;

public class ProyectosV3 {
    // Bloque de Declaraciones
    String[][] registro;
    int posicionActual;

    // Bloque de Instrucciones
    public ProyectosV3(int cantidad) {
        // Coloco tres filas, porque almacenaré tres datos: Nombre Completo, Proyecto, Nota
        this.registro = new String[cantidad][3];
        this.posicionActual = 0;
    }

    public int obtenerNumeroEntero(Scanner scanner) {
        int numero = 0;
        boolean hayNumero = false;
        do {
            try {
                System.out.println("Ingrese un número entero:");
                String auxiliar = scanner.nextLine();
                numero = Integer.parseInt(auxiliar);
                hayNumero = true;
            } catch (Exception error) {
                System.out.println("El programa sólo admite números.");
            }
        } while (!hayNumero);
        return numero;
    }
}

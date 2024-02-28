package main;

import java.util.Scanner;

public class Rot13Arreglos {
    public static void main(String[] args) {
        String[] linea1 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
        String[] linea2 = {"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Scanner lector;
        String mensaje;
        String traduccion;

        System.out.println("--- ROT 13 ---");
        lector = new Scanner(System.in);
        traduccion = "";
        System.out.println("Ingrese el mensaje para codificar/decodificar:");
        mensaje = lector.nextLine();
        for (int posicion = 0; posicion < mensaje.length(); posicion++) {
            String letra = mensaje.substring(posicion, posicion + 1);
            // Buscamos letra en el primer arreglo
            int encontrado = -1;
            for (int indice = 0; indice < linea1.length; indice++) {
                if (letra.equalsIgnoreCase(linea1[indice])) {
                    encontrado = indice;
                }
            }
            // Verificamos si se encontró la letra, sino revisamos en la siguiente línea
            if (encontrado != -1) {
                traduccion = traduccion.concat(linea2[encontrado]);
            } else {
                for (int indice = 0; indice < linea2.length; indice++) {
                    if (letra.equalsIgnoreCase(linea2[indice])) {
                        encontrado = indice;
                    }
                }
                // Verificamos si se encontró la letra, sino pasamos la letra a traducción
                if (encontrado != -1) {
                    traduccion = traduccion.concat(linea1[encontrado]);
                } else {
                    traduccion = traduccion.concat(letra);
                }
            }
        }
        System.out.println("El mensaje codificado/decodificado es:");
        System.out.println(traduccion);
    }
}

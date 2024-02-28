package main;

import java.util.Scanner;

public class CondicionalMultiple {
    public static void main(String[] args) {
        int elegido;
        int cupones;
        Scanner lector;
        System.out.println("--- Sorteo ---");
        lector = new Scanner(System.in);
        cupones = 0;
        System.out.println("Ingrese un número entre 1 y 10:");
        elegido = lector.nextInt();
        // switch (elemento de estudio) {
        switch (elegido) {
            // case una_de_las_posibilidades (tipo de dato del elemento de estudio
            case 1:
                System.out.println("Siga partipando, ganaste un chicle");
                cupones = elegido * 2;
                // break; Rompe con el ámbito del caso en cuestión
                break;
            case 7:
                System.out.println("Siga partipando, ganaste tres chicles");
                break;
            case 8:
                System.out.println("Siga partipando, ganaste 5 chicles");
                break;
            case 9:
                System.out.println("Ganaste un Cine en Casa!");
                cupones = elegido * 3;
                break;
            case 10:
                System.out.println("Ganaste un pasaje a Porongo!");
                break;
            case 2:
                System.out.println("Ganaste un balón de fútbol!");
                cupones = elegido * 5;
                break;
            case 3:
                System.out.println("Siga partipando, ganaste 6 chicles");
                break;
            case 6:
                System.out.println("Ganste un televisor!");
                break;
            case 4:
                System.out.println("Siga partipando, ganaste dos chicles");
                cupones = elegido * 7;
                break;
            case 5:
                System.out.println("Ganaste una alcancía de cerámica!");
                break;
            default:
                System.out.println("El número no existe en el sorteo, usted es un tramposo!");
                break;
        }
        System.out.println("Obtuviste " + cupones + " cupones.");
    }
}

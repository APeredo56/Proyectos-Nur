package repetitivos;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        // Escribir un programa que le solicite al usuario un número entero, se debe generar la tabla de multiplicar
        // desde el número 1 hasta el número que ingresó el usuario. Ejemplo: 6
        // 1 x 1 = 1
        // 1 x 2 = 2 ...
        // 1 x 10 = 10
        // 2 x 1 = 2
        int multiplicando;
        int multiplicador;
        int limite;
        Scanner scanner;
        System.out.println("--- Tablas de Multiplicar ---");
        scanner = new Scanner(System.in);
        multiplicando = 1;
        multiplicador = 1;
        do {
            try {
                System.out.println("Ingrese el número de tablas de multiplicar que desea ver:");
                String auxiliar = scanner.nextLine();
                limite = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                limite = 0;
                System.out.println("Sólo se aceptan números.");
            }
        } while (limite <= 0);
        // Comienza la tabla
        while(multiplicando <= limite){
            multiplicador = 1;
            while (multiplicador <= 10){
                System.out.println(multiplicando + " x " + multiplicador + " = " + (multiplicando * multiplicador));
                multiplicador++;
            }
            System.out.println("");
            multiplicando++;
        }
    }
}

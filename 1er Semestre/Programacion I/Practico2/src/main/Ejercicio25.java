package main;
import java.util.Scanner;
public class Ejercicio25 {
    public static void main(String[] args) {
        int cantidad;
        Scanner lector;
        System.out.println(">>> Serie grafica rombo <<<");
        lector = new Scanner(System.in);
        System.out.println("Indique hasta que numero llegara el rombo:");
        do {
            try {
                String auxiliar = lector.nextLine();
                cantidad = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros.");
                cantidad = 0;
            }
        } while (cantidad <= 0);
        for (int filas = 1; filas <= cantidad; filas++) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                if (columnas == 1) {
                    System.out.print(" ".repeat(cantidad - filas));
                }
                System.out.print(columnas + " ");
            }
            System.out.println("");
        }
        for (int filas = cantidad; filas >= 1; filas--) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                if (columnas == 1) {
                    System.out.print(" ".repeat(cantidad - filas));
                }
                System.out.print(columnas + " ");
            }
            System.out.println("");
        }
    }
}

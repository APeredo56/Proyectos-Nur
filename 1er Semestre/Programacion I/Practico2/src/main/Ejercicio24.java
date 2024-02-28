package main;
import java.util.Scanner;
public class Ejercicio24 {
    public static void main(String[] args) {
        int limite;
        Scanner lector;
        System.out.println(">>> Serie grafica triangulo <<<");
        lector = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de filas que desea ver:");
        do {
            try {
                String auxiliar = lector.nextLine();
                limite = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros");
                limite = 0;
            }
        } while (limite <= 0);
        for (int filas = 1; filas <= limite; filas++) {
            for (int columnas = 1; columnas <= filas; columnas++) {
                System.out.print(columnas + " ");
            }
            System.out.println("");
        }
    }
}

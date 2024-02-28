package main;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        double pmexicanos;
        double pbolivianos;
        Scanner lector;
        System.out.println(">>> Conversor de pesos mexicanos a pesos bolivianos <<<");
        System.out.println("Ingrese la cantidad de pesos mexicanos a convertir: ");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                pmexicanos = Double.parseDouble(auxiliar);
            } catch (Exception error) {
                System.out.println("El programa solo funciona con numeros");
                pmexicanos = 0;
            }
        } while (pmexicanos == 0);
        pbolivianos = pmexicanos * 0.348;
        System.out.println(pmexicanos + " pesos mexicanos = " + pbolivianos + " pesos bolivianos");

    }
}

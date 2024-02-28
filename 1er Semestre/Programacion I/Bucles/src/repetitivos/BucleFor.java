package repetitivos;

import java.util.Scanner;

public class BucleFor {
    public static void main(String[] args) {
        int limite;
        Scanner lector;

        System.out.println("--- Bucle for ---");
        System.out.println("Este bucle es es exclusivo para la utilización con números, no necesita un acumulador");
        System.out.println("porque lo tiene incorporado.");
        lector = new Scanner(System.in);
        System.out.println("Ingrese el número hasta el que desea contar:");
        limite = lector.nextInt();
        // for(T.D variable_acumuladora = valor_inicio; condición_lógica; incremento/decremento){}
        for (int acumulador = 1; acumulador <= limite; acumulador += 1) { //acumulador--; acumulador = acumulador - 1;
            System.out.println(acumulador);
        }
    }
}

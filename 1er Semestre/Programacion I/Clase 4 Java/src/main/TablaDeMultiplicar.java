package main;

import java.util.Scanner;

public class TablaDeMultiplicar {
    public static void main(String[] args) {
        int limite;
        int contador;
        int contador2;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println("Tablas de multiplicar");
        System.out.println("Ingrese hasta que numero de tabla desea ver:");
        limite = lector.nextInt();
        for (contador = 1; contador <= limite; contador += 1){
            System.out.println("Tabla de multiplicar del " + contador);
            for (contador2 = 1; contador2 <= 10; contador2 += 1){
                System.out.println(contador + " * " + contador2 + " = " + (contador * contador2));
            }
        }
    }
}

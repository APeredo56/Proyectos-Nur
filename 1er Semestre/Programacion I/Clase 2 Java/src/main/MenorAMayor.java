package main;

import java.util.Scanner;

public class MenorAMayor<While, sout> {
    public static void main(String[] args) {
        int numero1;
        int numero2;
        int numero3;
        int mayor;
        int menor;
        int medio;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println("Ordenador de numeros de menor a mayor");
        System.out.println("Ingresar el primer numero: ");
        numero1 = lector.nextInt();
        System.out.println("Ingresar el segundo numero: ");
        numero2 = lector.nextInt();
        System.out.println("Ingresar el tercer numero: ");
        numero3 = lector.nextInt();
        medio = 0;
        menor = 0;
        mayor = 0;
        if (numero1 > numero2 && numero1 > numero3) {
            mayor = numero1;
            if (numero2 > numero3){
                medio = numero2;
                menor = numero3;
            } else {
                medio = numero3;
                menor = numero2;
            }
        }
        if (numero2 > numero1 && numero2 > numero3) {
            mayor = numero2;
            if (numero1 > numero3){
                medio = numero1;
                menor = numero3;
            } else {
                medio = numero3;
                menor = numero1;
            }
        }
        if (numero3 > numero1 && numero3 > numero2) {
            mayor = numero3;
            if (numero1 > numero2) {
                medio = numero1;
                menor = numero2;
            } else {
                medio = numero2;
                menor = numero1;
            }
        }
        System.out.println(menor + " < " + medio + " < " + mayor);
    }

}

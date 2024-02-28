package main;
import java.util.Scanner;
public class Ejercicio22 {
    public static void main(String[] args) {
        int numerovocales;
        String texto;
        String identificador;
        Scanner lector;
        String letra;
        int longitud;
        System.out.println(">>> Contador de vocales de un texto <<<");
        System.out.println("Ingrese un texto para contar sus vocales:");
        lector = new Scanner(System.in);
        texto = lector.nextLine();
        longitud = texto.length();
        numerovocales = 0;
        for (int contador = 0; contador < longitud; contador +=1){
            letra = texto.substring(contador,contador + 1);
            switch (letra){
                case "a":
                case "e":
                case "o":
                case "i":
                case "u":
                    numerovocales += 1;
                    break;
            }
        }
        System.out.println("El texto ingresado contiene " + numerovocales + " vocales");
    }
}

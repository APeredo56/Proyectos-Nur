package main;
import java.util.Scanner;
public class Ejercicio19 {
    public static void main(String[] args) {
        String texto;
        String letra;
        Scanner lector;
        int longitud;
        System.out.println(">>> Ampliador de texto <<<");
        System.out.println("Ingrese el texto a ampliar:");
        lector = new Scanner(System.in);
        texto = lector.nextLine();
        longitud = texto.length();
        System.out.println("Texto ampliado: ");
        for (int contador = 1; contador <= longitud; contador += 1){
            letra = texto.substring(contador-1, contador);
            System.out.print(letra.repeat(contador));
        }

    }
}

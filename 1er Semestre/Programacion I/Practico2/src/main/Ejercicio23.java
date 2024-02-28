package main;
import java.util.Scanner;
public class Ejercicio23 {
    public static void main(String[] args) {
        String texto;
        String letra;
        Scanner lector;
        int longitud;
        System.out.println(">>> Modificador de texto <<<");
        System.out.println("Ingrese un texto para reemplazar sus caracteres pares por un *:");
        lector = new Scanner(System.in);
        texto = lector.nextLine();
        longitud = texto.length();
        letra = "";
        for (int contador = 0; contador < longitud; contador += 1){
            letra = texto.substring(contador, contador + 1);
            if (contador % 2 == 0){
                System.out.print(letra);
            } else {
                System.out.print("*");
            }
        }
    }
}

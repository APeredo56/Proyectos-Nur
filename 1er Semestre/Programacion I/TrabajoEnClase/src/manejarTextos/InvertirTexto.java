package manejarTextos;

import java.util.Scanner;

public class InvertirTexto {
    public static void main(String[] args) {
        String texto;
        String textoInvertido;
        String letra;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println(">>> Inversor de texto <<<");
        System.out.println("Ingrese el texto a invertir:");
        texto = lector.nextLine();
        textoInvertido = "";
        for (int contador = texto.length() - 1; contador >= 0; contador--) {
            letra = texto.substring(contador, contador + 1);
            textoInvertido += letra;
        }
        System.out.println(textoInvertido);
    }

    String vocales = "aeiou";


}

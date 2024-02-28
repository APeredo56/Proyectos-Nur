package trabajo1;

import java.util.Scanner;

public class CapitalizarLetras {
    public static void main(String[] args) {
        String texto;
        String textoCapitalizado;
        String mayuscula;
        String minuscula;
        int longitud;
        int longitud2;
        int indice;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println(">>> Capitalizador De Oraciones");
        System.out.println("Ingrese el texto a capitalizar:");
        texto = lector.nextLine();
        texto = texto.toUpperCase() + " ";
        longitud = texto.length();
        longitud2 = texto.length();
        textoCapitalizado = "";
       while (longitud >= 1){
            int contador = 0;
            indice = texto.indexOf(" ");
            textoCapitalizado = textoCapitalizado + texto.substring(0, 1);
            textoCapitalizado = textoCapitalizado + texto.toLowerCase().substring(1, indice) + " ";
            texto = texto.substring(indice + 1, longitud);
            longitud = texto.length();
        }
        System.out.println(textoCapitalizado);
    }
}

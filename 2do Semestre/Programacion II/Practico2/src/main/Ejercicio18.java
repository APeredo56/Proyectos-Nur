package main;
import java.util.Scanner;
public class Ejercicio18 {
    public static void main(String[] args) {
        String texto;
        Scanner lector;
        String cifrador;
        String letra;
        int longitud;
        String subcadena;
        String textocifrado;
        System.out.println(">>> Codificador en ROT13 <<<");
        System.out.println("Ingrese el texto a codificar o descodificar (la letra 'ñ' sera sustituida por 'n':");
        lector = new Scanner(System.in);
        texto = lector.nextLine();
        longitud = texto.length();
        letra = "";
        textocifrado = "";

        for (int contador = 0; contador < longitud; contador += 1){
            subcadena = texto.toLowerCase().substring(contador, contador + 1);
            switch (subcadena){
                case "a":
                    letra = "n";
                    break;
                case "b":
                    letra = "o";
                    break;
                case "c":
                    letra = "p";
                    break;
                case "d":
                    letra = "q";
                    break;
                case "e":
                    letra = "r";
                    break;
                case "f":
                    letra = "s";
                    break;
                case "g":
                    letra = "t";
                    break;
                case "h":
                    letra = "u";
                    break;
                case "i":
                    letra = "v";
                    break;
                case "j":
                    letra = "w";
                    break;
                case "k":
                    letra = "x";
                    break;
                case "l":
                    letra = "y";
                    break;
                case "m":
                    letra = "z";
                    break;
                case "n":
                    letra = "a";
                    break;
                case "o":
                    letra = "b";
                    break;
                case "p":
                    letra = "c";
                    break;
                case "q":
                    letra = "d";
                    break;
                case "r":
                    letra = "e";
                    break;
                case "s":
                    letra = "f";
                    break;
                case "t":
                    letra = "g";
                    break;
                case "u":
                    letra = "h";
                    break;
                case "v":
                    letra = "i";
                    break;
                case "w":
                    letra = "j";
                    break;
                case "x":
                    letra = "k";
                    break;
                case "y":
                    letra = "l";
                    break;
                case "z":
                    letra = "m";
                    break;
                case "ñ":
                    letra = "a";
                    break;
                default:
                    letra = subcadena;
            }
            textocifrado = textocifrado + letra;
        }
        System.out.println("Su texto cifrado/descifrado en ROT13 es: " + textocifrado);

    }
}

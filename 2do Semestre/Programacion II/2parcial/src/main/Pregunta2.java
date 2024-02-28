package main;
import java.util.Scanner;
public class Pregunta2 {
    public static void main(String[] args) {
        String texto;
        String letra;
        int longitud;
        Scanner lector;
        int contador;
        int contadorletras;
        contador = 0;
        String letratexto;
        System.out.println(">>> Contador de letras en un texto <<<");
        lector = new Scanner(System.in);
        do {
            if (contador > 0){
                System.out.println("Error el texto ingresado contiene menos de 5 caracteres o mas de 25");
            }
            System.out.println("Ingrese un texto que contenga entre 5 y 25 caracteres:");
            texto = lector.nextLine();
            longitud = texto.length();
            contador += 1;
        } while (longitud < 5 || longitud > 25);
        contador = 0;
        do {
            if (contador > 0){
                System.out.println("Error, solo debe ingresar una letra");
            }
            System.out.println("Ingrese una letra para contar cuantas veces aparece en el texto:");
            letra = lector.nextLine();
            longitud = letra.length();
            contador += 1;
        } while (longitud > 1);
        contadorletras = 0;
        texto = texto.toUpperCase();
        letra = letra.toUpperCase();
        for (contador = 0; contador < texto.length(); contador += 1){
            letratexto = texto.substring(contador, contador + 1);
            if (letra.equals(letratexto)){
                contadorletras += 1;
            }
        }
        System.out.println("El texto ingresado contiene la letra '" + letra + "' " + contadorletras + " veces");

    }
}

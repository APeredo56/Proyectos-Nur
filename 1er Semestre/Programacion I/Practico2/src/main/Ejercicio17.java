package main;
import java.util.Scanner;
public class Ejercicio17 {
    public static void main(String[] args) {
        int longitud;
        String llave;
        String texto;
        String letra;
        int verificador;
        int posicion;
        String valor;
        String textocodificado;
        Scanner lector;
        System.out.println(">>> Codificador de texto segun llave elegida <<<");
        lector = new Scanner(System.in);
        verificador = 1;
        do {
            if (verificador > 1) {
                System.out.println("Error, la llave no debe contener mas de 10 caracteres");
            }
            System.out.println("Ingrese la llave a utilizar para codificar el texto (debe ser menor a 10 caracteres):");
            llave = lector.nextLine();
            verificador += 1;
            longitud = llave.length();
        } while (longitud > 10);
        System.out.println("Ingrese el texto a codificar:");
        texto = lector.nextLine();
        textocodificado = texto;
        for (int contador = 0; contador < longitud; contador += 1){
            letra = llave.substring(contador, contador + 1);
            posicion = contador;
            valor = posicion + "";
            textocodificado = textocodificado.replace(letra,valor);



        }
        System.out.println("Texto codificado:");
        System.out.println(textocodificado);
    }
}

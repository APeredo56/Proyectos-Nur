package main;
import java.util.Scanner;
public class Ejercicio21 {
    public static void main(String[] args) {
        String nombre1;
        String nombre2;
        String apellido1;
        String apellido2;
        String nombrecompleto;
        Scanner lector;
        int numeronombres;
        int separador;
        int longitud;
        System.out.println(">>> Inversor de nombres completos <<<");
        System.out.println("Indique cuantos nombres tiene: ");
        lector = new Scanner(System.in);
        do {
            try {
                String auxiliar = lector.nextLine();
                numeronombres = Integer.parseInt(auxiliar);
                if (numeronombres > 2 || numeronombres < 0) {
                    System.out.println("Error el numero de nombres debe ser indicado con '1' o '2'");
                }
            } catch (Exception error) {
                System.out.println("Error el numero de nombres debe ser indicado con '1' o '2'");
                numeronombres = 0;
            }
        } while (numeronombres <= 0 || numeronombres > 2);
        System.out.println("Ingrese su nombre completo (primero su(s) nombre(s) y luego apellidos):");
        nombrecompleto = lector.nextLine();
        if (numeronombres == 1){
            separador = nombrecompleto.indexOf(" ");
            nombre1 = nombrecompleto.substring(0, separador);
            longitud = nombrecompleto.length();
            nombrecompleto = nombrecompleto.substring(separador + 1, longitud);
            separador = nombrecompleto.indexOf(" ");
            apellido1 = nombrecompleto.substring(0, separador);
            longitud = nombrecompleto.length();
            nombrecompleto = nombrecompleto.substring(separador + 1, longitud);
            apellido2 = nombrecompleto;
            System.out.println("Nombre invertido: "+ apellido2 + " " + apellido1 + " " + nombre1);
        } else {
            separador = nombrecompleto.indexOf(" ");
            nombre1 = nombrecompleto.substring(0, separador);
            longitud = nombrecompleto.length();
            nombrecompleto = nombrecompleto.substring(separador + 1, longitud);
            separador = nombrecompleto.indexOf(" ");
            nombre2 = nombrecompleto.substring(0, separador);
            longitud = nombrecompleto.length();
            nombrecompleto = nombrecompleto.substring(separador + 1, longitud);
            separador = nombrecompleto.indexOf(" ");
            apellido1 = nombrecompleto.substring(0, separador);
            longitud = nombrecompleto.length();
            nombrecompleto = nombrecompleto.substring(separador + 1, longitud);
            apellido2 = nombrecompleto;
            System.out.println("Nombre invertido: "+ apellido2 + " " + apellido1 + " " + nombre2 + " " + nombre1);
        }
    }
}

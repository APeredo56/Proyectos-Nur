package main;
import java.util.Scanner;
public class Pregunta4 {
    public static void main(String[] args) {
        int numero;
        int contador2;
        Scanner lector;
        System.out.println(">>> Serie grafica reloj de arena <<<");
        lector = new Scanner(System.in);
        numero = 0;
        contador2 = 1;
        do {
            try {
                System.out.println("Ingrese un numero impar mayor a 5:");
                String auxiliar = lector.nextLine();
                numero = Integer.parseInt(auxiliar);
            } catch (Exception error) {
                System.out.println("Error el programa solo funciona con numeros positivos impares");
            }
        } while (numero < 5 || numero % 2 == 0);
        for (int contador = numero; contador > numero / 2; contador -=2){
            System.out.print((" ").repeat(numero - contador));
            System.out.println((contador2 + " ").repeat(contador));
            contador2 += 1;
            }

        }
    }


package main;
import java.util.Scanner;
public class Pregunta1 {
    public static void main(String[] args) {
        int mayorimpar;
        int menorpar;
        int numero;
        double promedio;
        int suma;
        int finalizador;
        int contador;
        Scanner lector;
        System.out.println(">>> Operaciones con la cantidad de numeros a elegir <<<");
        lector = new Scanner(System.in);
        finalizador = -1;
        contador = 1;
        mayorimpar = 0;
        menorpar = 999999999;
        suma = 0;
        promedio = 0;
        do {
            System.out.println("Ingrese el numero " + contador + ":");
            try {
                String auxiliar = lector.nextLine();
                numero = Integer.parseInt(auxiliar);
                if (numero != 0) {
                    if (numero % 2 == 0 && numero < menorpar) {
                        menorpar = numero;
                    }
                    if (numero % 2 != 0 && numero > mayorimpar) {
                        mayorimpar = numero;
                    }
                    suma = suma + numero;
                    promedio = suma / contador;
                    contador += 1;
                } else {
                    finalizador = 0;
                }
            } catch (Exception error){
                System.out.println("El programa solo funciona con numeros");
            }
        } while (finalizador != 0);

        if (mayorimpar == 0){
            System.out.println("No ingreso un numero impar");
        } else {
            System.out.println("El mayor numero impar ingresado es: " + mayorimpar);
        }
        if (menorpar == 999999999){
            System.out.println("No ingreso un numero par");
        }else {
            System.out.println("El menor numero par ingresado es: " + menorpar);
        }
        System.out.println("La suma total de los numeros ingresados es: " + suma);
        System.out.println("El promedio de los numeros ingresados es: " + promedio);

    }
}

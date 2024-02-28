import java.util.Scanner;

public class Multiplicador {
    public static void main(String[] args) {
        int numero1;
        int numero2;
        int numero3;
        int suma;
        int resultado;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println("Suma y Multiplicacion de numeros");
        System.out.println("Ingrese un numero Entero:");
        numero1 = lector.nextInt();
        System.out.println("Ingrese el segundo numero Entero para sumar:");
        numero2 = lector.nextInt();
        System.out.println("Ingrese el tercer numero Entero para multiplicar");
        numero3 = lector.nextInt();
        System.out.println("El resultado de la operacion es: " + (numero1 + numero2)* numero3);

    }
}

import java.util.Scanner;

public class Potenciador {
    public static void main(String[] args) {
        int base;
        Scanner lector;
        lector = new Scanner(System.in);
        System.out.println("Potencia 5 de un numero");
        System.out.println("Ingrese un numero entero para calcular su potencia:");
        base = lector.nextInt();
        System.out.println("El resultado es: " + Math.pow(base,5));
    }
}

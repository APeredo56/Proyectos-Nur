import java.util.Scanner;

public class Consola {
    public static void main(String[] args) {
        Numeros conversor = new Numeros();
        boolean validador = true;
        Scanner lector = new Scanner(System.in);
        String texto;
        System.out.println(">>> Conversor de numeros enteros a numeros romanos <<<");
        System.out.println("Ingrese los numeros enteros a convertir:");
        while (validador) {
            texto = lector.nextLine();
            if (texto.equals("0")){
                break;
            }
            conversor.convertirNumeros(texto);
        }
        System.out.println(conversor.getConversion());
    }
}

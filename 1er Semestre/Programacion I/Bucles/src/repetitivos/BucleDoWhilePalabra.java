package repetitivos;

import java.util.Scanner;

public class BucleDoWhilePalabra {
    public static void main(String[] args) {
        String password;
        String autorizado;
        Scanner scanner;

        System.out.println("--- Calabozo ---");
        autorizado = "gracias";
        scanner = new Scanner(System.in);
        do {
            System.out.println("Si quieres salir del calabozo, dinos la contraseña:");
            password = scanner.nextLine();
        } while (!autorizado.equals(password));
        System.out.println("Felicidades, la amabilidad te abrirá muchas puertas.");
    }
}

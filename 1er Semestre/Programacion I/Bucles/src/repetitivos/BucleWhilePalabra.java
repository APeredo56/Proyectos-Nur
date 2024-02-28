package repetitivos;

import java.util.Scanner;

public class BucleWhilePalabra {
    public static void main(String[] args) {
        String password;
        String autorizado;
        Scanner scanner;

        System.out.println("--- Calabozo ---");
        password = "";
        autorizado = "por favor";
        scanner = new Scanner(System.in);
        while (!autorizado.equals(password)) {
            System.out.println("Si quieres salir del calabozo, dinos la contraseña:");
            password = scanner.nextLine();
        }
        System.out.println("Felicidades, la amabilidad te abrirá muchas puertas.");
    }
}

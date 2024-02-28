package main;
import java.util.Scanner;
public class Ejercicio16 {
    public static void main(String[] args) {
        int intentos;
        String correctpassword;
        String password;
        Scanner lector;
        System.out.println(">>> Inicio de sesion <<<");
        System.out.println("Ingrese la contraseña:");
        lector = new Scanner(System.in);
        correctpassword = "12345678";
        intentos = 1;
        do {
            if (intentos >= 2) {
                System.out.println("Contraseña incorrecta, intento " + intentos + " de 3");
            }
            password = lector.nextLine();
            intentos += 1;
        } while (!password.equals(correctpassword) && intentos <= 3);
        if (password.equals(correctpassword)){
            System.out.println("Contraseña correcta, bienvenido de nuevo");
        } else {
            System.out.println("Excedio el limite de intentos, intente de nuevo mas tarde");
        }
    }
}

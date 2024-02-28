package main;

import java.util.Scanner;

public class Ejercicio {
    public static void main(String[] args) {
        int intentos;
        String password;
        String correctpassword;
        Scanner lector;
        lector = new Scanner(System.in);
        intentos = 1;
        password = "12345678";
        correctpassword = "";
        while (intentos <= 3) ||(!correctpassword.equals(password)) {
            System.out.println("Ingrese la contraseña:");
            correctpassword = lector.nextLine();
            intentos += 1;
        }
    }
}

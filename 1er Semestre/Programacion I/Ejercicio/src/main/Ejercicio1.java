package main;

import javax.swing.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        int intentos;
        String password;
        String correctpassword;
        Scanner lector;
        lector = new Scanner(System.in);
        intentos = 1;
        password = "12345678";
        correctpassword = "";
        while ((intentos <= 3) && (!correctpassword.equals(password))) {
            System.out.println("Ingrese la contraseña:");
            correctpassword = lector.nextLine();
            intentos += 1;
        }
        if (intentos <= 4) {
            System.out.println("Bienvenido de vuelta");
        } else {
            System.out.println("Demasiados intentos incorrectos, acceso bloqueado");
        }
    }
}



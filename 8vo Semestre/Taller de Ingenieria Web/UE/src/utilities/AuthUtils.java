package utilities;

import models.User;

import java.util.Scanner;

public class AuthUtils {
    public static User authenticate(Scanner scanner){
        String email = "";
        String password = "";
        while (true){
            System.out.println("Ingrese su correo");
            email = scanner.nextLine();
            System.out.println("Ingrese su contraseña");
            password = scanner.nextLine();
            for (User user: Constants.users) {
                if (user.authenticate(email, password)){
                    return user;
                }
            }
            System.out.println("Credenciales inválidas");
        }
    }
}

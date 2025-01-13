import models.User;
import utilities.AuthUtils;
import utilities.AdminUtils;
import utilities.StudentUtils;
import utilities.TeacherUtils;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciar Sesión");
        User user = AuthUtils.authenticate(scanner);
        System.out.println("Inicio de Sesión Exitoso");
        switch (user.getType()){
            case ADMIN -> AdminUtils.adminLoop(scanner);
            case STUDENT -> StudentUtils.studentLoop(scanner, user.getId());
            case TEACHER -> TeacherUtils.teacherLoop(scanner, user.getId());
            default -> System.out.println("Usuario no implementado");
        }
        System.out.println("Finalizando programa");
    }
}
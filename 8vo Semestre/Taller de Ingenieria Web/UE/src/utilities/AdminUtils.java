package utilities;

import models.Enrollment;
import models.Subject;
import models.User;
import models.UserType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminUtils {
    private static final Map<String, String> adminActions = new LinkedHashMap<>() {{
        put("1", "Agregar Estudiante");
        put("2", "Agregar Profesor");
        put("3", "Agregar Materia");
        put("4", "Listar Estudiantes");
        put("5", "Listar Profesores");
        put("6", "Listar Materias");
        put("7", "Inscribir Estudiante");
    }};
    public static void adminLoop(Scanner scanner){
        String action;
        while (true){
            System.out.println();
            System.out.println("Indique la acción a realizar: ");
            adminActions.forEach((key, value) -> System.out.println(key + " -> " + value));

            action = scanner.nextLine();
            if (action.equals("exit")){
                break;
            }
            if (!adminActions.containsKey(action)){
                System.out.println("La acción especficada no es válida");
                continue;
            }
            switch (action){
                case "1" -> {
                    System.out.println(adminActions.get(action));
                    addUser(UserType.STUDENT, scanner);
                }
                case "2" -> {
                    System.out.println(adminActions.get(action));
                    addUser(UserType.TEACHER, scanner);
                }
                case "3" -> {
                    System.out.println(adminActions.get(action));
                    addSubject(scanner);
                }
                case "4" -> {
                    System.out.println(adminActions.get(action));
                    listUsers(UserType.STUDENT);
                }
                case "5" -> {
                    System.out.println(adminActions.get(action));
                    listUsers(UserType.TEACHER);
                }
                case "6" -> {
                    System.out.println(adminActions.get(action));
                    for (Subject subject: Constants.subjects){
                        System.out.println(subject.getId() + " - " + subject.getName());
                    }
                }
                case "7" -> {
                    System.out.println(adminActions.get(action));
                    enrollStudent(scanner);
                }
            }
        }
    }

    public static void addUser(UserType userType, Scanner scanner){
        System.out.println("Ingrese el nombre: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese el apellido: ");
        String lastName = scanner.nextLine();
        System.out.println("Ingrese el correo: ");
        String email = scanner.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String password = scanner.nextLine();
        Constants.users.add(new User(Constants.users.size() + 1, name, lastName, email, password, userType));
    }

    public static void addSubject(Scanner scanner){
        System.out.println("Ingrese el nombre de la materia: ");
        String name = scanner.nextLine();
        System.out.println("Ingrese el id del profesor: ");
        int teacherId = Integer.parseInt(scanner.nextLine());
        Constants.subjects.add(new Subject(Constants.subjects.size() + 1, name, teacherId));
    }

    public static void listUsers(UserType userType){
        for (User user: Constants.users){
            if (user.getType() == userType){
                System.out.println(user.getId() + " - " + user.getName() + " " + user.getLastName());
            }
        }
    }

    public static void enrollStudent(Scanner scanner){
        System.out.println("Ingrese el id del estudiante: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese el id de la materia: ");
        int subjectId = Integer.parseInt(scanner.nextLine());
        Constants.enrollments.add(new Enrollment(studentId, subjectId, 0));
    }
}

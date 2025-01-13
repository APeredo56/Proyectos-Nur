package utilities;

import models.Enrollment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentUtils {
    private static final Map<String, String> studentActions = new LinkedHashMap<>() {{
        put("1", "Ver Calificaciones");
    }};

    public static void studentLoop(Scanner scanner, int userId){
        String action;
        while (true){
            System.out.println();
            System.out.println("Indique la acción a realizar: ");
            studentActions.forEach((key, value) -> System.out.println(key + " -> " + value));

            action = scanner.nextLine();
            if (action.equals("exit")){
                break;
            }
            if (!studentActions.containsKey(action)){
                System.out.println("La acción especficada no es válida");
                continue;
            }
            switch (action){
                case "1" -> {
                    System.out.println(studentActions.get(action));
                    showGrades(scanner, userId);
                }
                default -> System.out.println("Acción no implementada");
            }
        }
    }

    private static void showGrades(Scanner scanner, int userId){
        System.out.println("Mostrando calificaciones");
        for (Enrollment enrollment: Constants.enrollments){
            if (enrollment.getStudentId() != userId){
                continue;
            }
            System.out.println(Constants.subjects.get(
                    enrollment.getSubjectId()-1).getName() + " -> " + enrollment.getGrade()
            );
        }
    }
}

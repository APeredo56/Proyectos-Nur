package utilities;

import models.Enrollment;
import models.Subject;
import models.User;
import models.UserType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TeacherUtils {
    private static final Map<String, String> teacherActions = new LinkedHashMap<>() {{
        put("1", "Ver Materias");
        put("2", "Ver Estudiantes");
        put("3", "Ver Calificaciones");
        put("4", "Calificar Estudiantes");
    }};

    public static void teacherLoop(Scanner scanner, int userId){
        String action;
        while (true){
            System.out.println();
            System.out.println("Indique la acción a realizar: ");
            teacherActions.forEach((key, value) -> System.out.println(key + " -> " + value));

            action = scanner.nextLine();
            if (action.equals("exit")){
                break;
            }
            if (!teacherActions.containsKey(action)){
                System.out.println("La acción especficada no es válida");
                continue;
            }
            switch (action){
                case "1" -> {
                    System.out.println(teacherActions.get(action));
                    showSubjects(userId);
                }
                case "2" -> {
                    System.out.println(teacherActions.get(action));
                    showStudents();
                }
                case "3" -> {
                    System.out.println(teacherActions.get(action));
                    showGrades(scanner, userId);
                }
                case "4" -> {
                    System.out.println(teacherActions.get(action));
                    gradeStudents(scanner, userId);
                }
                default -> System.out.println("Acción no implementada");
            }
        }
    }

    private static void showSubjects(int teacherId){
        System.out.println("Mostrando materias");
        for (Subject subject: Constants.subjects){
            if (subject.getTeacherId() == teacherId){
                System.out.println(subject.getId() + " - " + subject.getName());
            }
        }
    }

    private static void showStudents(){
        for (User user: Constants.users){
            if (user.getType() == UserType.STUDENT){
                System.out.println(user.getId() + " - " + user.getName() + " " + user.getLastName());
            }
        }
    }

    private static void showGrades(Scanner scanner, int teacherId){
        System.out.println("Seleccione la materia a ver calificaciones");
        for (Subject subject: Constants.subjects){
            if (subject.getTeacherId() == teacherId){
                System.out.println(subject.getId() + " - " + subject.getName());
            }
        }
        int subjectId = Integer.parseInt(scanner.nextLine());
        for (Enrollment enrollment: Constants.enrollments){
            if (enrollment.getSubjectId() == subjectId){
                User student = Constants.users.get(enrollment.getStudentId()-1);
                System.out.println(student.getName() + " " + student.getLastName() + " -> " + enrollment.getGrade());
            }
        }
    }

    private static void gradeStudents(Scanner scanner, int teacherId){
        System.out.println("Seleccione la materia a calificar");
        for (Subject subject: Constants.subjects){
            if (subject.getTeacherId() == teacherId){
                System.out.println(subject.getId() + " - " + subject.getName());
            }
        }
        int subjectId = Integer.parseInt(scanner.nextLine());
        System.out.println("Seleccione el estudiante a calificar");
        for (Enrollment enrollment: Constants.enrollments){
            if (enrollment.getSubjectId() == subjectId){
                User student = Constants.users.get(enrollment.getStudentId()-1);
                System.out.println(student.getId() + " - " + student.getName() + " " + student.getLastName());
            }
        }
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese la calificación");
        int grade = Integer.parseInt(scanner.nextLine());
        for (Enrollment enrollment: Constants.enrollments){
            if (enrollment.getStudentId() == studentId && enrollment.getSubjectId() == subjectId){
                enrollment.setGrade(grade);
            }
        }

    }
}

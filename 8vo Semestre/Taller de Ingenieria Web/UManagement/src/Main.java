import Data.DataManager;
import Model.*;
import Model.Enums.GradeType;
import Model.Enums.GraduateType;
import Model.Enums.StudentType;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Main {
    private static void showGradesConsole(Context ctx) {
        ctx.getStudentService().getAll().forEach(student -> {
            System.out.println(">>>>>>>>>>>>>>>>> Notas del Estudiante <<<<<<<<<<<<<<<<<");
            System.out.println(student.toString());
            ctx.getEnrollmentService().getByStudent(student.getCi()).forEach(enrollment -> {
                System.out.println("* Materia *");
                System.out.println(enrollment.getSubject());
                List<Grade> subjectGrades = ctx.getGradeService().getByStudentAndSubject(
                        student.getCi(),
                        enrollment.getSubject().getCode()
                );
                subjectGrades.forEach(System.out::println);
                System.out.println("Nota Final: ");
                if (enrollment.getSubject().getGradeType() == GradeType.AVERAGE) {
                    System.out.print(ctx.getAverageCalcService().calculateFinalGrade(subjectGrades));
                } else if (enrollment.getSubject().getGradeType() == GradeType.WEIGHTED) {
                    System.out.print(ctx.getWeightedCalcService().calculateFinalGrade(subjectGrades));
                }
                System.out.println();
            });
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println();
        });
    }

    private static void showGradesPDF(Context ctx) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("reporte-notas.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        document.open();
        ctx.getStudentService().getAll().forEach(student -> {
            try {
                document.add(new Paragraph("Reporte de Notas del Estudiante"));

                document.add(new Phrase(student.toString()));

                ctx.getEnrollmentService().getByStudent(student.getCi()).forEach(enrollment -> {
                    Subject enrollmentSubject = enrollment.getSubject();
                    PdfPTable table = new PdfPTable(2);

                    table.addCell("Materia");
                    table.addCell(enrollmentSubject.getName());

                    table.addCell("Profesor");
                    table.addCell(enrollmentSubject.getTeacher().getFullName());

                    table.addCell("Tipo de Evaluacion" );
                    table.addCell(enrollmentSubject.getGradeType().toString());

                    List<Grade> subjectGrades = ctx.getGradeService().getByStudentAndSubject(
                            student.getCi(),
                            enrollment.getSubject().getCode()
                    );
                    table.addCell("Nota");
                    table.addCell("Ponderación");
                    subjectGrades.forEach(grade -> {
                        table.addCell(String.valueOf(grade.getGrade()));
                        table.addCell(String.valueOf(grade.getWeight()));
                    });

                    table.addCell("Nota Final");
                    if (enrollment.getSubject().getGradeType() == GradeType.AVERAGE) {
                        table.addCell(String.valueOf(ctx.getAverageCalcService().calculateFinalGrade(subjectGrades)));
                    } else if (enrollment.getSubject().getGradeType() == GradeType.WEIGHTED) {
                        table.addCell(String.valueOf(ctx.getWeightedCalcService().calculateFinalGrade(subjectGrades)));
                    }

                    try {
                        document.add(table);
                        document.add(new Paragraph("\n"));
                    } catch (DocumentException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
        document.close();
    }

    public static void main(String[] args) {
        DataManager dataManager = new DataManager("mysql");
        Context ctx = new Context(dataManager);

        // Guardado de estudiante normal
        Student regularStudent1 =
                new Student("001", "Normal", "1", "78456555", StudentType.REGULAR);
        Student regularStudent2 =
                new Student("002", "Normal", "2", "77369625", StudentType.REGULAR);
        ctx.getStudentService().save(regularStudent1);
        ctx.getStudentService().save(regularStudent2);

        // Guardado de estudiante de intercambio
        Student exchangeStudent1 =
                new Student("003", "Intercambio", "1", "78456555", StudentType.EXCHANGE);
        Student exchangeStudent2 =
                new Student("004", "Intercambio", "2", "78456555", StudentType.EXCHANGE);
        ctx.getStudentService().save(exchangeStudent1);
        ctx.getStudentService().save(exchangeStudent2);

        // Guardado de estudiante becario
        Student scholarshipStudent1 =
                new Student("005", "Becario", "1", "78456555", StudentType.SCHOLARSHIP);
        Student scholarshipStudent2 =
                new Student("006", "Becario", "2", "78456555", StudentType.SCHOLARSHIP);
        ctx.getStudentService().save(scholarshipStudent1);
        ctx.getStudentService().save(scholarshipStudent2);

        // Guardado de estudiante de postgrado
        Student graduateStudent1 =
                new Student("007", "Postgrado", "1", "78456555", StudentType.GRADUATE);
        Student graduateStudent2 =
                new Student("008", "Postgrado", "2", "78456555", StudentType.GRADUATE);
        ctx.getStudentService().save(graduateStudent1);
        ctx.getStudentService().save(graduateStudent2);

        System.out.println("Estudiantes");
        ctx.getStudentService().getAll().forEach(student -> {
            System.out.println(student.toString());
        });

        //Guardado de tipos de postgrado
        StudentGraduateType studentGraduateType1 =
                new StudentGraduateType("900", graduateStudent1, GraduateType.MASTERS_DEGREE);
        StudentGraduateType studentGraduateType2 =
                new StudentGraduateType("901", graduateStudent2, GraduateType.DIPLOMA);
        ctx.getStudentGraduateTypeService().save(studentGraduateType1);
        ctx.getStudentGraduateTypeService().save(studentGraduateType2);

        //Guardado de profesores
        Teacher teacher1 = new Teacher("100", "Profesor", "1", "78456555");
        Teacher teacher2 = new Teacher("101", "Profesor", "2", "78456555");
        ctx.getTeacherService().save(teacher1);
        ctx.getTeacherService().save(teacher2);

        System.out.println("Profesores");
        ctx.getTeacherService().getAll().forEach(teacher -> {
            System.out.println(teacher.toString());
        });

        //Guardado de materias
        Subject subject1 = new Subject("200", "Matematicas", GradeType.AVERAGE, teacher1);
        Subject subject2 = new Subject("201", "Fisica", GradeType.AVERAGE, teacher1);
        Subject subject3 = new Subject("202", "Quimica", GradeType.WEIGHTED, teacher2);
        ctx.getSubjectService().save(subject1);
        ctx.getSubjectService().save(subject2);
        ctx.getSubjectService().save(subject3);

        //Asignacion de Estudiantes a materias
        Enrollment enrollment1 = new Enrollment("300", subject1, regularStudent1);
        Enrollment enrollment2 = new Enrollment("301", subject1, regularStudent2);
        Enrollment enrollment3 = new Enrollment("302", subject1, exchangeStudent1);
        Enrollment enrollment4 = new Enrollment("303", subject1, scholarshipStudent1);
        Enrollment enrollment5 = new Enrollment("304", subject3, regularStudent1);
        Enrollment enrollment6 = new Enrollment("305", subject3, regularStudent2);
        Enrollment enrollment7 = new Enrollment("306", subject3, exchangeStudent1);
        Enrollment enrollment8 = new Enrollment("307", subject3, scholarshipStudent1);
        ctx.getEnrollmentService().save(enrollment1);
        ctx.getEnrollmentService().save(enrollment2);
        ctx.getEnrollmentService().save(enrollment3);
        ctx.getEnrollmentService().save(enrollment4);
        ctx.getEnrollmentService().save(enrollment5);
        ctx.getEnrollmentService().save(enrollment6);
        ctx.getEnrollmentService().save(enrollment7);
        ctx.getEnrollmentService().save(enrollment8);

        //Asignacion de notas
        Grade grade1 = new Grade("400", regularStudent1, subject1, 90, 0);
        Grade grade2 = new Grade("401", regularStudent1, subject1, 90, 0);
        Grade grade3 = new Grade("402", regularStudent1, subject1, 80, 0);
        Grade grade4 = new Grade("403", regularStudent1, subject1, 80, 0);
        Grade grade5 = new Grade("404", regularStudent1, subject3, 90, 0.1);
        Grade grade6 = new Grade("405", regularStudent1, subject3, 80, 0.1);
        Grade grade7 = new Grade("406", regularStudent1, subject3, 80, 0.3);
        Grade grade8 = new Grade("407", regularStudent1, subject3, 80, 0.5);
        ctx.getGradeService().save(grade1);
        ctx.getGradeService().save(grade2);
        ctx.getGradeService().save(grade3);
        ctx.getGradeService().save(grade4);
        ctx.getGradeService().save(grade5);
        ctx.getGradeService().save(grade6);
        ctx.getGradeService().save(grade7);
        ctx.getGradeService().save(grade8);

        //Reporte de Notas
        showGradesConsole(ctx);
        showGradesPDF(ctx);

        System.out.println(ctx.getStudentGraduateTypeService().getAll());
    }
}
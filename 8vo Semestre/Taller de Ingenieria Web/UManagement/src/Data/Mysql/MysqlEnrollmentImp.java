package Data.Mysql;

import Data.IDataBaseEnrollment;
import Data.IDataBaseGrade;
import Model.*;
import Model.Enums.GradeType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlEnrollmentImp implements IDataBaseEnrollment {
    private MySQLConnection connection;

    public MysqlEnrollmentImp() {
        connection = MySQLConnection.getInstance();
    }

    @Override
    public void save(Enrollment enrollment) {
        try {
            connection.connect();
            String query = "INSERT INTO Enrollment (code, subject_code, student_ci) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(enrollment.getCode()));
            preparedStatement.setString(2, enrollment.getSubject().getCode());
            preparedStatement.setString(3, enrollment.getStudent().getCi());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Enrollment> getAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            String query = "SELECT e.code, s.code AS subject_code, s.name AS subject_name, s.gradeType as subject_gradeType, " +
                    "st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, " +
                    "t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Enrollment e " +
                    "JOIN Subject s ON e.subject_code = s.code " +
                    "JOIN Student st ON e.student_ci = st.ci " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci";  // Cambiado a JOIN con teacher_ci
            ResultSet rs = connection.query(statement, query);

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setCode(rs.getString("code"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("subject_gradeType")));

                // Obtener el profesor asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));  // Cambiado a teacher_ci
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);  // Asignar el profesor a la materia

                enrollment.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                enrollment.setStudent(student);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return enrollments;
    }

    @Override
    public Enrollment getByCode(String code) {
        Enrollment enrollment = null;
        try {
            connection.connect();
            String query = "SELECT e.code, s.code AS subject_code, s.name AS subject_name, s.gradeType as subject_gradeType, " +
                    "st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, " +
                    "t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Enrollment e " +
                    "JOIN Subject s ON e.subject_code = s.code " +
                    "JOIN Student st ON e.student_ci = st.ci " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci " +  // Cambiado a JOIN con teacher_ci
                    "WHERE e.code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                enrollment = new Enrollment();
                enrollment.setCode(rs.getString("code"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("subject_gradeType")));

                // Obtener el profesor asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));  // Cambiado a teacher_ci
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);  // Asignar el profesor a la materia

                enrollment.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                enrollment.setStudent(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return enrollment;
    }

    @Override
    public List<Enrollment> getByStudent(String studentCi) {
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            connection.connect();
            String query = "SELECT e.code, s.code AS subject_code, s.name AS subject_name, s.gradeType as subject_gradeType, " +
                    "st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, " +
                    "t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Enrollment e " +
                    "JOIN Subject s ON e.subject_code = s.code " +
                    "JOIN Student st ON e.student_ci = st.ci " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci " +  // Cambiado a JOIN con teacher_ci
                    "WHERE e.student_ci = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, studentCi);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setCode(rs.getString("code"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("subject_gradeType")));

                // Obtener el profesor asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));  // Cambiado a teacher_ci
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);  // Asignar el profesor a la materia

                enrollment.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                enrollment.setStudent(student);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return enrollments;
    }

    @Override
    public List<Enrollment> getBySubject(String subjectCode) {
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            connection.connect();
            String query = "SELECT e.code, s.code AS subject_code, s.name AS subject_name, s.gradeType as subject_gradeType, " +
                    "st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, " +
                    "t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Enrollment e " +
                    "JOIN Subject s ON e.subject_code = s.code " +
                    "JOIN Student st ON e.student_ci = st.ci " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci " +  // Cambiado a JOIN con teacher_ci
                    "WHERE e.subject_code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, subjectCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setCode(rs.getString("code"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("subject_gradeType")));

                // Obtener el profesor asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));  // Cambiado a teacher_ci
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);  // Asignar el profesor a la materia

                enrollment.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                enrollment.setStudent(student);

                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return enrollments;
    }
}

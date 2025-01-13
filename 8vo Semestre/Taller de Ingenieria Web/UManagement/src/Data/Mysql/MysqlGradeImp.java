package Data.Mysql;

import Data.IDataBaseGrade;
import Data.IDataBaseTeacher;
import Model.Grade;
import Model.Student;
import Model.Subject;
import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlGradeImp implements IDataBaseGrade {
    private MySQLConnection connection;

    public MysqlGradeImp() {
        connection = MySQLConnection.getInstance();
    }


    @Override
    public void save(Grade grade) {
        try {
            connection.connect();
            String query = "INSERT INTO Grade (code, student_ci, subject_code, grade, weight) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(grade.getCode()));
            preparedStatement.setString(2, grade.getStudent().getCi());
            preparedStatement.setString(3, grade.getSubject().getCode());
            preparedStatement.setInt(4, grade.getGrade());
            preparedStatement.setDouble(5, grade.getWeight());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Grade> getAll() {
        List<Grade> grades = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            String query = "SELECT g.code, g.grade, g.weight, s.code AS subject_code, s.name AS subject_name, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone " +
                    "FROM Grade g " +
                    "JOIN Subject s ON g.subject_code = s.code " +
                    "JOIN Student st ON g.student_ci = st.ci";
            ResultSet rs = connection.query(statement, query);

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setCode(rs.getString("code"));
                grade.setGrade(rs.getInt("grade"));
                grade.setWeight(rs.getDouble("weight"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));

                grade.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                grade.setStudent(student);

                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return grades;
    }

    @Override
    public Grade getByCode(String code) {
        Grade grade = null;
        try {
            connection.connect();
            String query = "SELECT g.code, g.grade, g.weight, s.code AS subject_code, s.name AS subject_name, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone " +
                    "FROM Grade g " +
                    "JOIN Subject s ON g.subject_code = s.code " +
                    "JOIN Student st ON g.student_ci = st.ci " +
                    "WHERE g.code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                grade = new Grade();
                grade.setCode(rs.getString("code"));
                grade.setGrade(rs.getInt("grade"));
                grade.setWeight(rs.getDouble("weight"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));

                grade.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                grade.setStudent(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return grade;
    }

    @Override
    public List<Grade> getByStudentAndSubject(String studentCi, String subjectCode) {
        List<Grade> grades = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            String query = "SELECT g.code, g.grade, g.weight, s.code AS subject_code, s.name AS subject_name, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone " +
                    "FROM Grade g " +
                    "JOIN Subject s ON g.subject_code = s.code " +
                    "JOIN Student st ON g.student_ci = st.ci " +
                    "WHERE g.student_ci = ? AND g.subject_code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, studentCi);
            preparedStatement.setString(2, subjectCode);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setCode(rs.getString("code"));
                grade.setGrade(rs.getInt("grade"));
                grade.setWeight(rs.getDouble("weight"));

                // Obtener la materia asociada
                Subject subject = new Subject();
                subject.setCode(rs.getString("subject_code"));
                subject.setName(rs.getString("subject_name"));

                grade.setSubject(subject);

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                grade.setStudent(student);

                grades.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return grades;
    }
}

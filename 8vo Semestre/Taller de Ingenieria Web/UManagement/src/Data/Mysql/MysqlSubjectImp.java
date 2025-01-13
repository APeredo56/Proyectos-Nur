package Data.Mysql;

import Data.IDataBaseSubject;
import Model.Enums.GradeType;
import Model.Subject;
import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MysqlSubjectImp implements IDataBaseSubject {
    private MySQLConnection connection;

    public MysqlSubjectImp() {
        connection = MySQLConnection.getInstance();
    }

    @Override
    public void save(Subject subject) {
        try {
            connection.connect();
            String query = "INSERT INTO Subject (code, name, teacher_ci, gradeType) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(subject.getCode()));
            preparedStatement.setString(2, subject.getName());
            preparedStatement.setString(3, subject.getTeacher().getCi());
            preparedStatement.setString(4, subject.getGradeType().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            String query = "SELECT s.code, s.name, s.gradeType, t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Subject s " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci";
            ResultSet rs = connection.query(statement, query);

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("gradeType")));

                // Obtener el docente asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);

                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return subjects;
    }

    @Override
    public Subject getByCode(String code) {
        Subject subject = null;
        try {
            connection.connect();
            String query = "SELECT s.code, s.name, s.gradeType, t.ci AS teacher_ci, t.name AS teacher_name, t.lastName AS teacher_lastName, t.phone AS teacher_phone " +
                    "FROM Subject s " +
                    "JOIN Teacher t ON s.teacher_ci = t.ci " +
                    "WHERE s.code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCode(rs.getString("code"));
                subject.setName(rs.getString("name"));
                subject.setGradeType(GradeType.valueOf(rs.getString("gradeType")));

                // Obtener el docente asociado
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("teacher_ci"));
                teacher.setName(rs.getString("teacher_name"));
                teacher.setLastName(rs.getString("teacher_lastName"));
                teacher.setPhone(rs.getString("teacher_phone"));

                subject.setTeacher(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return subject;
    }

}

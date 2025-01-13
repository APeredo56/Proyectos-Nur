package Data.Mysql;

import Data.IDataBaseStudent;
import Model.Enums.StudentType;
import Model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MysqlStudentImp implements IDataBaseStudent {
    private MySQLConnection connection;

    public MysqlStudentImp() {
        connection = MySQLConnection.getInstance();
    }

    @Override
    public void save(Student student) {
        try {
            connection.connect();
            String query = "INSERT INTO Student (ci, name, lastName, phone, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(student.getCi()));
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getType().name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            ResultSet rs = connection.query(statement, "SELECT * FROM Student");

            while (rs.next()) {
                Student student = new Student();
                student.setCi(rs.getString("ci"));
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("lastName"));
                student.setPhone(rs.getString("phone"));
                student.setType(StudentType.valueOf(rs.getString("type")));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return students;
    }

    @Override
    public Student getByCode(String code) {
        Student student = null;
        try {
            connection.connect();
            String query = "SELECT * FROM Student WHERE ci = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setCi(rs.getString("ci"));
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("lastName"));
                student.setPhone(rs.getString("phone"));
                student.setType(StudentType.valueOf(rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return student;
    }

    public List<Student> getByType(StudentType studentType) {
        List<Student> students = new ArrayList<>();
        try {
            connection.connect();
            String query = "SELECT * FROM Student WHERE ci = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, studentType.name());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setCi(rs.getString("ci"));
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("lastName"));
                student.setPhone(rs.getString("phone"));
                student.setType(StudentType.valueOf(rs.getString("type")));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return students;
    }
}

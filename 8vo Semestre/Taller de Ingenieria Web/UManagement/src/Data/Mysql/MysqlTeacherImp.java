package Data.Mysql;

import Data.IDataBaseTeacher;
import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlTeacherImp implements IDataBaseTeacher {
    private MySQLConnection connection;

    public MysqlTeacherImp() {
        connection = MySQLConnection.getInstance();
    }

    @Override
    public void save(Teacher model) {
        try {
            connection.connect();
            String query = "INSERT INTO Teacher (ci, name, lastName, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(model.getCi()));
            preparedStatement.setString(2, model.getName());
            preparedStatement.setString(3, model.getLastName());
            preparedStatement.setString(4, model.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            ResultSet rs = connection.query(statement, "SELECT * FROM Teacher");

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setCi(rs.getString("ci"));
                teacher.setName(rs.getString("name"));
                teacher.setLastName(rs.getString("lastName"));
                teacher.setPhone(rs.getString("phone"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return teachers;
    }

    @Override
    public Teacher getByCode(String code) {
        Teacher teacher = null;
        try {
            connection.connect();
            String query = "SELECT * FROM Teacher WHERE ci = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                teacher = new Teacher();
                teacher.setCi(rs.getString("ci"));
                teacher.setName(rs.getString("name"));
                teacher.setLastName(rs.getString("lastName"));
                teacher.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return teacher;
    }
}

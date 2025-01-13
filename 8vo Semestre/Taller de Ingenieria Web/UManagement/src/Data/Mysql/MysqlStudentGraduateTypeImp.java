package Data.Mysql;

import Data.IDataBaseGrade;
import Data.IDataBaseStudentGraduateType;
import Model.Enums.GraduateType;
import Model.Grade;
import Model.Student;
import Model.StudentGraduateType;
import utilities.GraduateTypeUtilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlStudentGraduateTypeImp implements IDataBaseStudentGraduateType {
    private MySQLConnection connection;

    public MysqlStudentGraduateTypeImp() {
        connection = MySQLConnection.getInstance();
    }


    @Override
    public void save(StudentGraduateType studentGraduateType) {
        try {
            connection.connect();
            String query = "INSERT INTO StudentGraduateType (code, student_ci, graduateType) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(studentGraduateType.getCode()));
            preparedStatement.setString(2, studentGraduateType.getStudent().getCi());
            preparedStatement.setInt(
                    3,
                    GraduateTypeUtilities.getGraduateTypeForSave(studentGraduateType.getGraduateType())
            );

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public List<StudentGraduateType> getAll() {
        List<StudentGraduateType> studentGraduateTypes = new ArrayList<>();
        try {
            connection.connect();
            Statement statement = connection.getConexion().createStatement();
            String query = "SELECT sgt.code, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, gt.id AS graduateType_id, gt.name AS graduateType_name " +
                    "FROM StudentGraduateType sgt " +
                    "JOIN Student st ON sgt.student_ci = st.ci " +
                    "JOIN GraduateType gt ON sgt.graduateType = gt.id";
            ResultSet rs = connection.query(statement, query);

            while (rs.next()) {
                StudentGraduateType studentGraduateType = new StudentGraduateType();
                studentGraduateType.setCode(rs.getString("code"));

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                studentGraduateType.setStudent(student);

                // Obtener el tipo de posgrado asociado
                GraduateType graduateType =
                        GraduateTypeUtilities.getGraduateTypeForDisplay(rs.getInt("graduateType_id"));
                studentGraduateType.setGraduateType(graduateType);

                studentGraduateTypes.add(studentGraduateType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return studentGraduateTypes;
    }

    @Override
    public StudentGraduateType getByCode(String code) {
        StudentGraduateType studentGraduateType = null;
        try {
            connection.connect();
            String query = "SELECT sgt.code, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, gt.id AS graduateType_id, gt.name AS graduateType_name " +
                    "FROM StudentGraduateType sgt " +
                    "JOIN Student st ON sgt.student_ci = st.ci " +
                    "JOIN GraduateType gt ON sgt.graduateType = gt.id " +
                    "WHERE sgt.code = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                studentGraduateType = new StudentGraduateType();
                studentGraduateType.setCode(rs.getString("code"));

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                studentGraduateType.setStudent(student);

                // Obtener el tipo de posgrado asociado
                GraduateType graduateType =
                        GraduateTypeUtilities.getGraduateTypeForDisplay(rs.getInt("graduateType_id"));
                studentGraduateType.setGraduateType(graduateType);

                studentGraduateType.setGraduateType(graduateType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return studentGraduateType;
    }

    @Override
    public StudentGraduateType getByStudent(String studentCi) {
        StudentGraduateType studentGraduateType = null;
        try {
            connection.connect();
            String query = "SELECT sgt.code, st.ci AS student_ci, st.name AS student_name, st.lastName AS student_lastName, st.phone AS student_phone, gt.id AS graduateType_id, gt.name AS graduateType_name " +
                    "FROM StudentGraduateType sgt " +
                    "JOIN Student st ON sgt.student_ci = st.ci " +
                    "JOIN GraduateType gt ON sgt.graduateType = gt.id " +
                    "WHERE sgt.student_ci = ?";
            PreparedStatement preparedStatement = connection.getConexion().prepareStatement(query);
            preparedStatement.setString(1, studentCi);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                studentGraduateType = new StudentGraduateType();
                studentGraduateType.setCode(rs.getString("code"));

                // Obtener el estudiante asociado
                Student student = new Student();
                student.setCi(rs.getString("student_ci"));
                student.setName(rs.getString("student_name"));
                student.setLastName(rs.getString("student_lastName"));
                student.setPhone(rs.getString("student_phone"));

                studentGraduateType.setStudent(student);

                // Obtener el tipo de posgrado asociado
                GraduateType graduateType =
                        GraduateTypeUtilities.getGraduateTypeForDisplay(rs.getInt("graduateType_id"));
                studentGraduateType.setGraduateType(graduateType);

                studentGraduateType.setGraduateType(graduateType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return studentGraduateType;
    }
}

package Data.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB = "run";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL+DB, USER, PASSWORD);
    }

}

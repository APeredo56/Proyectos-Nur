package Data.Mysql;

import java.sql.*;

public class MySQLConnection {
        public static final String URL = "jdbc:mysql://localhost:3306/umanagement";
        public static final String USER = "root";
        public static final String PASSWORD = "root";

        private static MySQLConnection instance;

        private Connection connection;

        private MySQLConnection() {
        }

        public static MySQLConnection getInstance() {
            if (instance == null) {
                instance = new MySQLConnection();
            }
            return instance;
        }

        public void connect() throws SQLException {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public Connection getConexion() {
            return connection;
        }

        public void disconnect()  {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ResultSet query(Statement sentencia, String sql) throws SQLException {
            return sentencia.executeQuery(sql);
        }

        public void execute(Statement sentencia, String sql) throws SQLException {
            sentencia.execute(sql);
        }

}

import java.sql.*;
public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jommasak";
    private static final String USER = "root";
    private static final String PASS = "Iman@1207";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}

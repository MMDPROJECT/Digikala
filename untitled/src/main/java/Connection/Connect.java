package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("GrazieInspection")
public class Connect {
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Shop.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to Shop's database has been established!\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
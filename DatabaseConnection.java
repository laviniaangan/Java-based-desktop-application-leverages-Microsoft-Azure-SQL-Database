import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://sap3.database.windows.net:1433;databaseName=GestionareProduse; encrypt=true;trustServerCertificate=true";
    private static final String USER = "lavinia";
    private static final String PASSWORD = "Marilena20";

    public static Connection getConnection() {
        try {
            // Load driver (optional for newer Java versions)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            
            // Return the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Nu s-a putut stabili conexiunea cu baza de date!", e);
        }
    }
}

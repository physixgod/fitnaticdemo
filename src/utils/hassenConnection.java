

package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class hassenConnection {
    private static final String url = "jdbc:mysql://localhost:3306/fitnatic"; // Replace with your database URL
    private static final String login = "root";
    private static final String pwd = "";
    private Connection connection;
    private static hassenConnection instance; 
  

    private hassenConnection() {
        try {
        connection = DriverManager.getConnection(url, login, pwd);
            System.out.println("Database connection established!");
        } catch (SQLException ex) {
            System.out.println("Error while connecting to the database: " + ex.getMessage());
        }// Private constructor to prevent instantiation.
    }

    // Create and return a connection to the database
    public  Connection getConnection() {
         try {
            // Check if the connection is still valid before returning it
            if (connection == null || connection.isClosed()) {
                System.out.println("Connection is closed; reopening...");
                connection = DriverManager.getConnection(url, login, pwd);
            }
        } catch (SQLException ex) {
            System.out.println("Error while reopening the database connection: " + ex.getMessage());
        }
        return  connection;
    }


    public static hassenConnection getInstance() {
        if (instance == null) {
            instance = new hassenConnection();
        }
        return instance;
    }

}

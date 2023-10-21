package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private String url = "jdbc:mysql://localhost:3306/fitnatic";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static MyConnection instance;

    // Private constructor to prevent instantiation from outside
    public MyConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Database connection established!");
        } catch (SQLException ex) {
            System.out.println("Error while connecting to the database: " + ex.getMessage());
        }
    }

    public Connection getCnx() {
        try {
            // Check if the connection is still valid before returning it
            if (cnx == null || cnx.isClosed()) {
                System.out.println("Connection is closed; reopening...");
                cnx = DriverManager.getConnection(url, login, pwd);
            }
        } catch (SQLException ex) {
            System.out.println("Error while reopening the database connection: " + ex.getMessage());
        }
        return cnx;
    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
}

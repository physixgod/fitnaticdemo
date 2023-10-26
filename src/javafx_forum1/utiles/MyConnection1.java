/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oumayma
 */
public class MyConnection1 {
        String url="jdbc:mysql://localhost:3306/fitnatic";
    String login="root";
    String pwd="";
    private Connection cnx;
    private static MyConnection1 instance;

    private MyConnection1() {
        try {
         cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection1 getInstance(){
        if(instance == null){
            instance = new MyConnection1();
        }
         return instance;
    }

  
    
    
}

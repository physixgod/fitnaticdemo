/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MyConnectiona {

    
    public String url="jdbc:mysql://localhost:3306/fitnatic";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnectiona instance;
    
    
    private MyConnectiona(){
        try {
           cnx = DriverManager.getConnection(url, login, pwd);
           System.out.println("Connection etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

public static MyConnectiona getInstance(){
if (instance == null){
instance = new MyConnectiona();
} 

return instance;
}

   
    
}

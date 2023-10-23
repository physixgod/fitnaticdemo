/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.MyConnection;

/**
 *
 * @author toufa
 */
public class inscriptionCRUD {
public void insertInscription(String email, String competition) {
    String evaluation = "Not evaluated yet";

    try {
        String query = "INSERT INTO Inscription (email, competition, evaluation) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, competition);
        preparedStatement.setString(3, evaluation);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Insertion successful");
        } else {
            System.out.println("Insertion failed");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}

package edu.esprit.services;

import edu.esprit.entities.Calendrier;
import edu.esprit.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalendrierCRUD {
    Connection connection = MyConnection.getInstance().getCnx();
    public void ajouterCalendrier(Calendrier calendrier) {
        String insertSQL = "INSERT INTO calendrier (lundi, mardi, mercredi, jeudi, vendredi, samedi) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, calendrier.getLundi());
            preparedStatement.setString(2, calendrier.getMardi());
            preparedStatement.setString(3, calendrier.getMercredi());
            preparedStatement.setString(4, calendrier.getJeudi());
            preparedStatement.setString(5, calendrier.getVendredi());
            preparedStatement.setString(6, calendrier.getSamedi());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted into Calendrier successfully.");
            } else {
                System.out.println("Data insertion into Calendrier failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierCalendrier(Calendrier calendrier,int id) {
        String updateSQL = "UPDATE calendrier SET lundi = ?, mardi = ?, mercredi = ?, jeudi = ?, vendredi = ?, samedi = ? WHERE id = ?";

        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, calendrier.getLundi());
            preparedStatement.setString(2, calendrier.getMardi());
            preparedStatement.setString(3, calendrier.getMercredi());
            preparedStatement.setString(4, calendrier.getJeudi());
            preparedStatement.setString(5, calendrier.getVendredi());
            preparedStatement.setString(6, calendrier.getSamedi());
            preparedStatement.setInt(7, calendrier.getId()); // Utilisez l'ID pour identifier l'enregistrement à mettre à jour

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data updated in Calendrier successfully.");
            } else {
                System.out.println("Data update in Calendrier failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

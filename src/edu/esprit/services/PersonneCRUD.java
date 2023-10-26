package edu.esprit.services;

import edu.esprit.entities.Personne;
import edu.esprit.utils.MyConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PersonneCRUD {
    public void ajouterPersonne(Personne p) throws SQLException {
        String insertSQL = "INSERT INTO personne (nom, prenom, email, genre, dateNaissance, motDePasse, motDePasse2) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            
            LocalDate localDate = p.getDateNaissance();
            Date sqlDate = Date.valueOf(localDate);
            
            preparedStatement.setString(1, p.getNom());
            preparedStatement.setString(2, p.getPrenom());
            preparedStatement.setString(3, p.getEmail());
            preparedStatement.setString(4, p.getGenre());
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setString(6, p.getMotDePasse()); 
            preparedStatement.setString(7, p.getMotDePasse2()); 
            
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Données insérées avec succès.");
            } else {
                System.out.println("Échec de l'insertion des données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Personne authentifier(String email, String motDePasse) throws SQLException {
        String selectSQL = "SELECT * FROM personne WHERE email = ? AND motDePasse = ?";
        
        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // L'utilisateur est authentifié, construisez et renvoyez un objet Personne
                Personne personne = new Personne();
                personne.setId(resultSet.getInt("id"));
                personne.setNom(resultSet.getString("nom"));
                personne.setPrenom(resultSet.getString("prenom"));
                personne.setEmail(resultSet.getString("email"));
                personne.setGenre(resultSet.getString("genre"));
                // Assurez-vous d'ajouter les autres attributs ici...
                
                return personne;
            } else {
                // L'authentification a échoué
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

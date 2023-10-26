/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx_forum1.entities.Evenement;
import javafx_forum1.entities.EventTableView;
import javafx_forum1.interfaces.EvenementInterface;
import javafx_forum1.utiles.MyConnection1;

/**
 *
 * @author OUMAIMA
 */
public class EvenementService implements EvenementInterface{
     MyConnection1 cnx; 

    public EvenementService() {
        //cnx = (MyConnection1) MyConnection1.getInstance().getCnx();
    }
     private static final String INSERT_EVENEMENT_SQL = "INSERT INTO evenement (date_evenement, choix_evenement, description_evenement) VALUES (?, ?, ?)";
    private static final String SELECT_EVENEMENT_BY_ID_SQL = "SELECT * FROM evenement WHERE id = ?";
    private static final String UPDATE_EVENEMENT_SQL = "UPDATE evenement SET date_evenement = ?, choix_evenement = ?, description_evenement = ? WHERE id = ?";
    private static final String DELETE_EVENEMENT_SQL = "DELETE FROM evenement WHERE id = ?";
    private static final String SELECT_ALL_EVENEMENTS_SQL = "SELECT * FROM evenement"; 
    
    @Override
    public void creerEvenement(Evenement evenement) {
    
     try (
             PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
                                    .prepareStatement(INSERT_EVENEMENT_SQL)) {
            preparedStatement.setDate(1, new java.sql.Date(evenement.getDateEvenement().getTime()));
            preparedStatement.setString(2, evenement.getChoixEvenement());
            preparedStatement.setString(3, evenement.getDescriptionEvenement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }

    @Override
public Evenement getEvenementById(int id) {
    Evenement evenement = null;
    try (PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
            .prepareStatement(SELECT_EVENEMENT_BY_ID_SQL)) {
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            evenement = new Evenement();
            evenement.setId(resultSet.getInt("id"));
            evenement.setDateEvenement(resultSet.getDate("date_evenement"));
            evenement.setChoixEvenement(resultSet.getString("choix_evenement"));
            evenement.setDescriptionEvenement(resultSet.getString("description_evenement"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return evenement;
}


@Override
public void mettreAJourEvenement(Evenement evenement) {
    try (PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
            .prepareStatement(UPDATE_EVENEMENT_SQL)) {
        preparedStatement.setDate(1, new java.sql.Date(evenement.getDateEvenement().getTime()));
        preparedStatement.setString(2, evenement.getChoixEvenement());
        preparedStatement.setString(3, evenement.getDescriptionEvenement());
        preparedStatement.setInt(4, evenement.getId());
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

@Override
public void supprimerEvenement(int id) {
    try (PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
            .prepareStatement(DELETE_EVENEMENT_SQL)) {
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  @Override
public List<Evenement> getAllEvenements() {
    List<Evenement> evenements = new ArrayList<>();
    try (PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
            .prepareStatement(SELECT_ALL_EVENEMENTS_SQL)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Evenement evenement = new Evenement();
            evenement.setId(resultSet.getInt("id"));
            evenement.setDateEvenement(resultSet.getDate("date_evenement"));
            evenement.setChoixEvenement(resultSet.getString("choix_evenement"));
            evenement.setDescriptionEvenement(resultSet.getString("description_evenement"));
            evenements.add(evenement);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return evenements;

}

     public List<EventTableView> getAllEvenementsEventTableView() {
    List<EventTableView> evenements = new ArrayList<>();
    try (PreparedStatement preparedStatement = MyConnection1.getInstance().getCnx()
            .prepareStatement(SELECT_ALL_EVENEMENTS_SQL)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            EventTableView evenement = new EventTableView();
            evenement.setId(resultSet.getInt("id"));
            evenement.setDateEvenement(resultSet.getDate("date_evenement"));
            evenement.setChoixEvenement(resultSet.getString("choix_evenement"));
            evenement.setDescriptionEvenement(resultSet.getString("description_evenement"));
            evenements.add(evenement);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return evenements;

}

}
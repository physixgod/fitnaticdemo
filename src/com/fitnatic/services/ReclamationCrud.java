/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.services;

import com.fitnatic.entities.Reclamation;
import com.fitnatic.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 *
 * @author Youssef
 */
public class ReclamationCrud {
    
     MyConnection cnx =MyConnection.getInstance();
        Connection myconx = cnx.getCnx();
    public void ajouterReclamation(Reclamation r){
     
        LocalDate date = r.getDate(); // Supposons que r.getDate() renvoie une java.util.Date           
       
            try {
        String requete = "INSERT INTO reclamation (userId, date, description) VALUES (?, ?, ?)";
            PreparedStatement st = myconx.prepareStatement(requete);
            
            //st.setInt(1, r.getReclamationId());
            st.setInt(1, r.getUserId());
            //st.setDate(2, (java.sql.Date) sqldate);
            //java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            st.setDate(2, sqlDate);
            st.setString(3, r.getDescription());

            st.executeUpdate();

            // Affichez un message de confirmation si nécessaire
            System.out.println("Réclamation ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      

    }
           /* try {
        String requete = "INSERT INTO reclamation (userId, date, description) VALUES (?, ?, ?)";
                LocalDate date = r.getDate(); // Supposons que r.getDate() renvoie une java.util.Date       
            Date sqldate=Date.valueOf(LocalDate);
            ResultSet resultset =st.executeQuery();
            if(resultset.next()){
            PreparedStatement st = myconx.prepareStatement(requete);

            st.setInt(1, r.getUserId());
            st.setDate(2, (java.sql.Date) sqldate);
            st.setString(3, r.getDescription());

            st.executeUpdate();
            }
            // Affichez un message de confirmation si nécessaire
          //  System.out.println("Réclamation ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
    

    public void ShowReclamation(TableView<Reclamation> tab_rec, TableColumn<Reclamation, Integer> id_col, 
        TableColumn<Reclamation, String> date_col, TableColumn<Reclamation, String> desc_col) throws SQLException {

}


      
      /*public List<Reclamation> afficherReclamation(){
          return null;
      } */ 
      
   // Méthode pour mettre à jour une réclamation
        public void modifierReclamation(int reclamationId, String nouvelleDescription) {
    String requete = "UPDATE reclamation SET description = ? WHERE reclamationId = ?";
    try {
        Connection connection = new MyConnection().getCnx();
        PreparedStatement preparedStatement = connection.prepareStatement(requete);
        preparedStatement.setString(1, nouvelleDescription);
        preparedStatement.setInt(2, reclamationId);
        int rowsAffected = preparedStatement.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Réclamation modifiée avec succès !");
        } else {
            System.out.println("Aucune réclamation trouvée avec l'ID spécifié.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}    

        public void supprimerReclamation(int reclamationId) {
    String requete = "DELETE FROM reclamation WHERE reclamationId = ?";
    try {
        
        MyConnection cnx =MyConnection.getInstance();
        Connection myconx = cnx.getCnx();
        PreparedStatement st = myconx.prepareStatement(requete);
        st.setInt(1, reclamationId);
        int rowsAffected = st.executeUpdate();
        
        
        if (rowsAffected > 0) {
            System.out.println("Réclamation supprimée avec succès !");
        } else {
            System.out.println("Aucune réclamation trouvée avec l'ID spécifié.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
            



}
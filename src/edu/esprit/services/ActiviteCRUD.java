/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Activite;
import edu.esprit.utils.MyConnectiona;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.util.List;

/**
 *
 * @author ASUS
 */
public class ActiviteCRUD {
    
   Connection cnx; 
   public ActiviteCRUD(){
   cnx = MyConnectiona.getInstance().getCnx();
   }
   

    public void ajouterActivite(Activite activite) {
        try {
            String requete = "INSERT INTO activite (nom, type, dateDebut, dateFin, duree, notes) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = cnx.prepareStatement(requete);

            st.setString(1, activite.getNom());
            st.setString(2, activite.getType());
            st.setDate(3, new java.sql.Date(activite.getDateDebut().getTime()));
            st.setDate(4, new java.sql.Date(activite.getDateFin().getTime()));
            st.setInt(5, activite.getDuree());
            st.setString(6, activite.getNotes());

            st.executeUpdate();

            System.out.println("Activité ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
}
    
 public ObservableList<Activite> rechercherActivites() {
        ObservableList<Activite> activites = FXCollections.observableArrayList(); ;

        try {
            String requete = "SELECT * FROM activite";
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String type = rs.getString("type");
                Date dateDebut = rs.getDate("dateDebut");
                Date dateFin = rs.getDate("dateFin");
                int duree = rs.getInt("duree");
                String notes = rs.getString("notes");

                Activite activite = new Activite(id, nom, type, dateDebut, dateFin, duree, notes);
                activites.add(activite);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return activites;
    }
  
    
    
    
    
public void modifierActivite(int id, Activite newActivite) {
        try {
            String requete = "UPDATE activite SET nom=?, type=?, dateDebut=?, dateFin=?, duree=?, notes=? WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(requete);

            st.setString(1, newActivite.getNom());
            st.setString(2, newActivite.getType());
            st.setDate(3, new java.sql.Date(newActivite.getDateDebut().getTime()));
            st.setDate(4, new java.sql.Date(newActivite.getDateFin().getTime()));
            st.setInt(5, newActivite.getDuree());
            st.setString(6, newActivite.getNotes());
            st.setInt(7, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Activité modifiée avec succès !");
            } else {
                System.out.println("Aucune activité trouvée avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    

   public void supprimerActivite(int id) {
    try {
        String requete = "DELETE FROM activite WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(requete);
        st.setInt(1, id);

        int rowsAffected = st.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Activité supprimée avec succès !");
        } else {
            System.out.println("Aucune activité trouvée avec l'ID spécifié.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
 
    
   
   
   public List<Activite> afficherActivite(){
          List<Activite> activites = rechercherActivites(); 

    if (activites.isEmpty()) {
        System.out.println("No activities found.");
    } else {
        System.out.println("List of Activities:");

        for (Activite activite : activites) {
            System.out.println(activite);
        }
    }

    return activites;
   } 
    
}

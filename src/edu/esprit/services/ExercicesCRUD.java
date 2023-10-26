/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Exercices;
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
/**
 *
 * @author ASUS
 */
public class ExercicesCRUD {
   Connection cnx; 
   public ExercicesCRUD(){
   cnx = MyConnectiona.getInstance().getCnx();
   }
    

    public void ajouterExercice(Exercices exercice) {
        try {
            String requete = "INSERT INTO exercices (nomE, categorie, dureeE, repetitions, series, objectif, niveauDifficulte) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = cnx.prepareStatement(requete);

            st.setString(1, exercice.getNomE());
            st.setString(2, exercice.getCategorie());
            st.setInt(3, exercice.getDureeE());
            st.setInt(4, exercice.getRepetitions());
            st.setInt(5, exercice.getSeries());
            st.setString(6, exercice.getObjectif());
            st.setString(7, exercice.getNiveauDifficulte());

            st.executeUpdate();

            System.out.println("Exercice ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
}
    
    
    public ObservableList<Exercices> rechercherExercices() {
        ObservableList<Exercices> exercices = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM exercices";
            PreparedStatement st =cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idE = rs.getInt("idE");
                String nomE = rs.getString("nomE");
                String categorie = rs.getString("categorie");
                int dureeE = rs.getInt("dureeE");
                int repetitions = rs.getInt("repetitions");
                int series = rs.getInt("series");
                String objectif = rs.getString("objectif");
                String niveauDifficulte = rs.getString("niveauDifficulte");

                Exercices exercice = new Exercices(idE, nomE, categorie, dureeE, repetitions, series, objectif, niveauDifficulte);
                exercices.add(exercice);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exercices;
    }
    
    
    
    public void modifierExercice(int idE, Exercices newExercice) {
        try {
            String requete = "UPDATE exercices SET nomE=?, categorie=?, dureeE=?, repetitions=?, series=?, objectif=?, niveauDifficulte=? WHERE idE=?";
            PreparedStatement st = cnx.prepareStatement(requete);

            st.setString(1, newExercice.getNomE());
            st.setString(2, newExercice.getCategorie());
            st.setInt(3, newExercice.getDureeE());
            st.setInt(4, newExercice.getRepetitions());
            st.setInt(5, newExercice.getSeries());
            st.setString(6, newExercice.getObjectif());
            st.setString(7, newExercice.getNiveauDifficulte());
            st.setInt(8, idE);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Exercice modifié avec succès !");
            } else {
                System.out.println("Aucun exercice trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    public void supprimerExercice(int idE) {
        try {
            String requete = "DELETE FROM exercices WHERE idE=?";
            PreparedStatement st =cnx.prepareStatement(requete);
            st.setInt(1, idE);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Exercice supprimé avec succès !");
            } else {
                System.out.println("Aucun exercice trouvé avec l'ID spécifié.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    
    
}
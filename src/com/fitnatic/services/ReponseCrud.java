/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.services;

import com.fitnatic.entities.Reponse;
import com.fitnatic.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class ReponseCrud {
    private MyConnection cnx = MyConnection.getInstance();
    private Connection myconx = cnx.getCnx();

    public void ajouterReponse(Reponse r) {
        LocalDate date = r.getDate();
        try {
            String requete = "INSERT INTO reponse (reclamationId, dateCreation, contenu) VALUES (?, ?, ?)";
            PreparedStatement st = myconx.prepareStatement(requete);

            st.setInt(1, r.getReclamationId());
            Date sqlDate = Date.valueOf(date);
            st.setDate(2, sqlDate);
            st.setString(3, r.getContenu());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Réponse ajoutée avec succès!");
            } else {
                System.out.println("L'ajout de la réponse a échoué.");
            }
        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'ajout de la réponse : " + ex.getMessage());
        }
    }
}


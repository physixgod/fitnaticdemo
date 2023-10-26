/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.interfaces;

import java.util.List;
import javafx_forum1.entities.Evenement;

/**
 *
 * @author OUMAIMA
 */
public interface EvenementInterface {
  

   

    // Méthode pour créer un nouvel événement
    public void creerEvenement(Evenement evenement) ;

    // Méthode pour récupérer un événement par son identifiant
    public Evenement getEvenementById(int id) ;

    // Méthode pour mettre à jour un événement
    public void mettreAJourEvenement(Evenement evenement) ;

    // Méthode pour supprimer un événement
    public void supprimerEvenement(int id) ;

    // Méthode pour récupérer tous les événements
    public List<Evenement> getAllEvenements() ;
}

    

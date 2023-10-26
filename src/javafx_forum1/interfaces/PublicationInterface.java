/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.interfaces;

import java.util.List;
import javafx_forum1.entities.Publication;

/**
 *
 * @author OUMAIMA
 */
public interface PublicationInterface {
  

   

    // Méthode pour créer une nouvelle publication
    public void creerPublication(Publication publication) ;

    // Méthode pour récupérer un événement par son identifiant
    public Publication getPublicationById(int id) ;

    // Méthode pour mettre à jour une publication
    public void mettreAJourPublication(Publication publication) ;

    // Méthode pour supprimer une publication
    public void supprimerPublication(int id) ;

    // Méthode pour récupérer tous les publications
    public List<Publication> getAllPublications() ;
}

    

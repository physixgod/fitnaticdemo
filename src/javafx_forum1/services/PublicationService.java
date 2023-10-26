package javafx_forum1.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx_forum1.entities.Publication;
import javafx_forum1.utiles.MyConnection1;

public class PublicationService {
    private Connection myconx; // Correction : déplacez la déclaration de la connexion ici

    public PublicationService() {
        MyConnection1 cnx = MyConnection1.getInstance();
        myconx = cnx.getCnx(); // Correction : initialisez la connexion ici
    }

    public void CreerPublication(Publication publication) {
        try {
            String requete = "INSERT INTO publication (nom_utilisateur, choix_evenement) VALUES (?, ?)";
            PreparedStatement st = myconx.prepareStatement(requete);

            st.setString(1, publication.getUsername());
            st.setString(2, publication.getChoix());

            st.executeUpdate();

            System.out.println("Publication ajoutée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

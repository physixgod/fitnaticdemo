/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.interfaces;

import static com.fitnatic.email.EmailSender.sendConfirmationEmail;
import com.fitnatic.entities.Reponse;
import com.fitnatic.services.ReponseCrud;
import com.fitnatic.utils.MyConnection;
import java.io.IOException;
import static java.lang.Boolean.parseBoolean;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class PageReponseController implements Initializable {

    @FXML
    private Button envoyer_rep;
    @FXML
    private Button retour_rep;
    private TextField idrec_rep;
    @FXML
    private TextField contenu_rep;
    @FXML
    private DatePicker date_rep;

    ReponseCrud rc= new ReponseCrud ();
    MyConnection mc = MyConnection.getInstance();
    String query = null;
    PreparedStatement pst = null;
    @FXML
    private TextField user_email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    @FXML
    private void envoyer(MouseEvent event) {
    String Email = user_email.getText();
    LocalDate date = date_rep.getValue();
    String contenu = contenu_rep.getText();

    if (Email.isEmpty() || date == null || contenu.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Données non disponibles!!");
        alert.showAndWait();
        return; // Sortez de la méthode en cas de données manquantes
    }

    int email_u;
    /*try {
        email_u = Integer.parseInt(Email);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("L'identifiant de réclamation n'est pas un nombre entier valide.");
        alert.showAndWait();
        return; // Sortez de la méthode en cas d'erreur de conversion
    }*/

    String query = "INSERT INTO reponse (Email, dateCreation, contenu) VALUES (?, ?, ?)";
    try {
        pst = mc.getCnx().prepareStatement(query);
       //pst.setInt(1, reponseId);
        pst.setString(1, Email);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        pst.setDate(2, sqlDate);
        pst.setString(3, contenu);
        int rowsAffected = pst.executeUpdate();
        
        sendConfirmationEmail(user_email.getText(),contenu_rep.getText());

        if (rowsAffected > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Réponse ajoutée avec succès!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Échec de l'ajout de la réponse.");
            alert.showAndWait();
        }

        user_email.clear();
        date_rep.setValue(null);
        contenu_rep.clear();
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'ajout de la réponse : " + ex.getMessage());
    }
}


    @FXML
    private void retour(MouseEvent event) {
                                                                       try {
 
        Parent page =FXMLLoader.load(getClass().getResource("/gui/menufitnatic.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    }
    


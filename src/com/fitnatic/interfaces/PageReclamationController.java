/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.interfaces;

import com.fitnatic.entities.Reclamation;
import com.fitnatic.services.ReclamationCrud;
import com.fitnatic.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import com.fitnatic.interfaces.SendSMS;
/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class PageReclamationController implements Initializable {

    @FXML
    private TextField userid;
    @FXML
    private TextField description_r;
    @FXML
    private Button retour_rec;
    @FXML
    private Button ajouter_rec;
    @FXML
    private DatePicker date_rec;
    @FXML
    private TableView<Reclamation> tab_rec;
    @FXML
    private TableColumn<Reclamation, Integer> id_col;
    @FXML
    private TableColumn<Reclamation, String> date_col;
    @FXML
    private TableColumn<Reclamation, String> desc_col;
    @FXML
    private Button supp_rec;
    @FXML
    private Button modif_rec;
    
    String query = null;
    MyConnection mc = MyConnection.getInstance();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Reclamation Reclamation = null;
    ReclamationCrud bb= new ReclamationCrud ();
    @FXML
    private TableColumn<Reclamation, Integer> nom_col;
    
    /**
     * Initializes the controller class.
     */
    private String[] badWords = {"Merde", "Putain", "Fils de pute", "Idiot", "Je vais te tuer", "LGBTQ", "racicte","terroriste"};
    @FXML
    private Button Évaluer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(PageReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private boolean containsBadWords(String description) {
    for (String badWord : badWords) {
        if (description.toLowerCase().contains(badWord.toLowerCase())) {
            return true;
        }
    }
    return false;
}

    @FXML
    private void retour(MouseEvent event) {
         Stage stage = (Stage) retour_rec.getScene().getWindow();
    stage.close(); // Ferme la fenêtre actuelle
    }

    @FXML
    private void ajouter(MouseEvent event) throws SQLException {
        
        //String strId = userId.getText(); // Supposez que strId contient "123" (valeur en tant que chaîne de caractères)
        String id = userid.getText();
        LocalDate date=date_rec.getValue();
        String desc=description_r.getText();
       int reclamationId=0;
       int id_u; 
       
       
        if ((id.length()<1)|| (!id.matches("\\d+"))) {
     
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("id invalide ..!");
        Optional<ButtonType>result =  alert.showAndWait();     
      }
      
      else if (date==null){   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText(" date invalide ..!");
        Optional<ButtonType>result =  alert.showAndWait();    }
      else if  (desc.length()<1) {
     
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText(" description invalide ..!");
        Optional<ButtonType>result =  alert.showAndWait();     
      }
      else if (containsBadWords(desc)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("La description contient des mots inappropriés.");
        alert.showAndWait();
    }
      
        else{
          id_u = Integer.parseInt(userid.getText());
           Reclamation r=new Reclamation(reclamationId,id_u,date,desc);
        bb.ajouterReclamation(r);
        ShowReclamation();
         //SendSMS.sendSMS();

        }
    
    }  


   @FXML
    private void supprimer(MouseEvent event) throws SQLException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
             Reclamation r= tab_rec.getSelectionModel().getSelectedItem();
             bb.supprimerReclamation(r.getReclamationId());
            ShowReclamation();}
        else {}

}
        
       
    

    @FXML
    private void modifier(MouseEvent event) {
        
        Reclamation r= tab_rec.getSelectionModel().getSelectedItem();

        int idReclamationAModifier = r.getUserId(); // Remplacez ... par la façon dont vous obtenez l'ID

        userid.setText(Integer.toString(r.getUserId()));
        date_rec.setValue(r.getDate());
        String desc=description_r.getText();
Reclamation r2=new Reclamation(r.getReclamationId(),r.getUserId(),r.getDate(),desc);
    // Appelez la méReclamationthode de modification de la classe ReclamationCrud
    ReclamationCrud rc=new ReclamationCrud();
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
    rc.modifierReclamation(r2.getReclamationId(), desc);
        try {
            ShowReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(PageReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{}
    }

    
    
public void ShowReclamation() throws SQLException {
    try {
        String query = "SELECT * FROM reclamation"; // Remplacez "reclamation" par le nom de votre table
           pst = mc.getCnx().prepareStatement(query);
              ResultSet rs =pst.executeQuery(query);

        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        while (rs.next()) {
            int id = rs.getInt("userId"); // Remplacez "id" par le nom de la colonne d'ID
            int idrec = rs.getInt("reclamationId");
            LocalDate date = rs.getDate("date").toLocalDate(); // Remplacez "date" par le nom de la colonne de date
            String description = rs.getString("description"); // Remplacez "description" par le nom de la colonne de description

            Reclamation reclamation = new Reclamation(idrec,id, date, description);
            reclamationList.add(reclamation);
        }

        // Associez les données aux colonnes de la table
        
        id_col.setCellValueFactory(new PropertyValueFactory<>("reclamationId"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("userId"));

        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Affichez les données dans la table
        tab_rec.setItems(reclamationList);

    } catch (SQLException ex) {
        System.err.println("Erreur lors de la récupération des réclamations : " + ex.getMessage());
    }
}

    @FXML
    private void Évaluer(MouseEvent event) {
        try {
            // Charger la PageReclamationStat.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageReclamationStat.fxml"));
            Parent root = loader.load();

            // Créer la scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de n'importe quel composant de votre interface
            Scene currentScene = ((Node) event.getSource()).getScene();

            // Utilisez le stage de la scène actuelle pour afficher la nouvelle scène
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void backtomenu(ActionEvent event) {
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

    @FXML
    private void BACK(MouseEvent event) {
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
    


    

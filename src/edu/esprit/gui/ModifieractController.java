/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Activite;
import edu.esprit.services.ActiviteCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifieractController implements Initializable {

    @FXML
    private TextField modnom;
    @FXML
    private DatePicker moddatedebut;
    @FXML
    private DatePicker moddatefin;
    @FXML
    private ComboBox<String> modtype;
    @FXML
    private TextField modnotes;
    @FXML
    private TextField modduree;
    @FXML
    private Button modconfirm;
    @FXML
    private Button backtotable;
    
    private Activite activite;
    private ActiviteCRUD activiteCRUD;
    public void setActiviteCRUD(ActiviteCRUD activiteCRUD) {
        this.activiteCRUD = activiteCRUD;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> options = FXCollections.observableArrayList(
                "Cardio", "Strength Training", "Flexibility and Mobility","Core Strengthening");
        
        modtype.setItems(options);
 
        modtype.setValue("Cardio"); 
        // TODO
    }    
    
    public void setActivite(Activite activite) {
        this.activite = activite;
        modnom.setText(activite.getNom());
        moddatedebut.setValue(new java.sql.Date(activite.getDateDebut().getTime()).toLocalDate());
        moddatefin.setValue(new java.sql.Date(activite.getDateFin().getTime()).toLocalDate());
        modtype.setValue(activite.getType()); // Assuming modtype is a ComboBox<String>
        modnotes.setText(activite.getNotes());
        modduree.setText(String.valueOf(activite.getDuree()));
    }

    @FXML
    private void confirmermodification(ActionEvent event) {
        String nom = modnom.getText();
        Date dateDebut = java.sql.Date.valueOf(moddatedebut.getValue());                                          
        Date dateFin =  java.sql.Date.valueOf(moddatefin.getValue());                                  
        String type = modtype.getValue(); // Assuming modtype is a ComboBox<String>
        String notes = modnotes.getText();
        int duree = Integer.parseInt(modduree.getText());
        
       

        // Assuming you have a constructor in Activite class that takes these parameters
        Activite newActivite = new Activite(nom, type,dateDebut, dateFin, duree, notes);
        ActiviteCRUD ac = new ActiviteCRUD();
        ac.modifierActivite(activite.getId(),newActivite);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Activity modified successfully.");
        alert.showAndWait();
        
          try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("treeviewact.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        TreeviewactController controller = loader.getController();
        controller.setActiviteCRUD(activiteCRUD);
      
        ObservableList<Activite> activitesList = ac.rechercherActivites();
               
       controller.displayActivities(activitesList);
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Activities Table");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }  
        
        /*activiteCRUD.modifierActivite(activite.getId(), newActivite);*/
        
    }

    @FXML
    private void backtotableview(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("treeviewact.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        TreeviewactController controller = loader.getController();
       
       

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Activities Table");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}

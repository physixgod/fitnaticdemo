/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Exercices;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListexinfoController implements Initializable {

    @FXML
    private ListView<String> exerciseListViewex;
    @FXML
    private Button bac;
    
    @FXML
    private TextField nomex;
    @FXML
    private Button add;
    
    private Exercices exercise; 
    
    public void setNomexTextField(TextField nomex) {
        this.nomex = nomex;
       
    }

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setExerciseNames(List<String> exerciseNames) {
    ObservableList<String> items = FXCollections.observableArrayList(exerciseNames);
    exerciseListViewex.setItems(items);
    
    exerciseListViewex.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
}

    @FXML
    private void backtoexercicetable(ActionEvent event) {
         try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interex.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        InterexController controller = loader.getController();
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("FitNatice");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
private void selectExercise(ActionEvent event) {
    String selectedExercise = exerciseListViewex.getSelectionModel().getSelectedItem();
    if (nomex != null) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("interex.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        InterexController controller = loader.getController();
        
         nomex.setText(selectedExercise);
       String nom = nomex.getText();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("FitNatice");
        stage.setScene(scene);
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
       
    } else {
        System.out.println("nomex is null");
    }

   
}
}

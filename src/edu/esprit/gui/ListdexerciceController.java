/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListdexerciceController implements Initializable {

    @FXML
    private ListView<String> exerciseListView;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void setExerciseNames(ObservableList<String> exerciseNames) {
        exerciseListView.setItems(exerciseNames);
    }   

    @FXML
    private void quitapi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interactivite.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            InteractiviteController firstController = loader.getController();
             
           
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
            stage.setTitle("FitNatic");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

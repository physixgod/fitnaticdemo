/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.gui.ExtableviewController;
import edu.esprit.entities.Exercices;
import edu.esprit.services.ExercicesCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierexController implements Initializable {

    @FXML
    private TextField nomexmod;
    @FXML
    private TextField durexmod;
    @FXML
    private ComboBox<String> catexmod;
    @FXML
    private ComboBox<String> ndexmod;
    @FXML
    private TextArea obexmod;
    @FXML
    private Spinner<Integer> serexmod;
    @FXML
    private Spinner<Integer> repexmod;
    @FXML
    private Button backtotable;
    @FXML
    private Button confirmermodex;
    
     private ObservableList<String> ndexmodOptions = FXCollections.observableArrayList(
            "Easy", "Medium", "Hard");
    private ObservableList<String> catexmodOptions = FXCollections.observableArrayList(
        "Bodyweight Exercises", "Free Weight Exercises", "Machine Exercises","Cardio Exercises","Compound Movements","Isolation Exercises");
    
    private Exercices exercices;
    private ExercicesCRUD exercicesCRUD;
    public void setExercicesCRUD(ExercicesCRUD exercicesCRUD) {
        this.exercicesCRUD = exercicesCRUD;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ndexmod.setItems(ndexmodOptions);
        ndexmod.setValue("Easy"); 

      
        catexmod.setItems(catexmodOptions);
        catexmod.setValue("Bodyweight Exercises");
        
        
        serexmod.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 1));
        repexmod.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 1));
        // TODO
    }    

    public void setExercices(Exercices exercices) {
        this.exercices = exercices;
        nomexmod.setText(exercices.getNomE());
        durexmod.setText(String.valueOf(exercices.getDureeE()));
        catexmod.setValue(exercices.getCategorie()); 
        ndexmod.setValue(exercices.getNiveauDifficulte()); 
        obexmod.setText(exercices.getObjectif());       
        serexmod.getValueFactory().setValue(exercices.getSeries());
        repexmod.getValueFactory().setValue(exercices.getRepetitions());
        
        
        
    }
    
    @FXML
    private void backtotableview(ActionEvent event) {
       try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("extableview.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ExtableviewController controller = loader.getController();
        
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Exercices Table");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void confirmermodificationex(ActionEvent event) {
        String nom = nomexmod.getText();
        String categorie = catexmod.getValue();
        int dureeE = Integer.parseInt(durexmod.getText());
        int repetitions = repexmod.getValue();
        int series = serexmod.getValue();
        String objectif = obexmod.getText();
        String niveauDifficulte = ndexmod.getValue();
        
       

        // Assuming you have a constructor in Activite class that takes these parameters
        Exercices newExercices = new Exercices(nom, categorie, dureeE, repetitions, series, objectif, niveauDifficulte);
        ExercicesCRUD ex = new ExercicesCRUD();
        ex.modifierExercice(exercices.getIdE(),newExercices);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Exercice modified successfully.");
        alert.showAndWait();
        
          try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("extableview.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ExtableviewController controller = loader.getController();
        controller.setExercicesCRUD(exercicesCRUD);
        ExercicesCRUD ac = new ExercicesCRUD();
        ObservableList<Exercices> exercicesList = ac.rechercherExercices();
             
  
       controller.displayExercices(exercicesList);
       

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Exercices Table");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }  
    }
    
}

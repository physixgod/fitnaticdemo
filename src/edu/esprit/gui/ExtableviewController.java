/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Activite;
import edu.esprit.entities.Exercices;
import edu.esprit.services.ActiviteCRUD;
import edu.esprit.services.ExercicesCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ExtableviewController implements Initializable {

    @FXML
    private TableView<Exercices> extable;
    @FXML
    private TableColumn<Exercices, String> exnom;
    @FXML
    private TableColumn<Exercices, String> excategorie;
    @FXML
    private TableColumn<Exercices, Integer> exduree;
    @FXML
    private TableColumn<Exercices, Integer> exrepetitions;
    @FXML
    private TableColumn<Exercices, Integer> exseries;
    @FXML
    private TableColumn<Exercices, String> exobjectif;
    @FXML
    private TableColumn<Exercices, String> exniveaudiff;
    
    private ObservableList<Exercices> ExercicesData = FXCollections.observableArrayList();
    @FXML
    private Button backtoex;
    @FXML
    private Button displayex;
    @FXML
    private Button suppexercice;
    @FXML
    private Button modex;
    
    private Exercices selectedExercices;
    @FXML
    private Button gotoact;
    private ActiviteCRUD activiteCRUD;
    
     public void displayExercices(ObservableList<Exercices> exercicesList) {
        extable.setItems(exercicesList);
    }
     public void setActiviteCRUD(ActiviteCRUD activiteCRUD) {
        this.activiteCRUD = activiteCRUD;
    } 
     
    private ExercicesCRUD exercicesCRUD;
    public void setExercicesCRUD(ExercicesCRUD exercicesCRUD) {
        this.exercicesCRUD = exercicesCRUD;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exnom.setCellValueFactory(new PropertyValueFactory<>("nomE"));
        excategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        exduree.setCellValueFactory(new PropertyValueFactory<>("dureeE"));
        exrepetitions.setCellValueFactory(new PropertyValueFactory<>("repetitions"));
        exseries.setCellValueFactory(new PropertyValueFactory<>("series"));
        exobjectif.setCellValueFactory(new PropertyValueFactory<>("objectif"));
        exniveaudiff.setCellValueFactory(new PropertyValueFactory<>("niveauDifficulte"));
        // TODO
    }    

    @FXML
    private void backtoexercices(ActionEvent event) {
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
    private void displayexercices(ActionEvent event) {
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

    @FXML
    private void supprimmerexercice(ActionEvent event) {
        try {
        Exercices exercices = extable.getSelectionModel().getSelectedItem();
        if (exercices != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Exercise");
            alert.setContentText("Are you sure you want to delete this exercise?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ExercicesCRUD ac = new ExercicesCRUD();
                ac.supprimerExercice(exercices.getIdE());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("extableview.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                ExtableviewController controller = loader.getController();

                controller.setExercicesCRUD(exercicesCRUD);

                ObservableList<Exercices> exercicesList = ac.rechercherExercices();

                controller.displayExercices(exercicesList);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Exercices Table");
                stage.setScene(scene);
                stage.show();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void modifierexercices(ActionEvent event) {
        
                Exercices selectedExercices = extable.getSelectionModel().getSelectedItem();
        if (selectedExercices != null){
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierex.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ModifierexController controller = loader.getController();
       
        controller.setExercices(selectedExercices);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Modifierexercice");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
        }
      
    }

    @FXML
    private void gotoactivites(ActionEvent event) {
         try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("treeviewact.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        TreeviewactController controller = loader.getController();
        controller.setActiviteCRUD(activiteCRUD);
        ActiviteCRUD ac = new ActiviteCRUD();
        ObservableList<Activite> activitesList = ac.rechercherActivites();
             
  
       controller.displayActivities(activitesList);
       

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Activities Table");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
}

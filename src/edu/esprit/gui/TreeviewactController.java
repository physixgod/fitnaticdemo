/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.esprit.entities.Activite;
import edu.esprit.entities.Exercices;
import edu.esprit.services.ActiviteCRUD;
import edu.esprit.services.ExercicesCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TreeviewactController implements Initializable {

   
    @FXML
    private Button backtree;
    
    private InteractiviteController interactiviteController;
    private ActiviteCRUD activiteCRUD;
    @FXML
    private TableView<Activite> table;
    @FXML
    private TableColumn<Activite, String> idt;
    @FXML
    private TableColumn<Activite,String> typet;
    @FXML
    private TableColumn<Activite, Date> dbt;
    @FXML
    private TableColumn<Activite, Date> dft;
    @FXML
    private TableColumn<Activite, Integer> dt;
    @FXML
    private TableColumn<Activite, String> nt;
    
    private Activite selectedActivite;
    
    private ObservableList<Activite> activitesData = FXCollections.observableArrayList();
    private ObservableList<Exercices> ExercicesData = FXCollections.observableArrayList();
    @FXML
    private Button modt;
    @FXML
    private Button deleteButton;
    @FXML
    private Button display;
    @FXML
    private Label afficherid;
    @FXML
    private Button IDdisplay;
    @FXML
    private Button gotoex;
    @FXML
    private FontAwesomeIconView BACK;

    public void displayActivities(ObservableList<Activite> activiteList) {
        table.setItems(activiteList);
    }

    public void setActiviteCRUD(ActiviteCRUD activiteCRUD) {
        this.activiteCRUD = activiteCRUD;
    }
private Exercices selectedExercices;
    
   
     
     
    private ExercicesCRUD exercicesCRUD;
    public void setExercicesCRUD(ExercicesCRUD exercicesCRUD) {
        this.exercicesCRUD = exercicesCRUD;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idt.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typet.setCellValueFactory(new PropertyValueFactory<>("type"));
        dbt.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dft.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        dt.setCellValueFactory(new PropertyValueFactory<>("duree"));
        nt.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        // TODO
    }    
 /*   public void displayActivities(List<Activite> activiteList) {
    activitesData.addAll(activiteList);
}*/
   
   /* public void setReturnButtonAction(EventHandler<ActionEvent> action) {
        backtree.setOnAction(action);
    }*/
   
    
    @FXML
    private void backtr(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interactivite.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            InteractiviteController firstController = loader.getController();
             
            /*firstController.setActiviteCRUD(activiteCRUD); */
                  
            /*Stage stage = (Stage) backtree.getScene().getWindow();*/
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
            stage.setTitle("FitNatic");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    @FXML
    private void modifyTable(ActionEvent event) {
         
        Activite selectedActivite = table.getSelectionModel().getSelectedItem();
        if (selectedActivite != null){
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifieract.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ModifieractController controller = loader.getController();
       
       controller.setActivite(selectedActivite);
       
       
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Modifieractivite");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
        }
      
    
    }

    @FXML
    private void deleteTable(ActionEvent event) {
        Activite activite = table.getSelectionModel().getSelectedItem();
       if (activite != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Activity");
        alert.setContentText("Are you sure you want to delete this activity?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ActiviteCRUD ac = new ActiviteCRUD();
            ac.supprimerActivite(activite.getId());
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
        }
    }       
    }

    @FXML
    private void displaytable(ActionEvent event) {
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

    @FXML
    private void displayid(ActionEvent event) {
            // Get the selected item from the table
    Activite activite = table.getSelectionModel().getSelectedItem();
    
    // Check if an item is actually selected
    if (activite != null) {
        // Retrieve the ID
        int id = activite.getId();
        
        // Display the ID in the label
        afficherid.setText("" + id);
        
        // Print the ID to the console (for debugging purposes)
        System.out.println("id: " + id);
    }
       
    }

    @FXML
    private void gotoexercices(ActionEvent event) {
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
    private void MENU(MouseEvent event) {
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

    


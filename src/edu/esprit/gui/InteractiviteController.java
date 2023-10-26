/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import edu.esprit.entities.Activite;
import edu.esprit.services.ActiviteCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InteractiviteController implements Initializable {

    @FXML
    private TextField actnom;
    @FXML
    private Button actvalider;
    @FXML
    private ComboBox<String> acttype;
    @FXML
    private DatePicker actdatedebut;
    @FXML
    private DatePicker actdatefin;
    @FXML
    private TextField actduree;
    @FXML
    private TextField actnotes;
    
    @FXML
    private ListView<String> exerciseListView;
  
    private ObservableList<Activite> activites = FXCollections.observableArrayList();
    private ObservableList<String> exerciseNames = FXCollections.observableArrayList();@FXML
    private Label errorLabela;
    
    private ActiviteCRUD activiteCRUD;
    @FXML
    private Button displayButton;
    @FXML
    private Button fetch;
    public void setActiviteCRUD(ActiviteCRUD activiteCRUD) {
        this.activiteCRUD = activiteCRUD;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> options = FXCollections.observableArrayList(
                "Cardio", "Strength Training", "Flexibility and Mobility","Core Strengthening");
        
        acttype.setItems(options);
 
        acttype.setValue("Cardio"); 
    }    
    
    private boolean containsNumber(String str) {
    return str.matches(".*\\d+.*");
    }
    private boolean isNumeric(String str) {
    return str.matches("\\d+");
}

    @FXML
    private void saveact(ActionEvent event) {
     try {
        
        String nom = actnom.getText();
        String type = acttype.getValue();
        Date dateDebut = java.sql.Date.valueOf(actdatedebut.getValue());
        Date dateFin = java.sql.Date.valueOf(actdatefin.getValue());
        int duree = Integer.parseInt(actduree.getText());
        String notes = actnotes.getText();

        
        if (nom.isEmpty() || dateDebut == null || dateFin == null || notes.isEmpty()) {
            errorLabela.setText("Please fill in all required fields ");
            return;
        }
         if (duree <= 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive numeric value for 'Durée'.");
            alert.showAndWait();
            return;
        }
         String dureeText = actduree.getText();

       
        if (!isNumeric(dureeText)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid numeric value for 'Durée'.");
            alert.showAndWait();
            return;
        }
         

        
        if (containsNumber(nom)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("The 'nom' field cannot contain numbers.");
            alert.showAndWait();
            return;
        }
        
        Activite a = new Activite(nom, type, dateDebut, dateFin, duree, notes);
        ActiviteCRUD ac = new ActiviteCRUD();
        ac.ajouterActivite(a);
        activites.add(a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Successful");
        alert.setHeaderText(null);
        alert.setContentText("Activity saved successfully.");
        alert.showAndWait();
       /* ObservableList<Activite> updatedActivites = FXCollections.observableArrayList(ac.rechercherActivites());*/
          
        errorLabela.setText("");
    } /*catch (NumberFormatException ex) {        
        errorLabela.setText("Please enter a valid numeric value for 'Durée'.");
    }*/ catch (Exception ex) {        
        errorLabela.setText("Error: " + ex.getMessage());
    }
        
    }
    
    
    
     public ObservableList<Activite> getActivites() {
        return activites;
    }

   /* public void setReturnButtonAction(EventHandler<ActionEvent> action) {
        displayButton.setOnAction(action);
    }*/
    
/*public void displayActivities(List<Activite> activiteList) {
        System.out.println("Received activities for display: " + activiteList);
        activites.setAll(activiteList);
    }
*/
    
     private void fetchExerciseDataFromAPI() {
    try {
        
        // Define the API URL to get exercise data
        String apiUrl = "https://wger.de/api/v2/exercise/";
      
        // Create a URL object
        URL url = new URL(apiUrl);
 System.out.println("winek?");
        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        

        // Set the request method to GET
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        // Get the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println("Content: " + content);


        // Process the JSON response
        JSONObject response = new JSONObject(content.toString());
        
        JSONArray results = response.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject exercise = results.getJSONObject(i);
            String exerciseName = exercise.getString("name");
            // Extract other exercise details as needed
             exerciseNames.add(exerciseName);
            // Now you can use the exercise data in your application
            System.out.println("Exercise Name: " + exerciseName);
        }
        if (exerciseListView != null) {
    exerciseListView.setItems(exerciseNames);
}
        
        
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handleDisplayButtonAction(ActionEvent event) {
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
    private void handleFetchExerciseData(ActionEvent event) {
        
         try {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listdexercice.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ListdexerciceController controller = loader.getController();
        
        controller.setExerciseNames(exerciseNames);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("api");
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
        fetchExerciseDataFromAPI();
        
        
        
        
    }

   

    
    
    
}

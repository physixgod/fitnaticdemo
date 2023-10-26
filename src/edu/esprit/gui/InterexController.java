package edu.esprit.gui;

import edu.esprit.gui.ExtableviewController;
import edu.esprit.entities.Exercices;
import edu.esprit.services.ExercicesCRUD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterexController implements Initializable {

    @FXML
    private Spinner<Integer> repex;
    @FXML
    private Spinner<Integer> serex;
    @FXML
    private ComboBox<String> ndex;
    @FXML
    private ComboBox<String> catex;
    @FXML
    private Button saveex;
    @FXML
    private TextField nomex;
    @FXML
    private TextField durex;
    @FXML
    private TextArea obex;
    
     private Exercices exercices;
     private ExercicesCRUD exercicesCRUD;
    public void setExercicesCRUD(ExercicesCRUD exercicesCRUD) {
        this.exercicesCRUD = exercicesCRUD;
    }
    
    public void setNomexTextField(TextField nomex) {
        this.nomex = nomex;
    }
    
    
    private ListView<String> exerciseListViewex;

    private ObservableList<String> ndexOptions = FXCollections.observableArrayList(
            "Easy", "Medium", "Hard");
    private ObservableList<String> catexOptions = FXCollections.observableArrayList(
        "Bodyweight Exercises", "Free Weight Exercises", "Machine Exercises","Cardio Exercises","Compound Movements","Isolation Exercises");
    @FXML
    private Label errorLabel;
    @FXML
    private Button displayex;
    @FXML
    private Button handleFetchExinfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("nomex is null: " + (nomex == null));
     
        ndex.setItems(ndexOptions);
        ndex.setValue("Easy"); 

      
        catex.setItems(catexOptions);
        catex.setValue("Bodyweight Exercises");

       
        repex.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 1));
        serex.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 1));
    }

    @FXML
    private void saveexr(ActionEvent event) {
    try {
        String nom = nomex.getText();
        String categorie = catex.getValue();
        int dureeE = Integer.parseInt(durex.getText());
        int repetitions = repex.getValue();
        int series = serex.getValue();
        String objectif = obex.getText();
        String niveauDifficulte = ndex.getValue();
        
         if (nom.isEmpty() || objectif.isEmpty() ) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
                
                return;
            }

            if (dureeE <= 0 ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive numeric value for 'Durée'.");
            alert.showAndWait();
            return;
        }

        Exercices e = new Exercices(nom, categorie, dureeE, repetitions, series, objectif, niveauDifficulte);
        ExercicesCRUD ec = new ExercicesCRUD();
        ec.ajouterExercice(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Successful");
        alert.setHeaderText(null);
        alert.setContentText("exercices saved successfully.");
        alert.showAndWait();
        

        System.out.println("Exercice ajouté !");
        errorLabel.setText("");
    } catch (NumberFormatException ex) {
        System.err.println("Erreur de format : Vérifiez que les champs numériques sont corrects.");
    }catch (IllegalArgumentException ex) {
            errorLabel.setText(ex.getMessage());
        }
}

    @FXML
    private void gotodisplayex(ActionEvent event) {
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
    

    
    private List<String> fetchExerciseInfoFromAPI() {
    List<String> exerciseNames = new ArrayList<>();
    try {
        String apiUrl = "https://wger.de/api/v2/muscle/";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject response = new JSONObject(content.toString());
        JSONArray results = response.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject exercise = results.getJSONObject(i);
            String exerciseName = exercise.getString("name");
            exerciseNames.add(exerciseName);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return exerciseNames;
}  
    
    
    

    @FXML
    private void handleFetchExerciseInfo(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listexinfo.fxml"));
            Parent root = loader.load();
            ListexinfoController listexController = loader.getController();
            
            listexController.setNomexTextField(nomex);

            List<String> exerciseNames = fetchExerciseInfoFromAPI();
            listexController.setExerciseNames(exerciseNames);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Listexinfo Interface");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }

    
    
    
    
    
    
}

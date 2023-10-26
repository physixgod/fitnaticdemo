/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx_forum1.api.SmsApi;
import javafx_forum1.api.WeitherApi;
import static javafx_forum1.api.WeitherApi.getWeatherData;
import javafx_forum1.entities.Evenement;
import javafx_forum1.entities.EventTableView;
import javafx_forum1.entities.Publication;
import javafx_forum1.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author OUMAIMA
 */
public class ChoixController implements Initializable {
    @FXML
  Button btnOK;
    @FXML
    private Label lbSport;
    @FXML
    private DatePicker tfDate;
    @FXML
    private Label lbSelDate;
    @FXML
    private Label lbSelect;
    @FXML
    private Hyperlink hlYoga;
    @FXML
    private Hyperlink hlMedi;
    @FXML
    private Hyperlink hlKarate;
    @FXML
    private Hyperlink hlMusc;
    @FXML
    private Hyperlink hlFoot;
    @FXML
    private Hyperlink hlFooting;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView imgSport;
    @FXML
    private TextField tfPublica;
    @FXML
    private Label lbCreate;
    
    
    @FXML
    private TableView<EventTableView> eventTable;
    @FXML
    private TableColumn<EventTableView, Date> dateColumn;
    @FXML
    private TableColumn<EventTableView, String> choixColumn;
    @FXML
    private TableColumn<EventTableView, EventTableView> deleteColumn;
    
     // Injectez votre service EvenementService ici
    private EvenementService evenementService = new EvenementService();
    public String choix;
    @FXML
    private AnchorPane BACK;

    /**
     * Initializes the controller class.
     */
    
     @FXML
   private void AffichePub(ActionEvent event) throws IOException {
       // Load the new FXML file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx_forum1/gui/Pubs.fxml"));
    Parent root = loader.load();
 // Get the controller for the publication view
    PubsController pubsController = loader.getController();
    
    // Get the stage (window) from the button
    Stage stage = (Stage) btnOK.getScene().getWindow();

    // Set the new scene
    Scene scene = new Scene(root);
    stage.setScene(scene);
   }
    
    
    @FXML
   private void AddPub(ActionEvent event) {
       
        SmsApi smsApi = new SmsApi(); 
        
        
       
     // dateErrorLabel.setText("");
  //  publicDescriptionErrorLabel.setText("");
  
  
        // Récupérez les valeurs des éléments de l'interface utilisateur
        
       if (tfDate.getValue()== null) {
        showErrorMessage("Please select a valid date.");
        return;
    }
        Date dateEvenement = java.sql.Date.valueOf(tfDate.getValue());
      
        String choixEvenement = this.choix; // Vous devez obtenir la valeur de votre choix à partir de vos hyperliens
        String descriptionEvenement = tfPublica.getText(); // Obtenez la description depuis le champ de texte


    if (choixEvenement == null || choixEvenement.isEmpty()) {
        showErrorMessage("Please select a choice.");
        return;
    }

    if (descriptionEvenement == null || descriptionEvenement.isEmpty()) {
        showErrorMessage("Description is required.");
        return;
    }

       // smsApi.SmsNotification(tfDate.getValue().toString(),choixEvenement); 
       
        // Créez un nouvel objet Evenement
        Evenement evenement = new Evenement(dateEvenement, choixEvenement, descriptionEvenement);
        

        // Appelez le service pour créer l'événement
        evenementService.creerEvenement(evenement);
        showSuccessMessage("Added with success!");
        
    
   }

   

   @FXML
    private Pane Temperature;
   
   @FXML
private void YogaClic(ActionEvent event) {
    this.choix = "Yoga";}
    @FXML
private void MedClic (ActionEvent event) {
    this.choix = "Meditation";}
    @FXML
    private void KarateClic(ActionEvent event) {
    this.choix = "Karate";
    }
     @FXML
    private void MuscClic(ActionEvent event) {
    this.choix = "Musculation";}
    @FXML
    private void FootKClic(ActionEvent event) {
   this.choix = "Foot kids ";}
    @FXML
    private void FootingClic(ActionEvent event) {
    this.choix = "Footing";}
    // Faites quelque chose avec la valeur du choix, par exemple, l'enregistrer ou l'afficher

   
    private void showErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    private void showSuccessMessage(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
   private ObservableList<EventTableView> eventList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        WeitherApi weither = new WeitherApi(); 
        
        String city = "Tunisie";
    String apiKey = "eeae869ae58441f2902f895481036cf1"; // Replace with your Weatherbit API key
   

    // Fetch current weather data
    String weatherData =  weither.getWeatherData(apiKey, city);
        System.out.println(weatherData);
    String temperature =  weither.parseTemperature(weatherData);
    String description =  weither.parseDescription(weatherData);
   Label textLabel = new Label(temperature+" "+description);
   Temperature.getChildren().add(textLabel) ; 
        
  // Set up the table columns
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateEvenementProperty());
        choixColumn.setCellValueFactory(cellData -> cellData.getValue().choixEvenementProperty());
       // descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionEvenementProperty());

        // Set up the delete button column
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(column -> {
            return new TableCell<EventTableView, EventTableView>() {
                final Button deleteButton = new Button("Delete");

                @Override
                protected void updateItem(EventTableView evenement, boolean empty) {
                    super.updateItem(evenement, empty);

                    if (evenement == null) {
                        setGraphic(null);
                        return;
                    }

                    deleteButton.setOnAction(event -> deleteEvent(evenement));
                    setGraphic(deleteButton);
                }
            };
        });

        // Load data into the table
        eventList.addAll(evenementService. getAllEvenementsEventTableView());
        eventTable.setItems(eventList);

                }    
    private void deleteEvent(EventTableView evenement) {
        evenementService.supprimerEvenement(evenement.getId());
        eventList.remove(evenement);
    }

    @FXML
    private void MuscClic(MouseEvent event) {
    }

    @FXML
    private void backtoMenu(MouseEvent event) {
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



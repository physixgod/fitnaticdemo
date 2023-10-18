/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Competition;
import entities.Sport_Type;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.SportTypeCRUD;
import services.CompetitionCRUD;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class AddCompetitionController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private ComboBox<String> locationTxt;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField entryFeeTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private ComboBox<Sport_Type> sportType;
    @FXML
    private ComboBox<String> competitonCategory;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextArea desciption;
    @FXML
    private Button addBtn;
    @FXML
    private AnchorPane prizeTxt;
    @FXML
    private TextField prize;
    @FXML
    private AnchorPane backBtn;
    @FXML
    private Button returnBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SportTypeCRUD sportTypeCRUD = new SportTypeCRUD();  
        ObservableList<Sport_Type> sportTypes = sportTypeCRUD.getAllSport();  
        sportType.setItems(sportTypes);
        // TODO
        ObservableList<String> options = FXCollections.observableArrayList(
        "Professional",
        "Amateur",
        "University",
        "Cadet",
        "Friendly",
        "Regional",
        "National",
        "Qualification",
        "Final",
        "Other"  
        );
    ObservableList<String> locations = FXCollections.observableArrayList(sportTypeCRUD.getAllLocations());
    locationTxt.setItems(locations);
    //locationTxt.textProperty().addListener((observable, oldValue, newValue) -> updateSportTypeComboBox());
    //startDate.valueProperty().addListener((observable, oldValue, newValue) -> updateSportTypeComboBox());
    //endDate.valueProperty().addListener((observable, oldValue, newValue) -> updateSportTypeComboBox());
    locationTxt.valueProperty().addListener((observable, oldValue, newValue) -> {
    updateSportTypeComboBox();
});
    competitonCategory.setItems(options);
    } 
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
private boolean validateForm() {
        List<String> errors = new ArrayList<>();

        if (nameTxt.getText().isEmpty()) {
            errors.add("Competition name is required.");
        }

        if (locationTxt.getValue() == null) {
            errors.add("Please select a location.");
        }

        if (startDate.getValue() == null) {
            errors.add("Start date is required.");
        }

        if (endDate.getValue() == null) {
            errors.add("End date is required.");
        }

        if (entryFeeTxt.getText().isEmpty() || !isNumeric(entryFeeTxt.getText())) {
            errors.add("Entry fee must be a valid number.");
            entryFeeTxt.setText("");
        }

        if (maxTxt.getText().isEmpty() || !isNumeric(maxTxt.getText())) {
            errors.add("Max participants must be a valid number.");
            maxTxt.setText("");
        }

        if (sportType.getValue() == null) {
            errors.add("Please select a sport type.");
        }

        if (competitonCategory.getValue() == null) {
            errors.add("Please select a competition category.");
        }
        if (prize.getText().isEmpty()) {
            errors.add("Prize is required.");
        } else {
        }

    if (!errors.isEmpty()) {
        displayError("Please correct the following errors:\n" + String.join("\n", errors));
        return false;
    } else {
            return true;
        }
    }

    private boolean isNumeric(String input) {
        return NUMBER_PATTERN.matcher(input).matches();
    }
    private void displayError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();
}
 
private ObservableList<Sport_Type> filteredSportTypes = FXCollections.observableArrayList();

private void updateSportTypeComboBox() {
    SportTypeCRUD sp = new SportTypeCRUD();
    String selectedLocation = locationTxt.getValue();
    ObservableList<Sport_Type> filteredSportTypes = sp.getFilteredSportTypes(selectedLocation);
    sportType.setItems(filteredSportTypes);
    //String location = locationTxt.getText();
 
    //sportType.setItems(getFilteredSportTypes(locationTxt.getText()));

}

    @FXML
    private void saveCompetition(ActionEvent event) {
    if (validateForm()) {
        String name = nameTxt.getText();
        String loc = locationTxt.getValue();
        LocalDate localStartDate = startDate.getValue();
        LocalDate localEndDate = endDate.getValue();
        String description = desciption.getText();
        Sport_Type selectedSportType = sportType.getValue();
        String selectedSportTypeName = selectedSportType.getName();
        String selectedCompetitionCategory = competitonCategory.getValue();
        String prix=prize.getText();
        // Make sure to convert the date fields from LocalDate to Date
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        // You can extract and convert the other fields as needed

        int entryFee = Integer.parseInt(entryFeeTxt.getText()); 
        int maxParticipants = Integer.parseInt(maxTxt.getText()); 

        Competition C = new Competition(
            name,
            sqlStartDate,
            sqlEndDate,
            loc,
            description,
            selectedSportTypeName,
            selectedCompetitionCategory,
            entryFee,
            maxParticipants,
            "Not Started", // Set your status here
            prix   // Set your prizes here
        );
        CompetitionCRUD pcd=new CompetitionCRUD();
        pcd.ajouterCompetition(C);
        show();
            
        }
    }
        public void setReturnButtonAction(EventHandler<ActionEvent> action) {
        returnBtn.setOnAction(action);
    }

    private ObservableList<Sport_Type> getFilteredSportTypes(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public void show() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Competition Added Successfully");

        alert.showAndWait();
    }





    
}

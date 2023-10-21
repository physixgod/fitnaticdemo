/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Sport_Type;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static services.CompetitionCRUD.datep;
import services.SportTypeCRUD;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class AddSportController implements Initializable {

    @FXML
    private AnchorPane prizeTxt;
    @FXML
    private TextField sportName;
    @FXML
    private DatePicker end;
    @FXML
    private DatePicker start;
    @FXML
    private TextField location;
    @FXML
    private AnchorPane backBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private Button addBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void saveSport(ActionEvent event) {
        if (validateForm()){
        String loc = location.getText();
        String sport =sportName.getText();
        LocalDate localStartDate = start.getValue();
        LocalDate localEndDate = end.getValue();
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        Sport_Type sp=new Sport_Type(sport,loc,sqlStartDate,sqlEndDate);
        SportTypeCRUD spc=new SportTypeCRUD();
        spc.ajouterSportType(sp);
        show();
        location.setText("");
        sportName.setText("");
        start.setValue(null);
        end.setValue(null);
        }
    }
    private boolean validateForm() {
        List<String> errors = new ArrayList<>();

        if (location.getText().isEmpty()) {
            errors.add("Please enter a  location.");
        }

        if (sportName.getText() == null) {
            errors.add("Please enter a sport name ");
        }

        if (start.getValue() == null) {
            errors.add("Start date is required.");
        }

        if (end.getValue() == null) {
            errors.add("End date is required.");
        }
                if(datep(start,end) == false){
            errors.add("End Date should come after the Start Date.");
                }

    if (!errors.isEmpty()) {
        displayError("Please correct the following errors:\n" + String.join("\n", errors));
        return false;
    } else {
            return true;
        }
    }
    private void displayError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();
}
    public void show() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Sport Type Added Successfully");

        alert.showAndWait();
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menuCompetition.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    
}

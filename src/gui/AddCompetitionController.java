/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Sport_Type;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class AddCompetitionController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField locationTxt;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            // Add more options as needed
        );
        private ObservableList<Sport_Type> filteredSportTypes = FXCollections.observableArrayList();
         competitonCategory.setItems(options);
    }    

    @FXML
    private void saveCompetition(ActionEvent event) {
    }

    
}

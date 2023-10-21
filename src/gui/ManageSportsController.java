/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Competition;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entities.Sport_Type;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import services.CompetitionCRUD;
import static services.CompetitionCRUD.datep;
import services.SportTypeCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class ManageSportsController implements Initializable {

    @FXML
    private AnchorPane backBtn;
    @FXML
    private TableColumn<Sport_Type, String> sportName;
    @FXML
    private TextField location;
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private Button submitBtn;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Sport_Type, String> sportLocation;
    @FXML
    private TableColumn<Sport_Type,Date> sportStart;
    @FXML
    private TableColumn<Sport_Type,Date> sportEnd;
    
    @FXML
    private TableView<Sport_Type> table;
    private TextField sport_name;
    @FXML
    private TextField nomSport;
    private ObservableList<Sport_Type> sports = FXCollections.observableArrayList();
    @FXML
    private Button deleteBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sportLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        sportStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        sportEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        sportName.setCellValueFactory(new PropertyValueFactory<>("name"));
        loadSportsFromDatabase();
        table.setItems(sports);
    }    
        private void loadSportsFromDatabase() {
        SportTypeCRUD dataAccess = new SportTypeCRUD();
        List<Sport_Type> sportlist = dataAccess.getAllSports();

        sports.addAll(sportlist);
    }

    @FXML
    private void comeback(ActionEvent event) {
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

    @FXML
    private void submit(ActionEvent event) {
        if(datep(start,end) == true){
            
        
    Sport_Type selectedSport = table.getSelectionModel().getSelectedItem();
    if (selectedSport != null) {
        String newName = nomSport.getText();
        String newLocation = location.getText();
        LocalDate newStartDate = start.getValue();
        LocalDate newEndDate = end.getValue();

        if (newName != null && newLocation != null && newStartDate != null && newEndDate != null) {
            // Update the selected Sport_Type
            selectedSport.setName(newName);
            selectedSport.setLocation(newLocation);
            // Update the dates (convert LocalDate to Date)
            selectedSport.setStartDate(java.sql.Date.valueOf(newStartDate));
            selectedSport.setEndDate(java.sql.Date.valueOf(newEndDate));

            // Now update this Sport_Type in the database
            SportTypeCRUD sportTypeCRUD = new SportTypeCRUD();
            boolean updated = sportTypeCRUD.updateSportType(selectedSport,newName,newLocation,newStartDate,newEndDate);

            if (updated) {
                show();
                nomSport.clear();
                location.clear();
                start.setValue(null);
                end.setValue(null);
                table.refresh();
            } else {
                displayError("Failed to update the Sport_Type in the database.");
            }
        } else {
            displayError("Please fill in all fields.");
        }
    } else {
        displayError("Please select a Sport_Type from the table.");
    }
}
        else{
            displayError("End Date should come after the Start Date.");
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
        alert.setContentText("Sport Type Updated Successfully");

        alert.showAndWait();
    }


    @FXML
private void removeSport(ActionEvent event) {
    Sport_Type selectedSport = table.getSelectionModel().getSelectedItem();
    System.out.println(selectedSport.getId());

    if (selectedSport != null) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to remove this Sport?");
        confirmation.setContentText("Click OK to proceed with the deletion.");

        Optional<ButtonType> result = confirmation.showAndWait();
        String query = "DELETE FROM sport_type WHERE id = ?";
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection connection = MyConnection.getInstance().getCnx()) {
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setInt(1, selectedSport.getId());
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        table.getItems().remove(selectedSport);
                    } else {
                        displayError("Failed to delete the Sport");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    displayError("Database error: " + ex.getMessage());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                displayError("Error opening a database connection: " + e.getMessage());
            }
        }
    } else {
        displayError("Please select the sport you want to delete.");
    }
}

    @FXML
    private void handleTableRowSelection(javafx.scene.input.MouseEvent event) {
         Sport_Type selectedSport = table.getSelectionModel().getSelectedItem();
    if (selectedSport != null) {
        nomSport.setText(selectedSport.getName());
        location.setText(selectedSport.getLocation());
  

    }
    
    
    }
    
    
}

    

    

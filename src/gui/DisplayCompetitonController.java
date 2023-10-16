package gui;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.CompetitionCRUD;
import entities.Competition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


public class DisplayCompetitonController implements Initializable {

    @FXML
    private AnchorPane backBtn;
    @FXML
    private TableView<Competition> tv;
    @FXML
    private TableColumn<Competition,String> name;
    @FXML
    private TableColumn<Competition,Date> sd;
    @FXML
    private TableColumn<Competition,Date> ed;
    @FXML
    private TableColumn<Competition,String> cg;
    @FXML
    private TableColumn<Competition,String> entryfee;
    @FXML
    private TableColumn<Competition,String> maxP;
    @FXML
    private TableColumn<Competition,String> status;
    @FXML
    private TableColumn<Competition,String> prize;
    @FXML
    private TableColumn<Competition,String> sport_type;
    @FXML
    private TableColumn<Competition,String> locationTxt;

    private ObservableList<Competition> competitions = FXCollections.observableArrayList();
    @FXML
    private Button deleteBtn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the table columns
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sd.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        ed.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        cg.setCellValueFactory(new PropertyValueFactory<>("competitionCategory"));
        entryfee.setCellValueFactory(new PropertyValueFactory<>("entryFee"));
        maxP.setCellValueFactory(new PropertyValueFactory<>("maxParticipants"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        prize.setCellValueFactory(new PropertyValueFactory<>("prizes"));
        sport_type.setCellValueFactory(new PropertyValueFactory<>("sportType"));
        locationTxt.setCellValueFactory(new PropertyValueFactory<>("location"));
        
        // Load competitions from the database and populate the table
        loadCompetitionsFromDatabase();

        // Set the items for the TableView
        tv.setItems(competitions);
    }

    private void loadCompetitionsFromDatabase() {
        CompetitionCRUD dataAccess = new CompetitionCRUD();
        List<Competition> competitionList = dataAccess.getAllCompetitions();
        competitions.addAll(competitionList);
    }

    @FXML
    private void removeCompetition(ActionEvent event) {
                Competition selectedCompetition = tv.getSelectionModel().getSelectedItem();
        if (selectedCompetition != null) {
            // Delete the selected competition from the database
            boolean deleted = deleteCompetition(selectedCompetition);
            
            if (deleted) {
                // Remove the selected competition from the TableView
                tv.getItems().remove(selectedCompetition);
            } else {
                // Handle deletion error
                // You can display an error message here
            }
        }
    }
}

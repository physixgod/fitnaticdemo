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
import static gui.PDFGenerator.printCompetitionToPDF;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import utils.MyConnection;


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
    @FXML
    private Button editBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private Button export;


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
        
        loadCompetitionsFromDatabase();
        tv.setItems(competitions);
    }

    private void loadCompetitionsFromDatabase() {
        CompetitionCRUD dataAccess = new CompetitionCRUD();
        List<Competition> competitionList = dataAccess.getAllCompetitions();
        competitions.addAll(competitionList);
    }
    private void refreshTable() {
        competitions.clear();
        CompetitionCRUD dataAccess = new CompetitionCRUD();
        List<Competition> competitionList = dataAccess.getAllCompetitions();
        competitions.addAll(competitionList);  
        tv.refresh();
    }

    @FXML
private void removeCompetition(ActionEvent event) throws SQLException {
    Competition selectedCompetition = tv.getSelectionModel().getSelectedItem();
    CompetitionCRUD Cr = new CompetitionCRUD();

    if (selectedCompetition != null) {
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Are you sure you want to remove this competition?");
        confirmation.setContentText("Click OK to proceed with the deletion.");

        Optional<ButtonType> result = confirmation.showAndWait();
        String query = "DELETE FROM Competition WHERE name = ?";
        if (result.isPresent() && result.get() == ButtonType.OK) {
        try (Connection connection = MyConnection.getInstance().getCnx();
            PreparedStatement ps = connection.prepareStatement(query)) {
           ps.setString(1, selectedCompetition.getName());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {        
                tv.getItems().remove(selectedCompetition);
            } else {
                displayError("Failed to delete the competition.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            displayError("Database error: " + ex.getMessage());
        }
    }} else {
        displayError("Please select the competition you want to delete.");
    }
    
}

    private void displayError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);

    alert.showAndWait();
}

    private boolean deleteCompetition(Competition selectedCompetition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private String showEditDialog(String currentStatus) {
    TextInputDialog dialog = new TextInputDialog(currentStatus);
    dialog.setTitle("Edit");
    dialog.setHeaderText("Edit Status");
    dialog.setContentText("New Status:");
    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
        
        return result.get();
    } else {
        return null;
    }
}
        public void setReturnButtonAction(EventHandler<ActionEvent> action) {
        returnBtn.setOnAction(action);
    }
    @FXML
    private void editCompetiotn(ActionEvent event) {
    Competition selectedCompetition = tv.getSelectionModel().getSelectedItem();
    String name = selectedCompetition.getName();
    String newStatus = showEditDialog(selectedCompetition.getStatus());
    System.out.println(name);
    if (newStatus != null) {
    try {
        Statement st = new MyConnection().getCnx().createStatement();
        String query = "UPDATE Competition SET status = '" + newStatus + "' WHERE name = '" + name + "'";

        int rowsAffected = st.executeUpdate(query);
        refreshTable();
    if (rowsAffected > 0) {
        tv.refresh();
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    } else {
        displayError("Failed to update competition status.");
    }
} catch (SQLException ex) {
    System.err.println(ex.getMessage());
}

    }
}

    @FXML
private void back(ActionEvent event) {
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
    private void exportTOPDF(ActionEvent event) {
        Competition selectedCompetition = tv.getSelectionModel().getSelectedItem();
        System.out.println(selectedCompetition.getName());
        System.out.println(selectedCompetition.getLocation());
        printCompetitionToPDF(selectedCompetition, "C:\\Users\\toufa\\OneDrive\\Bureau\\Competitions",selectedCompetition.getName());
    }
}
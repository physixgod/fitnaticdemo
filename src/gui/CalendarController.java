package gui;

import edu.esprit.entities.Calendrier;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CalendarController implements Initializable {

    @FXML
    private AnchorPane backBtn;
    @FXML
    private TableView<Calendrier> Calendrier;
    @FXML
    private TableColumn<Calendrier, Integer> idColumn; // Added idColumn
    @FXML
    private TableColumn<Calendrier, String> Lundi;
    @FXML
    private TableColumn<Calendrier, String> Mardi;
    @FXML
    private TableColumn<Calendrier, String> Mercredi;
    @FXML
    private TableColumn<Calendrier, String> Jeudi;
    @FXML
    private TableColumn<Calendrier, String> Vendredi;
    @FXML
    private TableColumn<Calendrier, String> Samedi;
    @FXML
    private Button returnBtn;
    @FXML
    private AnchorPane prizeTxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Calendrier.setFixedCellSize(50); // Set the height of a single row
Calendrier.prefHeightProperty().bind(Bindings.size(Calendrier.getItems()).multiply(50).add(30));
        Lundi.setCellValueFactory(new PropertyValueFactory<>("lundi"));
        Mardi.setCellValueFactory(new PropertyValueFactory<>("mardi"));
        Mercredi.setCellValueFactory(new PropertyValueFactory<>("mercredi"));
        Jeudi.setCellValueFactory(new PropertyValueFactory<>("jeudi"));
        Vendredi.setCellValueFactory(new PropertyValueFactory<>("vendredi"));
        Samedi.setCellValueFactory(new PropertyValueFactory<>("samedi"));

        // Populate the TableView with data
        populateCalendar();
    }

    // Method to populate the TableView with data from the database
    private void populateCalendar() {
        // Get the database connection instance
        MyConnection myConnection = MyConnection.getInstance();
        Connection conn = myConnection.getCnx();

        try {
            // Execute a query to retrieve calendar data
            // Modify the SQL query as needed to match your table structure
            String query = "SELECT id, lundi, mardi, mercredi, jeudi, vendredi, samedi FROM calendrier WHERE id = (SELECT MAX(id) FROM calendrier)";
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            // Populate the TableView with data
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lundi = resultSet.getString("lundi");
                String mardi = resultSet.getString("mardi");
                String mercredi = resultSet.getString("mercredi");
                String jeudi = resultSet.getString("jeudi");
                String vendredi = resultSet.getString("vendredi");
                String samedi = resultSet.getString("samedi");
                Calendrier.getItems().add(new Calendrier(id, lundi, mardi, mercredi, jeudi, vendredi, samedi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("menufitnatic.fxml"));
            Scene scene = new Scene(page);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

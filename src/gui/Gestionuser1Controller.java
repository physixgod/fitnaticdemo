package gui;

import edu.esprit.entities.Calendrier;
import edu.esprit.services.CalendrierCRUD;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Gestionuser1Controller implements Initializable {
Connection connection = MyConnection.getInstance().getCnx();
    @FXML
    private TableView<Calendrier> tableView;

    @FXML
    private TableColumn<Calendrier, String> lundiColumn;
    @FXML
    private TableColumn<Calendrier, String> mardiColumn;
    @FXML
    private TableColumn<Calendrier, String> mercrediColumn;
    @FXML
    private TableColumn<Calendrier, String> jeudiColumn;
    @FXML
    private TableColumn<Calendrier, String> vendrediColumn;
    @FXML
    private TableColumn<Calendrier, String> samediColumn;

    private ObservableList<Calendrier> calendrierList;
    private CalendrierCRUD calendrierCRUD;
    @FXML
    private TextField tabletext;
    @FXML
    private Button ajoutbtn;
    @FXML
    private Button suppbtn;
    @FXML
    private Button modifbtn;
    @FXML
    private Button btnSend;
    @FXML
    private ImageView refbtn;
    @FXML
    private Button menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                tableView.setFixedCellSize(80); // Set the height of a single row

        // Initialize columns
        lundiColumn.setCellValueFactory(new PropertyValueFactory<>("lundi"));
        lundiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lundiColumn.setOnEditCommit(event -> handleModify(event, lundiColumn));

        mardiColumn.setCellValueFactory(new PropertyValueFactory<>("mardi"));
        mardiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mardiColumn.setOnEditCommit(event -> handleModify(event, mardiColumn));

        mercrediColumn.setCellValueFactory(new PropertyValueFactory<>("mercredi"));
        mercrediColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mercrediColumn.setOnEditCommit(event -> handleModify(event, mercrediColumn));

        jeudiColumn.setCellValueFactory(new PropertyValueFactory<>("jeudi"));
        jeudiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        jeudiColumn.setOnEditCommit(event -> handleModify(event, jeudiColumn));

        vendrediColumn.setCellValueFactory(new PropertyValueFactory<>("vendredi"));
        vendrediColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        vendrediColumn.setOnEditCommit(event -> handleModify(event, vendrediColumn));

        samediColumn.setCellValueFactory(new PropertyValueFactory<>("samedi"));
        samediColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        samediColumn.setOnEditCommit(event -> handleModify(event, samediColumn));

        tableView.setEditable(true);

        calendrierCRUD = new CalendrierCRUD();
        calendrierList = FXCollections.observableArrayList();
        tableView.setItems(calendrierList);
    }

    private void handleModify(TableColumn.CellEditEvent<Calendrier, String> event, TableColumn<Calendrier, String> column) {
        Calendrier item = event.getRowValue();
        String newValue = event.getNewValue();

        if (column == lundiColumn) {
            item.setLundi(newValue);
        } else if (column == mardiColumn) {
            item.setMardi(newValue);
        } else if (column == mercrediColumn) {
            item.setMercredi(newValue);
        } else if (column == jeudiColumn) {
            item.setJeudi(newValue);
        } else if (column == vendrediColumn) {
            item.setVendredi(newValue);
        } else if (column == samediColumn) {
            item.setSamedi(newValue);
        }

    }

    @FXML
    private void handleAdd(ActionEvent event) {
        Calendrier newCalendrier = new Calendrier(0, "", "", "", "", "", "");
        calendrierList.add(newCalendrier);
    }

    @FXML
    private void handleModify(ActionEvent event) {
    }

    @FXML
    private void senDV(MouseEvent event) {
        
        Calendrier r=tableView.getSelectionModel().getSelectedItem();
        calendrierCRUD.ajouterCalendrier(r);
        tableView.getItems().clear();
    }

    @FXML
    private void refresh(MouseEvent event) {
       tableView.getItems().clear();
    }

    @FXML
    private void tomenu(ActionEvent event) {
                                                                try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menufitnatic.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

   
}

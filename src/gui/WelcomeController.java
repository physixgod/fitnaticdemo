package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class WelcomeController {

    @FXML
    private Button sign;
    @FXML
    private Button login;
    @FXML
    private Text welcometxt;
    @FXML
    private ComboBox<String> languagebox;

    public void initialize() {
     
        languagebox.getItems().addAll("FR", "ENG");
       
        languagebox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("FR")) {
                welcometxt.setText("Bienvenue dans Fitnatic, l'application qui vous ouvrira les portes d'un univers dédié au fitness");
            } else if (newValue.equals("ENG")) {
                welcometxt.setText("Join the Fitnatic community and pursue your fitness goals with us");
            }
        });
    }

    @FXML
    private void sign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacelogin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void slectlanguage(ActionEvent event) {
      
    }

    private void b(ActionEvent event) {
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

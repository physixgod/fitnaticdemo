package GUI;

import Services.CaloriqueCrud;
import entities.Calorique;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import utils.hassenConnection;
import Services.ImcCrud;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Diet2Controller implements Initializable {

    @FXML
    private RadioButton radioGain;
    @FXML
    private RadioButton radioPerte;
    @FXML
    private RadioButton radiolactose;
    @FXML
    private RadioButton radiogluten;
    @FXML
    private RadioButton radioVegetrian;
    @FXML
    private RadioButton radioeger;
    @FXML
    private RadioButton radiermoder;
    @FXML
    private RadioButton radrotractif;
    @FXML
    private RadioButton radioextremen;
    @FXML
    private Button btnCaloriques;
    @FXML
    private RadioButton radiofaible;
    @FXML
    private RadioButton radiomodere;
    @FXML
    private RadioButton radioeleve;
    @FXML
    private Pane regime;
    @FXML
    private Pane activite;
    @FXML
    private Pane stress;

    // Créer des groupes ToggleGroup pour chaque ensemble de boutons radio
    private ToggleGroup besoinToggleGroup = new ToggleGroup();
    private ToggleGroup alimentationToggleGroup = new ToggleGroup();
    private ToggleGroup activiteToggleGroup = new ToggleGroup();
    private ToggleGroup stressToggleGroup = new ToggleGroup();
    @FXML
    private Button btnretour;

    public void initialize(URL url, ResourceBundle rb) {
        // Assigner les boutons radio aux groupes ToggleGroup
        radioGain.setToggleGroup(besoinToggleGroup);
        radioPerte.setToggleGroup(besoinToggleGroup);

        radiolactose.setToggleGroup(alimentationToggleGroup);
        radiogluten.setToggleGroup(alimentationToggleGroup);
        radioVegetrian.setToggleGroup(alimentationToggleGroup);

        radioeger.setToggleGroup(activiteToggleGroup);
        radiermoder.setToggleGroup(activiteToggleGroup);
        radrotractif.setToggleGroup(activiteToggleGroup);
        radioextremen.setToggleGroup(activiteToggleGroup);

        radiofaible.setToggleGroup(stressToggleGroup);
        radiomodere.setToggleGroup(stressToggleGroup);
        radioeleve.setToggleGroup(stressToggleGroup);

          

        
    }
@FXML
private void CalculerBesoinCalorique(ActionEvent event) {
    try (        Connection connection = hassenConnection.getInstance().getConnection();) {
        // Exemple d'utilisation des groupes ToggleGroup pour obtenir les sélections
        String objectif = "";
        String regimeAlimentaire = "";
        String activite = "";
        String niveauStress = "";
         // Vérifiez si au moins un bouton radio de chaque groupe est sélectionné
    if (besoinToggleGroup.getSelectedToggle() == null ||
        alimentationToggleGroup.getSelectedToggle() == null ||
        activiteToggleGroup.getSelectedToggle() == null ||
        stressToggleGroup.getSelectedToggle() == null) {
        showAlert("Veuillez sélectionner une option dans chaque groupe.");
        return;
    }

        // Obtenez les valeurs à partir des composants interactifs
        if (besoinToggleGroup.getSelectedToggle() != null) {
            RadioButton selectedBesoin = (RadioButton) besoinToggleGroup.getSelectedToggle();
            objectif = selectedBesoin.getText();
        }

        if (alimentationToggleGroup.getSelectedToggle() != null) {
            RadioButton selectedAlimentation = (RadioButton) alimentationToggleGroup.getSelectedToggle();
            regimeAlimentaire = selectedAlimentation.getText();
        }

        if (activiteToggleGroup.getSelectedToggle() != null) {
            RadioButton selectedActivite = (RadioButton) activiteToggleGroup.getSelectedToggle();
            activite = selectedActivite.getText();
        }

        if (stressToggleGroup.getSelectedToggle() != null) {
            RadioButton selectedStress = (RadioButton) stressToggleGroup.getSelectedToggle();
            niveauStress = selectedStress.getText();
        }

        // Vous pouvez appeler la méthode pour calculer les besoins caloriques en fonction des sélections
        Calorique calorique = new Calorique();
        calorique.setObjectif(objectif);
        calorique.setRegimeAlimentaire(regimeAlimentaire);
        calorique.setActivite(activite);
        calorique.setNiveauStress(niveauStress);

        double besoinsCaloriques = calorique.calculerBesoinsCaloriques();

     
        
        
     // Créez une instance de CaloriqueCrud
CaloriqueCrud caloriqueCrud = new CaloriqueCrud();

// ... Votre code pour obtenir les valeurs de calorique et appeler la méthode insertCalorique
caloriqueCrud.insertCalorique(calorique);
caloriqueCrud.updateAllCaloriqueBesoinsCalorique(besoinsCaloriques);
            try {
        // Chargez l'interface CalculerImc.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultatCalorique.fxml"));
        Parent root = loader.load();

        // Créez une nouvelle scène
        Scene scene = new Scene(root);

        // Obtenez la scène actuelle depuis n'importe quel nœud source
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        // Réglez la nouvelle scène sur la fenêtre de la scène actuelle
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }


        
        // Vous pouvez effectuer d'autres actions en fonction des besoins caloriques ici
    } catch (SQLException ex) {
        Logger.getLogger(Diet2Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
      }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void retourcalorique(ActionEvent event) {
    }
}

   
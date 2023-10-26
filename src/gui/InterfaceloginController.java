package gui;

import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;
import javafx.scene.text.Text;

public class InterfaceloginController implements Initializable {

    @FXML
    private Button savebtn;
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenomuser;
    @FXML
    private TextField emailuser;
    @FXML
    private DatePicker dateuser;
    @FXML
    private ComboBox<String> genreuser;
    @FXML
    private PasswordField mdpuser;
    @FXML
    private PasswordField mdp2user;
    @FXML
    private Button test;

    // Liste de mots inappropriés
    private static final List<String> motsInappropries = Arrays.asList("fuck", "shit", "putain","merde");
    @FXML
    private ComboBox<String> logboxlang;
    @FXML
    private Text prenomtexxt;
    @FXML
    private Text mailtexxt;
    @FXML
    private Text genretexxt;
    @FXML
    private Text nomtexxt;
    @FXML
    private Text datetexxt;
    @FXML
    private Text mdp1texxt;
    @FXML
    private Text mdp2texxt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         logboxlang.getItems().addAll("FR", "ENG");
       
        logboxlang.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("FR")) {
                nomtexxt.setText("nom");
                mailtexxt.setText("e-mail");
                genretexxt.setText("genre");
                mdp1texxt.setText("mot de passe");
                mdp2texxt.setText("confimer mot de passe");
                prenomtexxt.setText("prénom");
                datetexxt.setText("DatedeNaissance");
                
                
                
                
                
            } else if (newValue.equals("ENG")) {
                nomtexxt.setText("LastName");
                 mailtexxt.setText("e-mail");
                genretexxt.setText("gender");
                mdp1texxt.setText("Password");
                mdp2texxt.setText("Confirm password");
                prenomtexxt.setText("Firstname");
                datetexxt.setText("BirthDate");
                
            }
        });
        ObservableList<String> options = FXCollections.observableArrayList("Homme", "Femme");
        genreuser.setItems(options);
    }

    PersonneCRUD PC = new PersonneCRUD();

    @FXML
    private void savepersonne(MouseEvent event) throws IOException {
        String nomValue = nomuser.getText();
        String prenomValue = prenomuser.getText();
        String emailValue = emailuser.getText();
        String genreValue = genreuser.getValue();
        LocalDate selectedDate = dateuser.getValue();
        String passwordValue = mdpuser.getText();
        String passwordConfirmValue = mdp2user.getText();

        if (nomValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Nom' est requis.");
            return;
        }

        if (prenomValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Prénom' est requis.");
            return;
        }

        if (emailValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Email' est requis.");
            return;
        }

        if (genreValue == null) {
            afficherMessageErreur("Champ manquant", "Le champ 'Genre' est requis.");
            return;
        }

        if (selectedDate == null) {
            afficherMessageErreur("Champ manquant", "Le champ 'Date de naissance' est requis.");
            return;
        }

        if (passwordValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Mot de passe' est requis.");
            return;
        }

        if (!passwordValue.equals(passwordConfirmValue)) {
            afficherMessageErreur("Mot de passe non conforme", "Les mots de passe ne correspondent pas.");
            return;
        }

        if (!estEmailValide(emailValue)) {
            afficherMessageErreur("Email invalide", "L'adresse e-mail n'est pas valide.");
            return;
        }

        if (contientMotsInappropries(nomValue) || contientMotsInappropries(prenomValue) || contientMotsInappropries(emailValue) || contientMotsInappropries(passwordValue)) {
            afficherMessageErreur("Mot inapproprié", "Veuillez ne pas utiliser de mots inappropriés.");
            return;
        }

        Personne p = new Personne(0, nomValue, prenomValue, emailValue, genreValue, selectedDate, passwordValue, passwordConfirmValue);

        try {
            PC.ajouterPersonne(p);
            afficherMessageInformation("Bienvenue " + nomValue);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceloginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        Object root = FXMLLoader.load(getClass().getResource("gestionuser1.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean estEmailValide(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void afficherMessageErreur(String titre, String contenu) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    public void afficherMessageInformation(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void logt(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("gestionuser1.fxml"));
            Scene scene = new Scene(page);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void log(ActionEvent event) {
    }

    private boolean contientMotsInappropries(String texte) {
        for (String mot : motsInappropries) {
            if (texte.toLowerCase().contains(mot.toLowerCase())) {
                return true; 
            }
        }
        return false; 
    }

}

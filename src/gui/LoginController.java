package gui;
import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneCRUD;
import java.io.IOException;
import javafx.scene.Cursor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField emaillog;
    @FXML
    private PasswordField mdplogin;
    @FXML
    private Button loginn;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    @FXML
    private void connecter(ActionEvent event) {
        String emailValue = emaillog.getText();
        String passwordValue = mdplogin.getText();

        
        PersonneCRUD PC = new PersonneCRUD();
        try {
            Personne personne = PC.authentifier(emailValue, passwordValue);

            if (personne != null) {
                
                String message = "Welcome, " + personne.getNom() + "!";
                afficherMessageInformation(message);
                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("gestionuser1.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();

    }
            } else {
                
                afficherMessageErreur("Erreur", "Email ou mot de passe incorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherMessageErreur("Erreur", "Erreur de base de données");
        }
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
    private void openmodifpass(MouseEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifpass.fxml"));
            Parent root = loader.load();

          
            Scene scene = new Scene(root);

           
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

            
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
        

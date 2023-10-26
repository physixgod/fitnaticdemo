package javafx_forum1.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx_forum1.entities.Publication;
import javafx_forum1.services.PublicationService;

public class PubsController implements Initializable {
     //@FXML
   // private TextField userTextField;

    @FXML
    private ChoiceBox<String> choixPub;
    @FXML
    private TextField User;
private PublicationService publicationService = new PublicationService();
    @FXML
    private Button addPub;
    @FXML
    private FontAwesomeIconView BACK;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialize method called.");
    
        choixPub.getItems().addAll("Yoga", "Footing", "FootKids", "Meditation", "Karate", "Musculation");
        choixPub.setValue("Yoga");
    }

   /* private void ajouterPublication() {
        String username = userTextField.getText();
        String choix = choixPub.getValue();

        // Créez une nouvelle instance de Publication
        Publication nouvellePublication = new Publication(username, choix);

        // Appelez la méthode de votre classe de service/DAO pour insérer la publication
        PublicationService publicationService = new PublicationService(); // Assurez-vous d'initialiser votre service correctement
        publicationService.CreerPublication(nouvellePublication);

        // Vous pouvez également réinitialiser les champs de saisie après l'ajout réussi si nécessaire
        userTextField.clear();
        choixPub.setValue(null);
    }*/

    @FXML
    private void btnADD(ActionEvent event) {
         String username = User.getText();
        String choix = choixPub.getValue();
        

        // Créez une nouvelle instance de Publication
        Publication nouvellePublication = new Publication(username, choix);

        // Appelez la méthode de votre classe de service/DAO pour insérer la publication
       // PublicationService publicationService = new PublicationService(); // Assurez-vous d'initialiser votre service correctement
        publicationService.CreerPublication(nouvellePublication);

        // Vous pouvez également réinitialiser les champs de saisie après l'ajout réussi si nécessaire
        User.clear();
        choixPub.setValue(null);
        
    }

    @FXML
    private void back(MouseEvent event){ 
                                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("Choix.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
}

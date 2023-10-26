/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import  entities.Calorique;
import Services.CaloriqueCrud;
import javafx.scene.control.Label;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hassen
 */
public class ResultatCaloriqueController implements Initializable {
    

@FXML
    private Button send;
    
@FXML
private TextField lblptdej;
@FXML
private TextField lbldej;
@FXML
private TextField lbldinner;
@FXML
private TextField lblsnack;
    @FXML
    private TextField lblbesoin;
    @FXML
    private Button chat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { setBesoinsCaloriques();
    }

    public void setBesoinsCaloriques() {
        // Appeler la méthode pour obtenir la valeur du besoin calorique
        CaloriqueCrud caloriqueCrud = new CaloriqueCrud();
        Calorique calorique = caloriqueCrud.getLatestBesoinCaloriques();
        double besoinsCaloriques = calorique.getBesoinsCaloriques();

        // Mettre à jour l'interface utilisateur
        lblbesoin.setText(String.valueOf(besoinsCaloriques));

        // Structure conditionnelle pour mettre à jour d'autres éléments de l'interface
        if (besoinsCaloriques >= 1550 && besoinsCaloriques <= 1900) {
            lblptdej.setText("Petit-déjeuner: \n" +
                    "- Smoothie aux fruits (200 kcal)\n" +
                    "- Pain complet avec beurre d'amande (180 kcal)");

            lbldej.setText(" \n" +
                    "- Salade verte avec du poulet grillé (250 kcal)\n" +
                    "- Quinoa cuit (220 kcal)\n" +
                    "- Légumes cuits à la vapeur (80 kcal)");

            lbldinner.setText("Dîner: \n" +
                    "- Saumon grillé (350 kcal)\n" +
                    "- Riz brun cuit (200 kcal)\n" +
                    "- Légumes rôtis (120 kcal)");

            lblsnack.setText("Collations: \n" +
                    "- Yaourt grec faible en gras (90 kcal)\n" +
                    "- Noix mixtes (amandes, noix, noix de cajou) (150 kcal)");
        } else if (besoinsCaloriques >= 1901 && besoinsCaloriques <= 2350) {
            lblptdej.setText("Petit-déjeuner: \n" +
                    "- Smoothie aux fruits (200 kcal)\n" +
                    "- Pain complet grillé avec du beurre d'amande (180 kcal)");

            lbldej.setText("Déjeuner: \n" +
                    "- Salade de thon avec des légumes frais (300 kcal)\n" +
                    "- Quinoa cuit à la vapeur (220 kcal)\n" +
                    "- Fruits (pomme ou orange) (90 kcal)");

            lbldinner.setText("Dîner: \n" +
                    "- Poulet rôti aux herbes (350 kcal)\n" +
                    "- Patates douces cuites au four (180 kcal)\n" +
                    "- Légumes verts cuits à la vapeur (100 kcal)");

            lblsnack.setText("Collations:  \n" +
                    "- Carottes avec houmous (150 kcal)\n" +
                    "- Yaourt nature (120 kcal)");
        } else if (besoinsCaloriques >= 2351 && besoinsCaloriques <= 2700) {
            lblptdej.setText("Petit-déjeuner: \n" +
                    "- Muesli avec yaourt et fruits (280 kcal)\n" +
                    "- Pain complet avec beurre d'arachide (220 kcal)");

            lbldej.setText("Déjeuner: \n" +
                    "- Salade de poulet césar (350 kcal)\n" +
                    "- Riz basmati cuit (250 kcal)\n" +
                    "- Légumes sautés à l'ail (100 kcal)");

            lbldinner.setText("Dîner:  \n" +
                    "- Poisson grillé (350 kcal)\n" +
                    "- Quinoa cuit (220 kcal)\n" +
                    "- Brocoli à la vapeur (80 kcal)");

            lblsnack.setText("Collations:  \n" +
                    "- Barre de céréales aux noix (150 kcal)\n" +
                    "- Smoothie aux épinards (120 kcal)");

        }else if (besoinsCaloriques >= 2701 && besoinsCaloriques <= 3000) {
            // Ajoutez ici la logique pour la plage 4
            lblptdej.setText("Petit-déjeuner: \n" +
                    "- Smoothie protéiné (banane, protéine en poudre, épinards) (300 kcal)\n" +
                    "- Avocat sur du pain complet (250 kcal)");

            lbldej.setText("Déjeuner: \n" +
                    "- Salade de quinoa aux légumes et aux pois chiches (400 kcal)\n" +
                    "- Poitrine de dinde grillée (350 kcal)\n" +
                    "- Légumes rôtis (150 kcal)");

            lbldinner.setText("Dîner: \n" +
                    "- Steak de saumon avec sauce au citron (400 kcal)\n" +
                    "- Riz sauvage cuit (300 kcal)\n" +
                    "- Asperges cuites à la vapeur (100 kcal)");

            lblsnack.setText("Collations: \n" +
                    "- Yaourt grec à la noix de coco (180 kcal)\n" +
                    "- Poignée de noix et de fruits secs (amandes, noix de cajou) (200 kcal)");
        
}


    }    
   
   
   
  @FXML
    private void notifications(ActionEvent event) {
    
    try {
        // Chargez l'interface CalculerImc.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("planmail.fxml"));
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
}

    @FXML
    private void openchatbot(ActionEvent event) {
            try {
        // Chargez l'interface CalculerImc.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("chatbot.fxml"));
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
    }


}

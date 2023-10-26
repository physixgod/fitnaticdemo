package GUI;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import java.io.IOException;
import api.Weather;
import java.net.MalformedURLException;


import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;

public class ChatbotController implements Initializable {

    @FXML
    private TextField t1;

    @FXML
    private TextArea t2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation du contrôleur, si nécessaire
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        // Implémentez ici la logique de changement de thème si nécessaire
    }

    @FXML
    private void txtEntrer(ActionEvent event) {
        String utext = t1.getText();

        t2.appendText("\nYou: " + utext + "\n\n");
        
        
        if (utext.contains("meteo svp")) {
        // L'utilisateur demande la météo, appelez la fonction pour obtenir les données météorologiques
        String city = "Tunisie"; // Remplacez par la ville souhaitée
        String apiKey = "dbb5609448aae18ebb703e5360746ae2"; // Remplacez par votre clé Weatherbit API

        // Obtenez les données météorologiques
        String weatherData = Weather.getWeatherData("dbb5609448aae18ebb703e5360746ae2", city);
        // Parsez les données météorologiques pour obtenir la température et la description, supposons que vous avez des méthodes pour le faire
        String temperature = Weather.parseTemperature(weatherData);
        String description = Weather.parseDescription(weatherData);
    
        // Affichez les informations météorologiques dans la zone de texte t2
        botSay("La température à " + city + " est " + temperature + "°C. Conditions météorologiques : " + description);

        if (utext.contains("parle moi de l'application")) {
            botSay("Fitnatic est une application didiée au fitness et éducation physique");
        } else if (utext.contains("salut cava") || utext.contains("cv ?")) {
            int decide = (int) (Math.random() * 2 + 1);
            if (decide == 1) {
                botSay("Je vais bien merci!");
            } else if (decide == 2) {
                botSay("Pas mal");
            }
        } else if (utext.contains("qui etes vous") || utext.contains("que faites-vous?") || utext.contains("que se passe-t-il?")) {
            int decide = (int) (Math.random() * 4 + 1);
            if (decide == 1) {
                botSay("je suis un chatbot");
            } else if (decide == 2) {
                botSay("lis ma réponse précédente !");
            }
        } else if (utext.contains("qui t'a développé ?") || utext.contains("qui t'a créé ?")) {
            botSay("Eh bien ! Je suis créé par Hassen Kaffel. Il est mon propriétaire et travaille toujours sur moi pour en faire le meilleur bot !");
        } else if (utext.contains("je peux calculer mon IMC")) {
            botSay("Bien sûr, Monsieur !");
            // Vous pouvez ajouter ici la logique pour calculer l'IMC si nécessaire.
        } else {
            int decide = (int) (Math.random() * 3 + 1);
            if (decide == 1) {
                botSay("Je n'ai pas compris !");
            } else if (decide == 2) {
                botSay("S'il vous plaît, répétez-le");
            } else if (decide == 3) {
                botSay("????");
            }
        }

        t1.clear();
    }

    }   private void botSay(String message) {
        t2.appendText("Bot: " + message + "\n\n");
    }
}

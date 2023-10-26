/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.interfaces;

import com.fitnatic.stat.StatistiquesManager;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class PageReclamationStatController implements Initializable {
    @FXML
    private CheckBox checkboxSatisfait;
    @FXML
    private CheckBox checkboxMoyen;
    @FXML
    private CheckBox checkboxInsatisfait;
    @FXML
    private BarChart<String, Number> barChart;

    private StatistiquesManager statistiquesManager;
    @FXML
    private Button retour_stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      // Initialisez le gestionnaire de statistiques avec les composants du contrôleur
     statistiquesManager = new StatistiquesManager(barChart, checkboxSatisfait, checkboxMoyen, checkboxInsatisfait);

    }    


    @FXML
    private void satisfait(ActionEvent event) {
    }

    @FXML
    private void moyen(ActionEvent event) {
    }

    @FXML
    private void insatisfait(ActionEvent event) {
    }

    @FXML
    private void retour(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageReclamation.fxml"));
            Parent root = loader.load();
            PageReclamationController controller = loader.getController();

            // Vous pouvez également passer des données à PageReclamationController si nécessaire
            // Par exemple, vous pouvez utiliser la méthode setSomeData(data) dans PageReclamationController.

            // Obtenez la scène actuelle à partir de n'importe quel composant de votre interface
            Scene scene = ((Node) event.getSource()).getScene();

            // Remplacez la racine de la scène actuelle par la nouvelle racine (PageReclamation)
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class MenufitnaticController implements Initializable {

    @FXML
    private Button Competition;
    @FXML
    private Button REC;
    @FXML
    private Button REP;
    @FXML
    private Button Act;
    @FXML
    private Button FORM;
    @FXML
    private Button ggg;
    @FXML
    private Button ca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToCompetition(ActionEvent event) {
                                                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menuCompetition.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void goToReclamations(ActionEvent event) {
        try {
 
        Parent page =FXMLLoader.load(getClass().getResource("/com/fitnatic/interfaces/PageReclamation.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void goToReponse(ActionEvent event) {
         try {
 
        Parent page =FXMLLoader.load(getClass().getResource("/com/fitnatic/interfaces/PageReponse.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void goToActivite(ActionEvent event) {
                 try {
 
        Parent page =FXMLLoader.load(getClass().getResource("/edu/esprit/gui/treeviewact.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML 
    private void goToForum(ActionEvent event) {
                        try {
 
        Parent page =FXMLLoader.load(getClass().getResource("/javafx_forum1/gui/Choix.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void gotoalimentaire(ActionEvent event) {
                              try {
 
        Parent page =FXMLLoader.load(getClass().getResource("CalculerImc.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
        
    }

    @FXML
    private void goToCalendrier(ActionEvent event) {
                                   try {
 
        Parent page =FXMLLoader.load(getClass().getResource("calendar.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class fitwindow extends Application {

    private Stage primaryStage;

@Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("treeviewact.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            TreeviewactController firstController = loader.getController();
            primaryStage.setTitle("Activities Table");
            primaryStage.setScene(scene);
            primaryStage.show();
             
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    


    public static void main(String[] args) {
        launch(args);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author OUMAIMA
 */
public class JavaFX_Forum1 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/javafx_forum1/gui/Choix.fxml"));
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Le Forum");
            }
        });
        
         // Créez un conteneur VBox pour votre interface
        //VBox Root = new VBox();

        // Chargez l'image depuis le fichier dans votre projet
        //Image image = new Image(getClass().getResourceAsStream("forumSP.png"));

        // Créez un ImageView pour afficher l'image
        //ImageView imageView = new ImageView(image);

        // Ajoutez l'ImageView à votre interface
        //Root.getChildren().add(imageView);

   
        
       // StackPane root = new StackPane();
       // root.getChildren().add(btn);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Le Forum");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

       

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

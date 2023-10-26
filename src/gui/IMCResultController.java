package GUI;

import gui.Imcpdf;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IMCResultController implements Initializable {

    @FXML
    private Label txtimc;
    @FXML
    private Label txtimc1;
    @FXML
    private Label labelimc;
    @FXML
    private Label txtimc14;
    @FXML
    private Button btnPlan;
    @FXML
    private Button btnrtr;
    
    @FXML
    private Pane txtbonne;
    @FXML
    private Pane txtrisque;
    @FXML
    private Pane txtsous;
    @FXML
    private Button IMC;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vous pouvez initialiser des éléments ici si nécessaire
    }

    // Méthode pour définir la valeur de l'étiquette IMC
    public void setIMC(double imc) {
        txtimc.setText(String.valueOf(imc));
    }

    // Méthode pour définir la catégorie IMC
    public void setCategorieIMC(String categorieIMC) {
        txtimc1.setText(categorieIMC);
        if (categorieIMC.equals("Poids normal")) {
                btnPlan.setVisible(false);
                txtsous.setVisible(false);
                txtbonne.setVisible(true);
                txtrisque.setVisible(false);
        } else if (categorieIMC.equals("Sous-poids")) {
                  btnPlan.setVisible(true);
                  txtsous.setVisible(true);
                  txtbonne.setVisible(false);
                  txtrisque.setVisible(false);
   
        } else if (categorieIMC.equals("Surpoids") || categorieIMC.equals("Obésité")) {
                btnPlan.setVisible(true);
                txtsous.setVisible(false);
                txtbonne.setVisible(false);
                txtrisque.setVisible(true);
} }
    // Gérer tout autre cas ici (si nécessaire)



    // Méthode pour définir le poids actuel
    public void setPoidsActuel(double poidsActuel) {
        labelimc.setText(" " + String.valueOf(poidsActuel));
    }

    // Méthode pour définir le poids idéal
    public void setPoidsIdeal(double poidsIdeal) {
        txtimc14.setText("  " + String.valueOf(poidsIdeal));
    }

    @FXML
   
   
          private void Plan(ActionEvent event) {
    try {
        // Chargez le fichier FXML de la nouvelle interface (remplacez "VotreFichierFXML.fxml" par le chemin réel)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Diet2.fxml"));
        Parent root = loader.load();

        // Créez une nouvelle scène avec la nouvelle interface
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Définissez le propriétaire de la nouvelle fenêtre (cela la rendra modale)
        Stage primaryStage = (Stage) btnPlan.getScene().getWindow();
        stage.initOwner(primaryStage);

        // Définissez le titre de la nouvelle fenêtre
        stage.setTitle("Nouvelle Interface");

        // Affichez la nouvelle fenêtre
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
          }

   @FXML
private void retour(ActionEvent event) {
    try {
        // Chargez l'interface CalculerImc.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculerImc.fxml"));
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
    private void IMCbtn(ActionEvent event) {
        String poidsActuelle = labelimc.getText();
        String imc=txtimc.getText();
        String category=txtimc1.getText();
        String poidsIdeale=txtimc14.getText();
        Imcpdf i= new Imcpdf();
        
i.printInformationToPDF(poidsActuelle, imc, category, poidsIdeale, "C:\\Users\\toufa\\OneDrive\\Bureau\\IMC", "IMC");
    }
    }
    



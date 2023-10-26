package GUI;

import Services.ImcCrud;
import entities.Imc;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class CalculerImcController implements Initializable {

    @FXML
    private Spinner<Double> txtnom1;
    @FXML
    private Spinner<Double> txtnom11;
    @FXML
    private ComboBox<String> combo_sexe;
    @FXML
    private DatePicker txtage;
    @FXML
    private Button btnavimc;
    @FXML
    private Button btncretour;
    @FXML
    private Button btnhome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_sexe.getItems().addAll("Homme", "Femme");

        SpinnerValueFactory<Double> tailleFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 500, 0);
        txtnom1.setValueFactory(tailleFactory);
        txtnom1.setEditable(true);

        txtnom1.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                txtnom1.increment(0);
            }
        });
        txtnom1.getValueFactory().setConverter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                return value != null ? value.toString() : "0";
            }

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }
        });

        SpinnerValueFactory<Double> poidsFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 500, 0);
        txtnom11.setValueFactory(poidsFactory);
        txtnom11.setEditable(true);

        txtnom11.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                txtnom11.increment(0);
            }
        });
        txtnom11.getValueFactory().setConverter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                return value != null ? value.toString() : "0";
            }

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }
        });
    }

    @FXML
    private void Calculer1(ActionEvent event) {
        System.out.println("Bouton Calculer1 cliqué.");

        String sexe = combo_sexe.getValue();
        double taille, poids, age;

        if (sexe == null || sexe.isEmpty()) {
            showAlert("Veuillez choisir le sexe (Homme ou Femme).");
            return;
        }

        try {
            taille = txtnom1.getValue();
            poids = txtnom11.getValue();
        } catch (NumberFormatException e) {
            showAlert("Erreur de format : Assurez-vous de saisir des nombres valides.");
            return;
        }

       LocalDate dateNaissance = txtage.getValue();
        if (dateNaissance == null) {
           showAlert("Veuillez choisir une date de naissance.");
              return;
                }

        LocalDate currentDate = LocalDate.now();
        age = Period.between(dateNaissance, currentDate).getYears();


        Imc personne = new Imc(sexe, taille, poids, (int) age);
        personne.calculerIMC();
        personne.calculerPoidsIdeal();

        double imc = personne.getIMC();
        double poidsActuel = personne.getPoids();
        double poidsIdeal = personne.getPoidsIdeal();
        String categorieIMC = personne.getCategorieIMC();

        ImcCrud imcCrud = new ImcCrud();
        if (imcCrud.insertImc(personne)) {
            System.out.println("Données IMC insérées avec succès dans la base de données.");
        } else { 
            System.out.println("Erreur lors de l'insertion des données IMC dans la base de données.");
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IMCResult.fxml"));
            Parent root = loader.load();

            IMCResultController resultController = loader.getController();
            resultController.setIMC(imc);
            resultController.setCategorieIMC(categorieIMC);
            resultController.setPoidsActuel(poidsActuel);
            resultController.setPoidsIdeal(poidsIdeal);

            Stage stage = new Stage();
            stage.setTitle("Résultats IMC");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void avantimc(ActionEvent event) {
    }

    @FXML
    private void imcretour(ActionEvent event) {
                              try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menufitnatic.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void Home(ActionEvent event) {
    }
}

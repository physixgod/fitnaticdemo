package gui;

import edu.esprit.utils.MyConnection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ModifpassController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField verificationCodeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button sendbtn;

    private Connection connection;
    private String code = generateRandomVerificationCode();
    private static final List<String> motsInappropries = Arrays.asList("fuck", "shit", "putain", "merde");
    private int motsInappropriésCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MyConnection.getInstance().getCnx();
    }

    @FXML
    private void confirmer(ActionEvent event) {
        if (verificationCodeField.getText().equals(code)) {
            String email = emailField.getText();
            String newPassword = passwordField.getText();

            if (contientMotsInappropries(email) || contientMotsInappropries(newPassword)) {
                motsInappropriésCount++;

                if (motsInappropriésCount >= 3) {
                    bannirUtilisateur(email);
                    return;
                }
                displayMessage("Mot Inapproprié", "Veuillez ne pas utiliser de mots inappropriés.");
                return;
            }

            try {
                String query = "UPDATE personne SET motDePasse = ?, motdePasse2 = ? WHERE email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, newPassword);
                preparedStatement.setString(3, email);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    displayMessage("Mot de passe mis à jour", "Votre mot de passe a été mis à jour avec succès.");
                } else {
                    displayMessage("Erreur de mise à jour", "Impossible de mettre à jour le mot de passe. Veuillez vérifier votre email.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                displayMessage("Erreur de base de données", "Une erreur s'est produite lors de la mise à jour du mot de passe.");
            }
        } else {
            displayMessage("Code invalide", "Le code de vérification est incorrect. Veuillez réessayer.");
        }
    }

    @FXML
    private void send(ActionEvent event) {
        sendConfirmationEmail("khalil@gmail.com");
    }

    private String generateRandomVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    private void sendConfirmationEmail(String toEmail) {String host = "sandbox.smtp.mailtrap.io";
        String username = "9ec65c94038977";
        String password = "24c21421688597";
        String fromEmail = "admin@fitnatic.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Code de vérification");

            message.setText("Voici votre code de vérification : " + code);

            Transport.send(message);

            System.out.println("Email envoyé avec succès à : " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
        
        
    }

    private void displayMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean contientMotsInappropries(String texte) {
        for (String mot : motsInappropries) {
            if (texte.toLowerCase().contains(mot.toLowerCase())) {
                return true; 
            }
        }
        return false; 
    }

    private void bannirUtilisateur(String email) {
        try {
            String query = "DELETE FROM personne WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                displayMessage("Utilisateur Banni", "Vous avez été banni de notre application.");
            } else {
                displayMessage("Erreur de suppression", "Impossible de supprimer le compte utilisateur. Veuillez vérifier votre email.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayMessage("Erreur de base de données", "Une erreur s'est produite lors de la suppression du compte utilisateur.");
        }
    }
}

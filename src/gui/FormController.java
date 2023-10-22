/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Competition;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CompetitionCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class FormController implements Initializable {

    @FXML
    private AnchorPane backBtn;
    @FXML
    private Button back;
    @FXML
    private TextField Competition1;
    @FXML
    private TextField start1;
    @FXML
    private TextField end1;
    @FXML
    private TextField category1;
    @FXML
    private TextField entryfee1;
    @FXML
    private TextField participants1;
    @FXML
    private TextField prize1;
    @FXML
    private Button rd1;
    @FXML
    private Button ev1;
    @FXML
    private Button p1;
    @FXML
    private TextField location1;
    @FXML
    private TextField Competition2;
    @FXML
    private TextField Competition111;
    @FXML
    private TextField start2;
    @FXML
    private TextField end2;
    @FXML
    private TextField category2;
    @FXML
    private TextField entryfee2;
    @FXML
    private TextField participants2;
    @FXML
    private TextField prize2;
    @FXML
    private Button rd2;
    @FXML
    private Button ev2;
    @FXML
    private Button p2;
    @FXML
    private TextField location2;
    @FXML
    private TextField sport1;
    @FXML
    private TextField sport2;
    List<Competition> lastTwoOpenCompetitions = new ArrayList<>();
    private static final String YOUR_EMAIL = "fitnatic3@gmail.com"; 
    private static final String YOUR_PASSWORD = "23045265danke";
    @FXML
    private Button send;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        populateTextAreas();
        // TODO
    }  
    public void populateTextAreas() {
        

    try {
        String query = "SELECT * FROM Competition WHERE status = 'open' ORDER BY id DESC LIMIT 2";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Competition c = new Competition();
            c.setId(resultSet.getInt("id"));
            c.setName(resultSet.getString("name"));
            c.setStartDate(resultSet.getDate("start_date"));
            c.setEndDate(resultSet.getDate("end_date"));
            c.setLocation(resultSet.getString("location"));
            c.setDescription(resultSet.getString("description"));
            c.setCompetitionCategory(resultSet.getString("competitionCategory"));
            c.setEntryFee(resultSet.getInt("entryFee"));
            c.setMaxParticipants(resultSet.getInt("maxParticipants"));
            c.setStatus(resultSet.getString("status"));
            c.setPrizes(resultSet.getString("prize"));
            c.setSportType(resultSet.getString("sportType"));
            lastTwoOpenCompetitions.add(c);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        if (lastTwoOpenCompetitions != null && lastTwoOpenCompetitions.size() >= 2) {
    Competition competition1 = lastTwoOpenCompetitions.get(0);
    Competition competition2 = lastTwoOpenCompetitions.get(1);

            Competition1.setText(competition1.getName());
            start1.setText(competition1.getStartDate().toString());
            end1.setText(competition1.getEndDate().toString());
            category1.setText(competition1.getCompetitionCategory());
            entryfee1.setText(Integer.toString(competition1.getEntryFee()));
            participants1.setText(Integer.toString(competition1.getMaxParticipants()));
            prize1.setText(competition1.getPrizes());
            location1.setText(competition1.getLocation());
            sport1.setText(competition1.getSportType());

            Competition2.setText(competition2.getName());
            start2.setText(competition2.getStartDate().toString());
            end2.setText(competition2.getEndDate().toString());
            category2.setText(competition2.getCompetitionCategory());
            entryfee2.setText(Integer.toString(competition2.getEntryFee()));
            participants2.setText(Integer.toString(competition2.getMaxParticipants()));
            prize2.setText(competition2.getPrizes());
            location2.setText(competition2.getLocation());
            sport2.setText(competition2.getSportType());
        }
    }

    @FXML
    private void Menu(ActionEvent event) {
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
    private void readmore1(ActionEvent event) {
    String description = lastTwoOpenCompetitions.get(0).getDescription(); 
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Competition Description");
    alert.setHeaderText("Description of "+Competition1.getText());
    alert.setContentText(description);
    alert.showAndWait();
    }

    @FXML
    private void evaluate1(ActionEvent event) {
    }

    @FXML
    private void participate1(ActionEvent event) {
        TextInputDialog emailDialog = new TextInputDialog();
        emailDialog.setTitle("Participate in "+Competition1.getText());
        emailDialog.setHeaderText("Enter Your Email");
        emailDialog.setContentText("Email:");
        

        // Show the email dialog and capture the email input
        emailDialog.showAndWait().ifPresent(email -> {
            String e = "jenhanimostfa1@gmail.com";
            // Send an email to the entered email address
            boolean emailSent = sendConfirmationEmail(email);

            // Display a confirmation message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Participation Confirmation");

            if (emailSent) {
                alert.setHeaderText("Thank you for participating!");
                alert.setContentText("An email has been sent to " + email);
            } else {
                alert.setHeaderText("Email sending failed");
                alert.setContentText("Please try again later.");
            }

            alert.showAndWait();
        });
    }

    @FXML
    private void readmore2(ActionEvent event) {
            String description = lastTwoOpenCompetitions.get(1).getDescription(); 
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Competition Description");
    alert.setHeaderText("Description of "+Competition2.getText());
    alert.setContentText(description);
    alert.showAndWait();
    }

    @FXML
    private void evaluate2(ActionEvent event) {
    }

    @FXML
    private void participate2(ActionEvent event) {
    }
    private boolean sendConfirmationEmail(String toEmail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(YOUR_EMAIL, YOUR_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(YOUR_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Participation Confirmation");
            message.setText("Thank you for participating!");

            Transport.send(message);
            return true; // Email sent successfully
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; // Email sending failed
        }
    }

    @FXML
    private void envoyer(ActionEvent event) {
        if(sendConfirmationEmail("jenhanimostfa1@gmail.com")){
            System.out.println("YESSSSSSSSSSSSSSSSSSS");
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.fxml.Initializable;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class PlanmailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        while (true) {
            // Calculate the initial delay until the next scheduled time
            long initialDelay = calculateInitialDelay(14,28); 

            try {
                // Sleep for the initial delay
                TimeUnit.MILLISECONDS.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Send the email
            sendConfirmationEmail("hassen@gmail.com");
        }
    }

    public static void sendConfirmationEmail(String toEmail) {
           String host = "sandbox.smtp.mailtrap.io";
        String username = "d614db2da752d8";
        String password = "feccdd31f97bef";
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
            message.setSubject("Fitnatic notifications");
            message.setText("Its Meal timeeeeeee");

          
            Transport.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static long calculateInitialDelay(int hour, int minute) {
        long now = System.currentTimeMillis();
        long targetTime = getTargetTimeMillis(hour, minute);

        if (now > targetTime) {
            targetTime += TimeUnit.DAYS.toMillis(1); // Add 24 hours in milliseconds
        }

        return targetTime - now;
    }

    public static long getTargetTimeMillis(int hour, int minute) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.HOUR_OF_DAY, hour);
        calendar.set(java.util.Calendar.MINUTE, minute);
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }
}





        
    


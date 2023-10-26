/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    //public void sendEmail(String recipient, String subject, String content) {
        // Configuration de JavaMail
        public static void sendConfirmationEmail(String toEmail, String contenue) {
      
        String host = "sandbox.smtp.mailtrap.io";
        String username = "003088a01ba1b7";
        String password = "df4e9a1a4bf31b";
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
            message.setSubject("Réponse de votre Réclamation");
            message.setText(contenue);

          
            Transport.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
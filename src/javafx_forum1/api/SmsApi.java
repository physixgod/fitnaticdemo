/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author OUMAIMA
 */
public class SmsApi {
    public static final String ACCOUNT_SID = "AC3a54b89391511f6ad7ab71db7084ec6f";
    public static final String AUTH_TOKEN = "4d42532063607ab4569708b6a46b5069";

    public SmsApi() {
    }
    

    public void SmsNotification(String Date, String Choix) {
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String fromPhoneNumber = "+13107366100";
        String toPhoneNumber = "+21625288388";
        String messageBody = "Hello from Fitnatic! Evenement reussit" +"  "+ Date +"  "+ Choix ;

        Message message = Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromPhoneNumber),
                messageBody
        ).create();

        System.out.println("Message sent with SID: " + message.getSid());
    }
    
}

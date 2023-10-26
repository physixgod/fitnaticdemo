/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.interfaces;

/**
 *
 * @author Youssef
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendSMS {
    
    
    public static final String ACCOUNT_SID = System.getenv("AC853fa8dd7f9f60c49329d0418a6f6162");
    public static final String AUTH_TOKEN = System.getenv("155c03cd8f5ce0a708193b21a5268c9d");

    public static void sendSMS() {
    Twilio.init("AC853fa8dd7f9f60c49329d0418a6f6162", "155c03cd8f5ce0a708193b21a5268c9d");
    Message message = Message.creator(new PhoneNumber("+21652362970"),
            new PhoneNumber("+15133225456"), "Nouvelle Réclamation a été ajouté : " ).create();

    System.out.println(message.getSid());
}
    
}


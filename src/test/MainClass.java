/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Competition;
import entities.Sport_Type;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import utils.MyConnection;
import services.CompetitionCRUD;

/**
 *
 * @author toufa
 */
public class MainClass {
    public static void main(String[] args) {
        MyConnection mc =new MyConnection();
        Sport_Type st= new Sport_Type("football","Tunis",new Date(),new Date());
      // Competition c = new Competition("Example Competition", new Date(), new Date(), "Example Location", "Example Description", "Example Sport Type", "Example Category", 50.0, 100, "Open", "Example Prizes");

        //Cr.ajouterCompetition(c);
    /* CompetitionCRUD Cr = new CompetitionCRUD();
List<Competition> competitions = Cr.afficherCompetition();

for (Competition c : competitions) {
    System.out.println("ID: " + c.getId());
    System.out.println("Name: " + c.getName());
    System.out.println("Start Date: " + c.getStartDate());
    System.out.println("End Date: " + c.getEndDate());
    System.out.println("Location: " + c.getLocation());
    System.out.println("Description: " + c.getDescription());
    System.out.println("Competition Category: " + c.getCompetitionCategory());
    System.out.println("Entry Fee: " + c.getEntryFee());
    System.out.println("Max Participants: " + c.getMaxParticipants());
    System.out.println("Status: " + c.getStatus());
    System.out.println("Prizes: " + c.getPrizes());
    System.out.println("Sport Type: " + c.getSportType());
    System.out.println();
}
*/






    }
}

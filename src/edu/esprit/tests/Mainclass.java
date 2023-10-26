/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;
import edu.esprit.entities.Activite;
import edu.esprit.services.ActiviteCRUD;
import edu.esprit.utils.MyConnectiona;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



//import edu.esprit.utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class Mainclass {
    public static void main(String[] args) {
       // MyConnection mc = new MyConnection();
       
       ActiviteCRUD acd =new ActiviteCRUD();
       
       /* MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode()+"-"+mc2.hashCode());*/
       
       /*try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = dateFormat.parse("2023-10-20");
            Date dateFin = dateFormat.parse("2023-10-22");

            Activite a2 = new Activite(0, "Another Activity", "Type", dateDebut, dateFin, 3, "Notes");

            acd.ajouterActivite(a2);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
       
       /*try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = dateFormat.parse("2023-10-20");
            Date dateFin = dateFormat.parse("2023-10-22");

            Activite newActivite = new Activite(0, "Updated Activity", "Updated Type",
                    dateDebut, dateFin, 5, "Updated Notes");

            // Assuming 'idToUpdate' is the ID of the activity you want to update
            int id2 = 5;

            acd.modifierActivite(id2, newActivite);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    
       
  /*int id3 = 5;

       acd.supprimerActivite(id3);   */  
       
       
       
    }   
}


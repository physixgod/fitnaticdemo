/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.entities;

import javafx.scene.control.Button;

/**
 *
 * @author OUMAIMA
 */
public class Publication {


    private String username;
    private String choix;
    
   


    public Publication(String username, String choix) {
        this.username = username;
        this.choix = choix;
    }

    // Ajouter d'autres getters et setters au besoin
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }
}



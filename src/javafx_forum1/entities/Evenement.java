/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.entities;
import java.util.Date; // Pour la gestion de la date
import javafx.beans.property.SimpleObjectProperty;

public class Evenement {
    private Date dateEvenement;
    private String choixEvenement;
    private String descriptionEvenement;

    // Constructeur
    public Evenement(Date dateEvenement, String choixEvenement, String descriptionEvenement) {
        this.dateEvenement = dateEvenement;
        this.choixEvenement = choixEvenement;
        this.descriptionEvenement = descriptionEvenement;
    }
    private SimpleObjectProperty<Date> dateEvenemen = new SimpleObjectProperty<>();

    public SimpleObjectProperty<Date> dateEvenementProperty() {
        return dateEvenemen;
    }

    public Evenement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters et Setters
    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getChoixEvenement() {
        return choixEvenement;
    }

    public void setChoixEvenement(String choixEvenement) {
        this.choixEvenement = choixEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void choixEvenementProperty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_forum1.entities;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author OUMAIMA
 */
public class EventTableView {
  
    private  IntegerProperty id;
    private  ObjectProperty<Date> dateEvenement;
    private  StringProperty choixEvenement;
    private  StringProperty descriptionEvenement;

    public EventTableView() {
        this.id = new SimpleIntegerProperty();
        this.dateEvenement = new SimpleObjectProperty<>();
        this.choixEvenement = new SimpleStringProperty();
        this.descriptionEvenement = new SimpleStringProperty();
    }

    // Getters and setters for id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Getters and setters for dateEvenement
    public Date getDateEvenement() {
        return dateEvenement.get();
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement.set(dateEvenement);
    }

    public ObjectProperty<Date> dateEvenementProperty() {
        return dateEvenement;
    }

    // Getters and setters for choixEvenement
    public String getChoixEvenement() {
        return choixEvenement.get();
    }

    public void setChoixEvenement(String choixEvenement) {
        this.choixEvenement.set(choixEvenement);
    }

    public StringProperty choixEvenementProperty() {
        return choixEvenement;
    }

    // Getters and setters for descriptionEvenement
    public String getDescriptionEvenement() {
        return descriptionEvenement.get();
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement.set(descriptionEvenement);
    }

    public StringProperty descriptionEvenementProperty() {
        return descriptionEvenement;
    }
}

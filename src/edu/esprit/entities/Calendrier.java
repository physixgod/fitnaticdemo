package edu.esprit.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Calendrier {
    private SimpleIntegerProperty id;
    private SimpleStringProperty lundi;
    private SimpleStringProperty mardi;
    private SimpleStringProperty mercredi;
    private SimpleStringProperty jeudi;
    private SimpleStringProperty vendredi;
    private SimpleStringProperty samedi;

    public Calendrier(int id, String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi) {
        this.id = new SimpleIntegerProperty(id);
        this.lundi = new SimpleStringProperty(lundi);
        this.mardi = new SimpleStringProperty(mardi);
        this.mercredi = new SimpleStringProperty(mercredi);
        this.jeudi = new SimpleStringProperty(jeudi);
        this.vendredi = new SimpleStringProperty(vendredi);
        this.samedi = new SimpleStringProperty(samedi);
    }

    // Getter and Setter methods for each property
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLundi() {
        return lundi.get();
    }

    public void setLundi(String lundi) {
        this.lundi.set(lundi);
    }

    public String getMardi() {
        return mardi.get();
    }

    public void setMardi(String mardi) {
        this.mardi.set(mardi);
    }

    public String getMercredi() {
        return mercredi.get();
    }

    public void setMercredi(String mercredi) {
        this.mercredi.set(mercredi);
    }

    public String getJeudi() {
        return jeudi.get();
    }

    public void setJeudi(String jeudi) {
        this.jeudi.set(jeudi);
    }

    public String getVendredi() {
        return vendredi.get();
    }

    public void setVendredi(String vendredi) {
        this.vendredi.set(vendredi);
    }

    public String getSamedi() {
        return samedi.get();
    }

    public void setSamedi(String samedi) {
        this.samedi.set(samedi);
    }
}

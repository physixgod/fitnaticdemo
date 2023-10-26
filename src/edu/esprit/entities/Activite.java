/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Activite {
    private int id;
    private String nom;
    private String type;
    private Date dateDebut;
    private Date dateFin;
    private int duree;
    private String notes;

    public Activite(int id, String nom, String type, Date dateDebut, Date dateFin, int duree, String notes) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.notes = notes;
    }

    public Activite(String nom, String type, Date dateDebut, Date dateFin, int duree, String notes) {
        this.nom = nom;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Activite{" + "nom=" + nom + ", type=" + type + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", duree=" + duree + ", notes=" + notes + '}';
    }

    
    
    
    
}

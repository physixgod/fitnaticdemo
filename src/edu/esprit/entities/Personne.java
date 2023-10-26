package edu.esprit.entities;

import java.time.LocalDate;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String genre;
    private LocalDate dateNaissance;
    private String motDePasse;
    private String motDePasse2; 

    public Personne(int id, String nom, String prenom, String email, String genre, LocalDate dateNaissance, String motDePasse, String motDePasse2) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.motDePasse = motDePasse;
        this.motDePasse2 = motDePasse2; 
    }  public Personne() {
        // Constructeur par défaut sans arguments
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse2() {
        return motDePasse2;
    }

    public void setMotDePasse2(String motDePasse2) {
        this.motDePasse2 = motDePasse2;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", genre=" + genre +
                ", dateNaissance=" + dateNaissance +
                ", motDePasse='" + motDePasse + '\'' +
                ", motDePasse2='" + motDePasse2 + '\'' +
                '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author ASUS
 */
public class Exercices {
    private int idE;
    private String nomE;
    private String categorie;
    private int dureeE;
    private int repetitions;
    private int series;
    private String objectif;
    private String niveauDifficulte;

    public Exercices(int idE, String nomE, String categorie, int dureeE, int repetitions, int series, String objectif, String niveauDifficulte) {
        this.idE = idE;
        this.nomE = nomE;
        this.categorie = categorie;
        this.dureeE = dureeE;
        this.repetitions = repetitions;
        this.series = series;
        this.objectif = objectif;
        this.niveauDifficulte = niveauDifficulte;
    }

    public Exercices(String nomE, String categorie, int dureeE, int repetitions, int series, String objectif, String niveauDifficulte) {
        this.nomE = nomE;
        this.categorie = categorie;
        this.dureeE = dureeE;
        this.repetitions = repetitions;
        this.series = series;
        this.objectif = objectif;
        this.niveauDifficulte = niveauDifficulte;
    }
    
    

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getDureeE() {
        return dureeE;
    }

    public void setDureeE(int dureeE) {
        this.dureeE = dureeE;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getNiveauDifficulte() {
        return niveauDifficulte;
    }

    public void setNiveauDifficulte(String niveauDifficulte) {
        this.niveauDifficulte = niveauDifficulte;
    }

    @Override
    public String toString() {
        return "Exercices{" + "idE=" + idE + ", nomE=" + nomE + ", categorie=" + categorie + ", dureeE=" + dureeE + ", repetitions=" + repetitions + ", series=" + series + ", objectif=" + objectif + ", niveauDifficulte=" + niveauDifficulte + '}';
    }

   
    
}

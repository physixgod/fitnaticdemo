package entities;

import java.text.DecimalFormat;

public class Imc {
    private int id;
    private String sexe;
    private double taille;
    private double poids;
    private double IMC;
    private String categorieIMC;
    private double poidsActuel;
    private Double poidsIdeal;
    private int age;

    public Imc() {
    }

public Imc(String sexe, double taille, double poids, int age) {
    this.sexe = sexe;
    this.taille = taille;
    this.poids = poids;
    this.age = age;
    calculerIMC();
    calculerPoidsIdeal();
}


 
    public int getId() {
        return id;
    }

    public String getSexe() {
        return sexe;
    }

    public double getTaille() {
        return taille;
    }

    public double getPoids() {
        return poids;
    }

    public double getIMC() {
        return IMC;
    }

    public String getCategorieIMC() {
        return categorieIMC;
    }

    public double getPoidsActuel() {
        return poidsActuel;
    }

    public Double getPoidsIdeal() {
        return poidsIdeal;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public void setCategorieIMC(String categorieIMC) {
        this.categorieIMC = categorieIMC;
    }

    public void setPoidsActuel(double poidsActuel) {
        this.poidsActuel = poidsActuel;
    }

    public void setPoidsIdeal(Double poidsIdeal) {
        this.poidsIdeal = poidsIdeal;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void calculerIMC() {
        double tailleEnM = taille / 100.0;
        IMC = poids / (tailleEnM * tailleEnM);

        if (sexe.equalsIgnoreCase("homme")) {
            if (IMC < 18.5) {
                categorieIMC = "Sous-poids";
            } else if (IMC >= 18.5 && IMC < 24.9) {
                categorieIMC = "Poids normal";
            } else if (IMC >= 25.0 && IMC < 29.9) {
                categorieIMC = "Surpoids";
            } else {
                categorieIMC = "Obésité";
            }
        } else if (sexe.equalsIgnoreCase("femme")) {
            if (IMC < 18.5) {
                categorieIMC = "Sous-poids";
            } else if (IMC >= 18.5 && IMC < 24.9) {
                categorieIMC = "Poids normal";
            } else if (IMC >= 25.0 && IMC < 29.9) {
                categorieIMC = "Surpoids";
            } else {
                categorieIMC = "Obésité";
            }
        }
    }

    public void calculerPoidsIdeal() {
        if (sexe.equalsIgnoreCase("homme")) {
            poidsIdeal = (taille - 100) - ((taille - 150) / 4);
        } else if (sexe.equalsIgnoreCase("femme")) {
            poidsIdeal = (taille - 100) - ((taille - 150) / 2.5);
        } else {
            poidsIdeal = null;  // Valeur par défaut en cas de sexe non reconnu
        }
    }

    public void afficherIMC() {
        // Formater l'IMC avec un seul nombre après la virgule
        DecimalFormat df = new DecimalFormat("0.0");
        String imcFormatted = df.format(IMC);

        // Afficher l'IMC formaté
        System.out.println("IMC : " + imcFormatted);
    }
}

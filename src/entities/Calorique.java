package entities;

import Services.ImcCrud;

public class Calorique {
        private int id;

    private String objectif;
    private double mb;
   
    private double besoinsCaloriques;
    private String activite;

    // Ajouter les nouvelles propriétés
    private String regimeAlimentaire;
    private String niveauStress;
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }


    public double getBesoinsCaloriques() {
        return besoinsCaloriques;
    }

    public void setBesoinsCaloriques(double besoinsCaloriques) {
        this.besoinsCaloriques = besoinsCaloriques;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

      public double getMb() {
        return mb;
    }

    public void setMb(double mb) {
        this.mb = mb;
    }
 
   
    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    // Méthode pour calculer les besoins caloriques
    public double calculerBesoinsCaloriques() {
        ImcCrud imcCrud = new ImcCrud();
        Imc imc = imcCrud.getLatestImc();

        if (imc != null) {
            double poids = imc.getPoids();
            double taille = imc.getTaille();
            String sexe = imc.getSexe();
            int age = imc.getAge();
            

            double mb;

  
    if (sexe.equals("homme")) {
        mb = 88.362 + (13.397 * poids) + (4.799 * taille) - (5.677 * age);
    } else {
        mb = 447.593 + (9.247 * poids) + (3.098 * taille) - (4.330 * age);
    }
    // Le calcul de la variable mb ici...

// Afficher la valeur de mb
System.out.println("Le métabolisme de base (MB) est : " + mb);


            double palierActivite = 1.0;

            if (activite.equals("légèrement actif")) {
                palierActivite = 1.2;
            } else if (activite.equals("modérément actif")) {
                palierActivite = 1.5;
            } else if (activite.equals("très actif")) {
                palierActivite = 1.8;
            } else if (activite.equals("extrêmement actif")) {
                palierActivite = 2;
            }

            double facteurObjectif = 1.0;

            if (objectif.equals("perte de poids")) {
                facteurObjectif = 1.1;
            } else if (objectif.equals("tonifier les muscles")) {
                facteurObjectif = 1.4;
            }

     
            double facteurRegime = 1.0;
            if (regimeAlimentaire != null) {
                if (regimeAlimentaire.equals("je suis intolérant au lactose")) {
                    facteurRegime = 0.9; 
                } else if (regimeAlimentaire.equals("je ne mange pas de gluten")) {
                    facteurRegime = 1.2; 
                } else if (regimeAlimentaire.equals("je suis végétarien")) {
                    facteurRegime = 1.4; 
                }
            }

            double facteurStress = 1.0;
            if (niveauStress != null) {
                if (niveauStress.equals("Faible")) {
                    facteurStress = 0.8;
                } else if (niveauStress.equals("Modéré")) {
                    facteurStress = 1.0;
                } else if (niveauStress.equals("Elevé")) {
                    facteurStress = 1.2; 
                }
            }

            besoinsCaloriques = mb * palierActivite * facteurObjectif * facteurRegime * facteurStress;
         besoinsCaloriques = Math.abs(besoinsCaloriques);}
        return besoinsCaloriques;
    }


    public String getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void setRegimeAlimentaire(String regimeAlimentaire) {
        this.regimeAlimentaire = regimeAlimentaire;
    }

    public String getNiveauStress() {
        return niveauStress;
    }

    public void setNiveauStress(String niveauStress) {
        this.niveauStress = niveauStress;
    }
}

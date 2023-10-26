package Test;

import entities.Imc;

import Services.ImcCrud;


public class MainClass {
    public static void main(String[] args) {
        // Créer une instance d'IMC avec des valeurs spécifiques
        Imc imc = new Imc("homme", 160, 60, 30); // Ajouter l'âge

        // Calculer l'IMC
        imc.calculerIMC();

        // Calculer et définir le poids idéal
        imc.calculerPoidsIdeal();

        // Créer une instance de ImcCrud
        ImcCrud imcCrud = new ImcCrud();

        // Insérer l'IMC dans la base de données
        boolean insertionImcReussie = imcCrud.insertImc(imc);

        if (insertionImcReussie) {
            System.out.println("IMC inséré avec succès dans la base de données.");
        } else {
            System.out.println("Erreur lors de l'insertion de l'IMC dans la base de données.");
        }

}}

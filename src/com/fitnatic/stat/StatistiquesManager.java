/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fitnatic.stat;

/**
 *
 * @author Youssef
 */
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;

public class StatistiquesManager {
    private BarChart<String, Number> barChart;
    private CheckBox checkboxSatisfait;
    private CheckBox checkboxMoyen;
    private CheckBox checkboxInsatisfait;

    public StatistiquesManager(BarChart<String, Number> barChart, CheckBox checkboxSatisfait, CheckBox checkboxMoyen, CheckBox checkboxInsatisfait) {
        this.barChart = barChart;
        this.checkboxSatisfait = checkboxSatisfait;
        this.checkboxMoyen = checkboxMoyen;
        this.checkboxInsatisfait = checkboxInsatisfait;

        // Écoutez les modifications des cases à cocher
        checkboxSatisfait.selectedProperty().addListener((observable, oldValue, newValue) -> mettreAJourStatistiques());
        checkboxMoyen.selectedProperty().addListener((observable, oldValue, newValue) -> mettreAJourStatistiques());
        checkboxInsatisfait.selectedProperty().addListener((observable, oldValue, newValue) -> mettreAJourStatistiques());

        // Configurez le graphique à barres
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart.setTitle("Statistiques des réclamations");
        barChart.getXAxis().setLabel("Catégorie");
        barChart.getYAxis().setLabel("Nombre de réclamations");

        // Créez les séries pour chaque catégorie
        XYChart.Series<String, Number> seriesSatisfait = new XYChart.Series<>();
        seriesSatisfait.setName("Satisfait");
        XYChart.Series<String, Number> seriesMoyen = new XYChart.Series<>();
        seriesMoyen.setName("Moyen");
        XYChart.Series<String, Number> seriesInsatisfait = new XYChart.Series<>();
        seriesInsatisfait.setName("Insatisfait");

        // Ajoutez les séries au graphique
        barChart.getData().addAll(seriesSatisfait, seriesMoyen, seriesInsatisfait);
    }

    public void mettreAJourStatistiques() {
        // Obtenez le nombre de réclamations pour chaque catégorie
        int satisfait = checkboxSatisfait.isSelected() ? 1 : 0;
        int moyen = checkboxMoyen.isSelected() ? 1 : 0;
        int insatisfait = checkboxInsatisfait.isSelected() ? 1 : 0;

        // Ajoutez les données aux séries
        barChart.getData().forEach(series -> series.getData().clear());
        barChart.getData().get(0).getData().add(new XYChart.Data<>("Satisfait", satisfait));
        barChart.getData().get(1).getData().add(new XYChart.Data<>("Moyen", moyen));
        barChart.getData().get(2).getData().add(new XYChart.Data<>("Insatisfait", insatisfait));
    }
}
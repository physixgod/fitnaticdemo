/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Competition;
import entities.Sport_Type;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author toufa
 */
public interface InterfaceCRUD<T> {
    public void ajouterCompetition(Competition T);
    public List<Competition> afficherCompetition();
    public ObservableList<Sport_Type> getAllSport();
    public ObservableList<Sport_Type> getFilteredSportTypes(String location);
    public List<String> getAllLocations();
}


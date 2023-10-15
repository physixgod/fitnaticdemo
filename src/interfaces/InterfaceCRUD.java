/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Competition;
import java.util.List;

/**
 *
 * @author toufa
 */
public interface InterfaceCRUD<T> {
    public void ajouterCompetition(Competition T);
    public List<Competition> afficherCompetition();
}


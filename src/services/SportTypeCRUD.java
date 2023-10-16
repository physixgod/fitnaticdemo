/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Competition;
import entities.Sport_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author toufa
 */
public class SportTypeCRUD {
        public ObservableList<Sport_Type> getAllSport(){
        ObservableList<Sport_Type> sportTypes = FXCollections.observableArrayList();
        try {
        String query="SELECT * FROM sport_type";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs= st.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String location = rs.getString("location");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            Sport_Type sportType = new Sport_Type(id, name, location, startDate, endDate);
            sportTypes.add(sportType);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return sportTypes;
    }
public ObservableList<Sport_Type> getFilteredSportTypes(String location) {
    ObservableList<Sport_Type> filteredSportTypes = FXCollections.observableArrayList();

    try {
         String query = "SELECT * FROM sport_type WHERE location = '" + location + "'";
       Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs= st.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            Sport_Type sportType = new Sport_Type(id, name, location, startDate, endDate);

            // Add the filtered Sport_Type to the list
            filteredSportTypes.add(sportType);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }

    return filteredSportTypes;
}
    public List<String> getAllLocations() {
        List<String> locations = new ArrayList<>();

        // Your SQL query to retrieve distinct locations
        String query = "SELECT DISTINCT location FROM sport_type";

        try (
             PreparedStatement ps = new MyConnection().getCnx().prepareStatement(query);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                String location = resultSet.getString("location");
                locations.add(location);
            }
        } catch (SQLException ex) {
            System.err.println("Error fetching locations: " + ex.getMessage());
        }

        return locations;
    }

}

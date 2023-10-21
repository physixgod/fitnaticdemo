/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Sport_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
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
            filteredSportTypes.add(sportType);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }

    return filteredSportTypes;
}
    public List<String> getAllLocations() {
        List<String> locations = new ArrayList<>();
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
        public void ajouterSportType(Sport_Type sportType) {
        try {
            String query = "INSERT INTO sport_type (name, location, start_date, end_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setString(1, sportType.getName());
            preparedStatement.setString(2, sportType.getLocation());
            preparedStatement.setDate(3, new java.sql.Date(sportType.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(sportType.getEndDate().getTime()));
            preparedStatement.executeUpdate();
            System.out.println("Sport Type Added");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       public List<Sport_Type> getAllSports(){
       List<Sport_Type> myList = new ArrayList<>();
        try {
        String query2="SELECT * FROM sport_type";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs= st.executeQuery(query2);
        while(rs.next()){
            Sport_Type c = new Sport_Type();
            c.setName(rs.getString("name"));
            c.setStartDate(rs.getDate("start_date"));
            c.setEndDate(rs.getDate("end_date"));
            c.setLocation(rs.getString("location"));
            c.setId(rs.getInt("id"));
            myList.add(c);
            
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
       public boolean updateSportType(Sport_Type sportType, String newName, String newLocation, LocalDate newStartDate, LocalDate newEndDate) {
        String query = "UPDATE sport_type SET name = ?, location = ?, start_date = ?, end_date = ? WHERE id = ?";
        
        try {
        Date startDate1 = Date.from(newStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate1 = Date.from(newEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlStartDate = new java.sql.Date(startDate1.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate1.getTime());
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newLocation);
            preparedStatement.setDate(3, sqlStartDate);
            preparedStatement.setDate(4, sqlEndDate);
            preparedStatement.setInt(5, sportType.getId());
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}









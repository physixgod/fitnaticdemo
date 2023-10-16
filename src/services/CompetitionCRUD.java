package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.MyConnection; 
import entities.Competition;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CompetitionCRUD {
    public void ajouterCompetition(Competition c) {
        try {
            String query1 = "INSERT INTO competition (name, start_date, end_date, location, description, sportType, competitionCategory, entryFee, maxParticipants, status, prize) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query1);
            preparedStatement.setString(1, c.getName());
            preparedStatement.setDate(2, new java.sql.Date(c.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(c.getEndDate().getTime()));
            preparedStatement.setString(4, c.getLocation());
            preparedStatement.setString(5, c.getDescription());
            preparedStatement.setString(6, c.getSportType());
            preparedStatement.setString(7, c.getCompetitionCategory());
            preparedStatement.setInt(8, c.getEntryFee());
            preparedStatement.setInt(9, c.getMaxParticipants());
            preparedStatement.setString(10, c.getStatus());
            preparedStatement.setString(11, c.getPrizes());
            preparedStatement.executeUpdate();
            System.out.println("Competiton Added");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<Competition> afficherCompetition(){
       List<Competition> myList = new ArrayList<>();
        try {
        String query2="SELECT * FROM Competition";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs= st.executeQuery(query2);
        while(rs.next()){
            Competition c = new Competition();
            c.setId(rs.getInt(1));
            c.setName(rs.getString("name"));
            c.setStartDate(rs.getDate("start_date"));
            c.setEndDate(rs.getDate("end_date"));
            c.setLocation(rs.getString("location"));
            c.setDescription(rs.getString("description"));
            c.setCompetitionCategory(rs.getString("competitionCategory"));
            c.setEntryFee(rs.getInt("entryFee"));
            c.setMaxParticipants(rs.getInt("maxParticipants"));
            c.setStatus(rs.getString("status"));
            c.setPrizes(rs.getString("prize"));
            c.setSportType("sportType");
            myList.add(c);
            
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
        public List<Competition> getAllCompetitions(){
       List<Competition> myList = new ArrayList<>();
        try {
        String query2="SELECT * FROM Competition";
        Statement st = new MyConnection().getCnx().createStatement();
        ResultSet rs= st.executeQuery(query2);
        while(rs.next()){
            Competition c = new Competition();
            c.setName(rs.getString("name"));
            c.setStartDate(rs.getDate("start_date"));
            c.setEndDate(rs.getDate("end_date"));
            c.setLocation(rs.getString("location"));
            c.setCompetitionCategory(rs.getString("competitionCategory"));
            c.setEntryFee(rs.getInt("entryFee"));
            c.setMaxParticipants(rs.getInt("maxParticipants"));
            c.setStatus(rs.getString("status"));
            c.setPrizes(rs.getString("prize"));
            c.setSportType(rs.getString("sportType"));
            myList.add(c);
            
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
        public boolean deleteCompetition(Competition competition) {
    try {
        String query = "DELETE FROM Competition WHERE id = ?";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setInt(1, competition.getId()); // Assuming 'id' is the unique identifier

        int rowsAffected = preparedStatement.executeUpdate();
        
        return rowsAffected > 0;
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

}

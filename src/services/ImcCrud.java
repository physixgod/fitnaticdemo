package Services;

import entities.Imc;
import utils.hassenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImcCrud {
    
public Imc getLatestImc() {
    Imc c = null; // Initialize to null

    String selectQuery = "SELECT sexe, taille, poids, IMC FROM imc ORDER BY id DESC LIMIT 1";

    try (        Connection connection = hassenConnection.getInstance().getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(selectQuery)) {

        if (resultSet.next()) {
            c = new Imc();
            c.setSexe(resultSet.getString("sexe"));
            c.setTaille(resultSet.getDouble("taille"));
            c.setPoids(resultSet.getDouble("poids"));
            c.setIMC(resultSet.getDouble("IMC"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return c;
}



    
    public static List<Imc> getAllImc() {
        Connection connection = hassenConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Imc> imcList = new ArrayList<>();

        try {
            String selectSQL = "SELECT * FROM imc";
            pstmt = connection.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Imc imc = new Imc();
                imc.setId(rs.getInt("id"));
                imc.setSexe(rs.getString("sexe"));
                imc.setTaille(rs.getDouble("taille"));
                imc.setPoids(rs.getDouble("poids"));
                imc.setIMC(rs.getDouble("imc"));
                imc.setPoidsIdeal(rs.getDouble("poidsIdeal"));
                imc.setCategorieIMC(rs.getString("categorieIMC"));
                imc.setAge(rs.getInt("age"));
                imcList.add(imc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        

        return imcList;
    }

    public boolean insertImc(Imc imc) {
            Connection connection = hassenConnection.getInstance().getConnection();
        PreparedStatement pstmt = null;
        

        try {
            String insertSQL = "INSERT INTO imc (sexe, taille, poids, imc, poidsIdeal, categorieIMC, age) VALUES (?, ?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, imc.getSexe());
            pstmt.setDouble(2, imc.getTaille());
            pstmt.setDouble(3, imc.getPoids());
            pstmt.setDouble(4, imc.getIMC());
            pstmt.setDouble(5, imc.getPoidsIdeal());
            pstmt.setString(6, imc.getCategorieIMC());
            pstmt.setInt(7, imc.getAge());

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

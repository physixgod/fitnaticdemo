

package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.hassenConnection;
import entities.Calorique;
import entities.Imc;
import java.sql.ResultSet;
import java.sql.Statement;

public class CaloriqueCrud {

  public Calorique getLatestBesoinCaloriques() {
        Calorique calorique = null; // Initialize to null

        String selectQuery = "SELECT besoinsCaloriques FROM calorique ORDER BY id DESC LIMIT 1";

        try (Connection connection = hassenConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            if (resultSet.next()) {
                calorique = new Calorique();
                calorique.setBesoinsCaloriques(resultSet.getDouble("besoinsCaloriques"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calorique;
    }
    
    
    public void insertCalorique(Calorique calorique) {
        Connection connection = hassenConnection.getInstance().getConnection(); // Utilisation de la connexion depuis hassenConnection
;

        try {
            if (connection != null) {
                String sql = "INSERT INTO calorique (objectif, besoinsCaloriques, activite, regimeAlimentaire, niveauStress) " +
                             "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, calorique.getObjectif());
                preparedStatement.setDouble(2, 0);
                preparedStatement.setString(3, calorique.getActivite());
                preparedStatement.setString(4, calorique.getRegimeAlimentaire());
                preparedStatement.setString(5, calorique.getNiveauStress());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
       
        }
    }
    public void updateAllCaloriqueBesoinsCalorique(double newValue) {
    Connection connection = hassenConnection.getInstance().getConnection(); // Get the connection from hassenConnection

    try {
        if (connection != null) {
            String updateSql = "UPDATE calorique SET besoinsCaloriques = ?";

            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setDouble(1, newValue);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Besoins calorique updated successfully for all records.");
            } else {
                System.out.println("No records found for update.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 

    }}




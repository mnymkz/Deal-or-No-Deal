
package Logins;

import Database.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class PlayerManager {
 //playerManager fields
    private final DBManager dBConnect;

    public PlayerManager(DBManager dBConnect) {
        this.dBConnect = dBConnect;
    }

    /**
     * Adds new player to the database 
     * 
     * @param player the player to be added 
     * @param loginUsername, the username in the player login (should be the same) 
     * @return true if successful, false if not 
     */
    public boolean addNewPlayer(Player player, String loginUsername) {
        if (!playerExists(player))
        {
            String insertStatement = "INSERT INTO PLAYER (playerName, highestEarnings, loginID) VALUES (?, ?, (SELECT loginID FROM LOGIN WHERE username = ?))";
            
            try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(insertStatement)) {
                
                preparedStatement.setString(1, player.getName()); // set the player's name
                preparedStatement.setDouble(2, 0.0); // set default highest earnings
                preparedStatement.setString(3, loginUsername); // find loginID by username
                
                int rowsInserted = preparedStatement.executeUpdate();
                return (rowsInserted > 0); //return true if rows successfully inserted
            } catch (SQLException e) {
                System.out.println("Error adding a new player: " + e.getMessage()); //error 
                return false;
            }
        } else {
            return false; // unsuccessful, return false
        }
    }
    
    /**
     * updates the player's highest earnings 
     * 
     * @param playerName the players username
     * @param newEarnings the player's updated earnings
     * @return true if successful else return false
     */
    public boolean updateHighestEarnings(String playerName, Double newEarnings) {
        if (newEarnings > 0) {
            String updatePlayerSQL = "UPDATE PLAYER SET highestEarnings = ? WHERE playerName = ?";

            try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(updatePlayerSQL)) {
                preparedStatement.setDouble(1, newEarnings); // set the new highest earnings
                preparedStatement.setString(2, playerName); // find playerID by username

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) { // if a row was updated
                    System.out.println("Player's highest earnings updated successfully.");
                    return true;
                } else { // player not found or unable to update earnings
                    System.out.println("Player not found or unable to update earnings.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error updating player's highest earnings: " + e.getMessage());
                return false; // return false in case of an error
            }
        } else {
            return false; //new earnings cannot be less than 0
        }
    }
    
    /**
     * playerExists method checks if a player exists in the database 
     * 
     * @param player the player to check
     * @return true if exists, else false
     */
    public boolean playerExists(Player player) {
        String query = "SELECT * FROM PLAYER WHERE playerName = ?";

        try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a row is returned from the query
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking if player exists: " + e.getMessage());
            return false; // Return false in case of an error
        }
    }
    
    /**
     * Retrieve the highest earnings from the database for a player.
     *
     * @param player the player for which to retrieve the highest earnings
     * @return the highest earnings of the player, or -1.0 if not found or an
     * error occurs
     */
    public double getHighestEarnings(Player player) {
        String query = "SELECT highestEarnings FROM PLAYER WHERE playerName = ?";

        try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(query)) { //query db 
            preparedStatement.setString(1, player.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("highestEarnings");
            } else {
                return -1.0; //player not found, return -1
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving highest earnings: " + e.getMessage());
            return -1.0; // error, return -1
        }
    }
}

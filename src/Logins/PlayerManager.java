
package Logins;

import Database.DBManager;
import java.sql.PreparedStatement;
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
     * @param playerName, string userName the username of the player 
     * @param loginUserName, the username in the player login (should be the same) 
     */
    public void addNewPlayer(String playerName, String loginUserName) {
        String insertStatement = "INSERT INTO PLAYER (playerName, highestEarnings, loginID) VALUES (?, ?, (SELECT loginID FROM LOGIN WHERE username = ?))";
        
        try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(insertStatement)) {
            preparedStatement.setString(1, playerName); // set the player's name
            preparedStatement.setDouble(2, 0.0); // set default highest earnings
            preparedStatement.setString(3, loginUserName); // find loginID by username
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding a new player: " + e.getMessage()); //error 
        }
    }
    
    /**
     * updates the player's highest earnings 
     * 
     * @param loginName the players username
     * @param newEarnings the player's updated earnings
     */
    public void updateHighestEarnings(String loginName, Double newEarnings) {
        String updatePlayerSQL = "UPDATE PLAYER SET highestEarnings = ? WHERE playerID = (SELECT playerID FROM LOGIN WHERE username = ?)";
        
        try (PreparedStatement preparedStatement = dBConnect.getConnection().prepareStatement(updatePlayerSQL)) {
            preparedStatement.setDouble(1, newEarnings); // set the new highest earnings
            preparedStatement.setString(2, loginName); // find playerID by username
            
            int rowsUpdated = preparedStatement.executeUpdate(); 
    
            if (rowsUpdated > 0) { //if a row was updated 
                System.out.println("Player's highest earnings updated successfully.");
            } else { //else error 
                System.out.println("Player not found or unable to update earnings.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating player's highest earnings: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        // Create a DBManager instance (configure it with your actual database details)
        DBManager dbManager = DBManager.getInstance();

        // Create a PlayerManager instance
        PlayerManager playerManager = new PlayerManager(dbManager);

        // Test adding a new player
        playerManager.addNewPlayer("John doe", "test_user");

        // Test updating the highest earnings
        playerManager.updateHighestEarnings("test_user", 50000.0);

        // Close the database connection when done
        dbManager.closeConnections();
    }
    
}

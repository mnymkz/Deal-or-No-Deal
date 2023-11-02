package Model;

import Database.DBManager;
import Database.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Game manager class contains database operations for game
 *
 * @author Tabitha
 */
public class GameManager {

    private final DBManager dBManager;

    public GameManager(DBManager dBManager) {
        this.dBManager = dBManager;
    }
    
    /**
     * createNew Game creates a new game row 
     * @param username the username of the player
     * @throws java.sql.SQLException
     */
    public void createNewGame(String username) throws SQLException
    {
        String sql = "INSERT INTO GAME (currentRound, currentEarnings, playerID) VALUES (?, ?, ?)";
        //create a prepared statement
        int playerId = getPlayerID(username);
        dBManager.insert(sql, 1, 0.0, playerId);
    }
    
    /**
     * getPlayerID method retrieves playerID from database
     * 
     * @param username the username of the player 
     * @return the player id, returns -1 if error
     * @throws SQLException 
     */
    public int getPlayerID(String username) throws SQLException {
        int playerID = -1; // Default to -1 (or another suitable default value)
        //query db
        String query = "SELECT playerID FROM PLAYER WHERE username = ?";
        Result result = dBManager.queryDB(query, username);
        ResultSet resultSet = result.getResultSet();
        
        if (resultSet.next()) { // Check if there's a result
            playerID = resultSet.getInt("playerID"); // Assign the playerID from the result
        }
        
        return playerID;
    }
    
    /**
     * update chosen case of the player 
     * 
     * @param username the username of the player
     * @param chosenCase the new earnings
     */
    public void firstRound(String username, int chosenCase) throws SQLException {
        int playerID = getPlayerID(username);
        String query = "UPDATE GAME SET chosenCaseNumber = ? Where playerID = ?";
        
        try {
            dBManager.update(query, chosenCase, playerID); 
        } catch (SQLException ex) {
            System.out.println("Error updating chosen case number for " + username + ": " + ex.getMessage());
        }
    }
    
    /**
     * update current earnings updates the earnings of the player 
     * 
     * @param username the username of the player
     * @param newEarnings the new earnings
     */
    public void updateCurrentEarnings(String username, double newEarnings) throws SQLException 
    {
        int playerID = getPlayerID(username); //get playerID
        String query = "UPDATE GAME SET currentEarnings = ? WHERE playerID = ?";
        
        try {
            dBManager.update(query, newEarnings, playerID);
        } catch (SQLException ex) {
            System.out.println("Error updating earnings for " + username + ": " + ex.getMessage());
        }
    }
    
    /**
     * update the current round 
     * @param username the username of the player
     * @param round the round number
     * @throws SQLException 
     */
    public void updateCurrentRound(String username, int round) throws SQLException
    {
        int playerID = getPlayerID(username); //get playerID
        String query = "UPDATE GAME SET currentRound = ? WHERE playerID = ?";
        dBManager.update(query, round, playerID);
    }
    
    /**
     * getCurrent round retrieves the current round from the database
     * 
     * @param username the username of the player
     * @return the current round 
     */
    public int getCurrentRound(String username) throws SQLException {
        int currentRound = -1;
        int playerID = getPlayerID(username);
        
        //query db
        String query = "SELECT * FROM GAME where playerID = ?";
        ResultSet rs = dBManager.queryDB(query, playerID).getResultSet();
        if (rs.next()) { 
            currentRound = rs.getInt("currentRound"); //get current round 
        }
        
        if (currentRound == -1) {
            System.out.println("Error retrieving current round");
        }
        
        return currentRound;
    }
    
    /**
     * getCurrentEarnings method returns the current earnings of the player based on the player id
     * 
     * @param username the username of the player
     * @return the current earnings from the game table
     * @throws SQLException 
     */
    public int getCurrentEarnings(String username) throws SQLException  {
        int currentEarnings = -1;
        int playerID = getPlayerID(username);
        
        //queryDB
        String query = "SELECT * FROM GAME where playerID = ?";
        ResultSet rs = dBManager.queryDB(query, playerID).getResultSet();
        if (rs.next()) {
            currentEarnings = rs.getInt("currentEarnings"); //get current earnings
        }
        
        if (currentEarnings == -1) {
            System.out.println("Error retrieving currentEarnings"); //if earnings not updated, error

        }
        return currentEarnings;
    }
}

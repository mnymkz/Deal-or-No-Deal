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
    private final Game game;

    public GameManager(DBManager dBManager, Game game) {
        this.dBManager = dBManager;
        this.game = game;
    }
    
    /**
     * createNew Game creates a new game row 
     * @throws java.sql.SQLException
     */
    public void createNewGame() throws SQLException
    {
        String sql = "INSERT INTO GAME (currentRound, currentEarnings, numChoices, playerID) VALUES (?, ?, ?, ?)";
        //create a prepared statement
        int playerId = getPlayerID();
        dBManager.insert(sql, 1, 0.0, 6, playerId);
    }
    
    /**
     * getPlayerID method retrieves playerID from database
     * 
     * @return the player id, returns -1 if error
     * @throws SQLException 
     */
    public int getPlayerID() throws SQLException {
        int playerID = -1; // Default to -1 (or another suitable default value)
        String username = this.game.getUser().getUsername();
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
     * update current earnings updates the earnings of the player 
     * 
     * @param newEarnings the new earnings
     */
    public void updateCurrentEarnings(double newEarnings)
    {
        String query = "UPDATE PLAYER SET highestEarnings = ? WHERE username = ?";
        String username = this.game.getUser().getUsername();
        try {
            dBManager.update(query, username, newEarnings);
        } catch (SQLException ex) {
            System.out.println("Error updating earnings for " + username + ": " + ex.getMessage());
        }
    }
    
    /**
     * update the current round 
     * @param round
     * @throws SQLException 
     */
    public void updateCurrentRound(int round) throws SQLException
    {
        int playerID = getPlayerID(); //get playerID
        String query = "UPDATE GAME SET currentRound = ? WHERE playerID = ?";
        dBManager.update(query, round, playerID);
    }

        /**
     * getCurrent round retrieves the current round from the database
     * @return the current round 
     */
    public int getCurrentRound() throws SQLException {
        int currentRound = -1;
        int playerID = getPlayerID();
        
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
     * @return the current earnings from the game table
     * @throws SQLException 
     */
    public int getCurrentEarnings() throws SQLException  {
        int currentEarnings = -1;
        int playerID = getPlayerID();
        
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

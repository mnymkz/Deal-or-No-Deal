
package Login;

import Database.DBManager;
import Database.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class LoginManager {
 //playerManager fields
    private final DBManager dbManager;

    public LoginManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * register Login creates a new login if not already in database
     *
     * @param player
     */
    public void registerLogin(Player player) throws SQLException {
        String query = "INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)";
        dbManager.update(query, player.getUsername(), player.getPassword(), player.getHighestEarnings());
    }
    
    /**
     * playerExists method checks if a player exists in the database 
     * 
     * @param player the player to check
     * @return true if exists, else false
     */
    public boolean playerExists(Player player) throws SQLException {
        String query = "SELECT * FROM PLAYER WHERE username = ?";
        Result result = dbManager.queryDB(query); //get the result
        ResultSet rs = result.getResultSet(); //get the result set
        return rs.next();
    }
    
    /**
     * updates the player's highest earnings 
     * 
     * @param player the player to update
     * @param newEarnings the player's updated earnings
     */
    public void updateHighestEarnings(Player player, Double newEarnings) throws SQLException {
        if (newEarnings > 0) {
            double currentHighest = getHighestEarnings(player);
            if (newEarnings > currentHighest) {
                String sql = "UPDATE PLAYER SET highestEarnings = ? WHERE username = ?";
                dbManager.update(sql, player.getUsername());
            } else  {
                System.out.println("No update. Current Earnings less than highestEarnings");
            }
        } else {
            System.out.println("HighestEarnings cannot be less than 0");
        }
    }
    
    /**
     * Retrieve the highest earnings from the database for a player.
     *
     * @param player the player for which to retrieve the highest earnings
     * @return the highest earnings of the player, or -1.0 if not found or an error occurs
     */
    public double getHighestEarnings(Player player) throws SQLException {
        String query = "SELECT highestEarnings FROM PLAYER WHERE username = ?";
        int highestEarnings = -1;
        Result result = dbManager.queryDB(query, player.getUsername());
        if (result.getResultSet().next()) {
            return result.getResultSet().getDouble("highestEarnings");
        } else {
            System.out.println("Error retrieving highest earnings");
            return highestEarnings;
        }
    }
    
    /**
     * authenticates user login
     * 
     * @param player the player login
     * @return true if username and passwords match database
     */
    public boolean Auth(Player player) throws SQLException {
        String query = "SELECT * FROM PLAYER WHERE username = ? AND password = ?"; 
        Result result = dbManager.queryDB(query, player.getUsername(), player.getPassword());
        return result.getResultSet().next();
    }
    
    /**
     * getUser retrieves a user object from the user table
     * 
     * @param username the name of the user
     * @return a user if found, null if not found
     * @throws SQLException 
     */
    public Player getUserDB(String username) throws SQLException {
        
        //get ID
        String idQuery = "SELECT playerID FROM PLAYER WHERE username = ?";
        Result idResult = dbManager.queryDB(idQuery, username);
        if (idResult.getResultSet().next()) {
            int playerID = idResult.getResultSet().getInt("playerId");
            String query = "SELECT * FROM PLAYER WHERE playerID = ?";
            Result playerResult = dbManager.queryDB(query, playerID);
            playerResult.getResultSet();
        }
        
        //get Player
        String query = "SELECT * FROM PLAYER WHERE playerID = ?";
        Result playerResult = dbManager.queryDB(query, username);
        ResultSet rs = playerResult.getResultSet();
        if (rs.next()) {
            String name = rs.getString("username");
            String password = rs.getString("password");
            Double highestEarnings = rs.getDouble("highestEarnings");
            Player newPlayer = new Player(name, password);
            newPlayer.setHighestEarnings(highestEarnings);
            return newPlayer;
        }
        
       return null; //not found - return null
    }
}



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
        Result result = dbManager.queryDB(query, player.getUsername()); //get the result
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
                dbManager.update(sql, newEarnings, player.getUsername());
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
     * @param username the player's username
     * @param password the player's password
     * @return true if username and passwords match database
     * @throws java.sql.SQLException
     */
    public boolean Auth(String username, String password) throws SQLException {
        String query = "SELECT * FROM PLAYER WHERE username = ? AND password = ?"; 
        Result result = dbManager.queryDB(query, username, password);
        return result.getResultSet().next();
    }
    
    /**
     * getUser retrieves a user object from the user table
     * 
     * @param username the name of the user
     * @return a user if found, null if not found
     * @throws SQLException 
     */
    public Player getPlayer(String username) throws SQLException {
        String query = "SELECT * FROM PLAYER WHERE username = ?";

        //get result from query
        Result result = dbManager.queryDB(query, username);
        ResultSet rs = result.getResultSet();

        // If a result is returned, create and return a Player object
        if (rs.next()) {
            //get params
            String retrievedUsername = rs.getString("username");
            String password = rs.getString("password");
            double highestEarnings = rs.getDouble("highestEarnings");

            Player player = new Player(retrievedUsername, password);
            player.setHighestEarnings(highestEarnings);

            return player; //return player
        }

        return null; // not found, return null
    }
}



package Login;

import Database.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class UserManager {
 //playerManager fields
    private final DBManager dbManager;

    public UserManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * register Login creates a new login if not already in database
     *
     * @param player
     */
    public void registerLogin(User player) {
        String query = "INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            preparedStatement.setDouble(3, player.getHighestEarnings());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) { //check if inserted
                System.out.println("Player registered successfully.");
            }
        } catch (SQLException ex) {
            System.out.println("Error registering player: " + ex.getMessage());
        }
    }
    
    /**
     * playerExists method checks if a player exists in the database 
     * 
     * @param player the player to check
     * @return true if exists, else false
     */
    public boolean playerExists(User player) {
        String query = "SELECT * FROM PLAYER WHERE username = ?";

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a row is returned from the query
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking if player exists: " + e.getMessage());
            return false; // Return false in case of an error
        }
    }
    
    
    
    /**
     * updates the player's highest earnings 
     * 
     * @param player the player to update
     * @param newEarnings the player's updated earnings
     * @return true if successful else return false
     */
    public boolean updateHighestEarnings(User player, Double newEarnings) {
        if (newEarnings > 0) {
            String updatePlayerSQL = "UPDATE PLAYER SET highestEarnings = ? WHERE username = ?";

            try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(updatePlayerSQL)) {
                preparedStatement.setDouble(1, newEarnings); // set the new highest earnings
                preparedStatement.setString(2, player.getUsername()); // find playerID by username

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
     * Retrieve the highest earnings from the database for a player.
     *
     * @param player the player for which to retrieve the highest earnings
     * @return the highest earnings of the player, or -1.0 if not found or an
     * error occurs
     */
    public double getHighestEarnings(User player) {
        String query = "SELECT highestEarnings FROM PLAYER WHERE username = ?";

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) { //query db 
            preparedStatement.setString(1, player.getUsername());
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
    
    /**
     * authenticates user login
     * 
     * @param player the player login
     * @return true if username and passwords match database
     */
    public boolean Auth(User player) {
        String query = "SELECT * FROM PLAYER WHERE username = ? AND password = ?"; 

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getUsername());
            preparedStatement.setString(2, player.getPassword());
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if there is a matching user record with the same name and password
            }
        } catch (SQLException ex) {
            System.out.println("Error authenticating the user: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * getUser retrieves a user object from the user table
     * 
     * @param username the name of the user
     * @return a user if found, null if not found
     * @throws SQLException 
     */
    public User getUserDB(String username) throws SQLException {
        String query = "SELECT * FROM PLAYER WHERE username = ?";
        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username); //get query
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    Double highestEarnings = rs.getDouble("highestEarnings");
                    User newUser = new User(name, password);
                    newUser.setHighestEarnings(highestEarnings);
                }
            }
        } catch (SQLException ex) {
            //error loading user
            System.out.println("Error loading user from database: " + ex.getMessage());
        }   
       return null; //not found - return null
    }
}


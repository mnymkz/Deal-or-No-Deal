
package Logins;

import Database.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class LoginManager {
    
    private final DBManager dbManager;

    //constructor
    public LoginManager(DBManager dBConnect) {
        this.dbManager = dBConnect;
    }
    
    /**
     * register Login creates a new login if not already in database
     * 
     * @param login the login to be added
     */
    public void registerLogin(Login login) {
        String query = "INSERT INTO LOGIN (username, password) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, login.getUserName());
            preparedStatement.setString(2, login.getPassword());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) { //check if inserted
                System.out.println("Login registered successfully.");
            }
        } catch (SQLException ex) {
            System.out.println("Error registering the login: " + ex.getMessage());
        }
    }
   
    /**
     * authenticates user login
     * 
     * @param login object representing user login
     * @return true if username and passwords match database
     */
    public boolean Auth(Login login) {
        String query = "SELECT * FROM LOGIN WHERE username = ? AND password = ?"; 

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, login.getUserName());
            preparedStatement.setString(2, login.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if there is a matching user record
            }
        } catch (SQLException ex) {
            System.out.println("Error authenticating the user: " + ex.getMessage());
            return false;
        }
    }
    
    /**
     * userExists checks if user exists in the db
     * 
     * @param login the login of the user
     * @return true if exists, false if not exists 
     */
    public boolean userExists(Login login) {
        String query = "SELECT * FROM LOGIN WHERE username = ?"; 
        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, login.getUserName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if at least one row was found
            }
        } catch (SQLException e) {
            System.out.println("Error checking if the user exists: " + e.getMessage());
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        // Create a DBManager instance (you should configure it with your actual database details)
        DBManager dbManager = DBManager.getInstance();

        // Create a LoginManager instance
        LoginManager loginManager = new LoginManager(dbManager);

        // Create a new Login object for testing
        Login testLogin = new Login("test_user", "test_password");

        // Register a new login
        loginManager.registerLogin(testLogin);

        // Authenticate the user
        if (loginManager.Auth(testLogin)) {
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Authentication failed.");
        }

        // Check if the user exists
        if (loginManager.userExists(testLogin)) {
            System.out.println("User exists in the database.");
        } else {
            System.out.println("User does not exist in the database.");
        }

        // Close the database connection when done
        dbManager.closeConnections();
    }
}

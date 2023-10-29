
package Main;

import Main.Login;
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
    
    public static void main(String[] args) throws SQLException {
        // Create a DBManager instance (you should configure it with your actual database details)
        DBManager dbManager = DBManager.getInstance();
        LoginManager loginManager = new LoginManager(dbManager);
        Login login = new Login("test", "password");
        loginManager.registerLogin(login);
        System.out.println("Test auth: " + loginManager.Auth(login));
        System.out.println("User exists: " + loginManager.userExists(login));
        
        
        String deleteLoginSQL = "DELETE FROM LOGIN WHERE username = 'test'";
        dbManager.update(deleteLoginSQL);
        ResultSet resultSet = dbManager.queryDB("SELECT * FROM LOGIN");
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("loginID"); 
                String name = resultSet.getString("username"); 
                
                System.out.println("itemID: " + id + ", itemName: " + name); // Print the values
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            dbManager.closeConnections();
        }
        
        dbManager.closeConnections();
    }
}

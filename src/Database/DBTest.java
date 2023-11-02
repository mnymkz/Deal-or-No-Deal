
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBManager dbManager = DBManager.getInstance();
        dbManager.establishConnection();
        System.out.println(dbManager.getConnection());
        
        GameDB gameDb = new GameDB(dbManager);
        gameDb.initTables();
        
        ResultSet rsItems = dbManager.queryItems("SELECT * FROM ITEM");
        try {
            while (rsItems.next()) {
                int id = rsItems.getInt("itemID"); // Replace "ID" with the actual column name
                String name = rsItems.getString("itemName"); // Replace "NAME" with the actual column name
                // Get other columns as needed
                
                System.out.println("itemID: " + id + ", itemName: " + name); // Print the values
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        
        ResultSet rsPlayers = dbManager.queryItems("SELECT * FROM PLAYER");
        try {
            while (rsPlayers.next()) {
                // Get the data by column name or column index
                int playerId = rsPlayers.getInt("playerID");
                String username = rsPlayers.getString("username");
                String password = rsPlayers.getString("password"); // Note: Printing passwords is not a good security practice
                double highestEarnings = rsPlayers.getDouble("highestEarnings");

                // Print the row
                System.out.println("Player ID: " + playerId
                        + ", Username: " + username
                        + ", Password: " + password
                        + ", Highest Earnings: " + highestEarnings);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        ResultSet rsGames = dbManager.queryItems("SELECT * FROM GAME");
        try {
            while (rsGames.next()) {
                int gameId = rsGames.getInt("gameID");
                int currentRound = rsGames.getInt("currentRound");
                double currentEarnings = rsGames.getDouble("currentEarnings");
                int numChoices = rsGames.getInt("numChoices");
                int playerID = rsGames.getInt("playerId");
                
                
                System.out.println("gameId: " + gameId
                        + ", currentRound: " + currentRound
                        + ", currentEarnings: " + currentEarnings
                        + ", numChoices: " + numChoices
                        + ", playerID: " + playerID);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        dbManager.closeConnections();
    }
}
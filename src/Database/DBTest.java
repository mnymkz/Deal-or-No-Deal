
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
        
        ResultSet resultSet = dbManager.queryItems("SELECT * FROM ITEM");
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("itemID"); // Replace "ID" with the actual column name
                String name = resultSet.getString("itemName"); // Replace "NAME" with the actual column name
                // Get other columns as needed
                
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
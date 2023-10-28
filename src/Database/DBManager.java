
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBManager used to handle db connections, queries and updates
 * 
 * @author Michael
 */
public class DBManager {
    
    private static final String URL = "jdbc:derby:DealOrNoDeal_Edb; create=true";
    private static final String USER_NAME = "pdc"; 
    private static final String PASSWORD = "pdc";
    Connection conn;    
    private static DBManager instance;
    
    //singleton instance 
    private DBManager() {
        this.conn = null;
        establishConnection();
    }
    
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * ensures only one instance of dbManager is running
     * @return dbManager instance
     */
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }
    /**
     * establish connection to the database
     */
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * close connection to the database
     */
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                conn = null; // set conn to null after closing the connection
            }
        }
    }
    
    /**
     * queries the db and returns a result set
     * 
     * @param query
     * @return the result set from the database
     * @throws java.sql.SQLException
     */
    public ResultSet queryDB(String query) throws SQLException {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            conn.rollback();//rollback transaction
            System.out.println(e.getMessage());
        }
        return resultSet;
    }
    
    /**
	 * Execute update command on database.
	 * 
	 * @param updateStatement statements to execute as array.
     * @throws java.sql.SQLException
	 */
    public void update(String updateStatement) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute(updateStatement); //execute statement
        } catch (SQLException e) {
            conn.rollback(); //rollback transaction
            System.out.println("Error executing: " + e.getMessage() + e.getCause());
        }
    }
}

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * used to load items using statement in init SQL
     * 
     * @param query
     * @return the result set from the database
     * @throws java.sql.SQLException
     */
    public ResultSet queryItems(String query) throws SQLException {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            conn.rollback();//rollback transaction
            System.out.println(e.getMessage());
            throw e;
        }
        return resultSet;
    }
    
    /**
     * queries the db and returns a result set
     * used for queries that require params e.g WHERE NAME = ?
     * 
     * @param query the sql query statement 
     * @param parameters the query params
     * @return a result set from the query
     * @throws SQLException 
     */
    public Result queryDB(String query, Object... parameters) throws SQLException {
        Connection connection = this.conn;
        ResultSet resultSet = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String) {
                    ps.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Double) {
                    ps.setDouble(i + 1, (Double) parameters[i]);
                }
                // You can continue with other data types as necessary
            }

            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            conn.rollback(); //rollback transaction if necessary
            System.out.println(e.getMessage());
            throw e;
        }
        return new Result(resultSet, ps); //return result object containing prepared statement and result set
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
            System.out.println("Error executing: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * execute update command with params
     * 
     * @param sql
     * @param parameters
     * @throws SQLException 
     */
    public void update(String sql, Object... parameters) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) { //check if any rows were updated
                System.out.println("The row was updated successfully!");
            } else {
                System.out.println("No rows were updated.");
            }
        } catch (SQLException e) {
            conn.rollback(); //error, rollback transaction
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * insert method inserts into a table with parameters
     * used to prevent sql injections 
     * 
     * @param sql
     * @param parameters 
     */
    public void insert(String sql, Object... parameters) throws SQLException {
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String) {
                    preparedStatement.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Double) {
                    preparedStatement.setDouble(i + 1, (Double) parameters[i]);
                } // ... (you can add more types as needed)
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully!");
            }
        } catch (SQLException e) {
            conn.rollback();
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
}
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBManager manages database connections 
 * 
 * @author Michael
 */
public class DBManager {

    private static final String DB_URL = "jdbc:derby:DealOrNoDealDB;create=true;user=pdc;password=pdc"; //DB URL
    Connection conn; //connection 
    
    //constructor
    public DBManager()
    {
        connectDb();
    }

    public Connection getConn() {
        return conn;
    }
    
    //connect to db 
    public void connectDb()
    {
        try {
            //init derby driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Successful");
        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Error connecting to db");
            System.out.println(e.getMessage());
        }
    }
    
    //close db
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println("Error closing db");
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Get query from db 
     * 
     * @param query SQL query to database
     * @return resultSet containing matching query 
     */
    public ResultSet queryDB(String query) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    /**
     * update the db 
     * 
     * @param sqlStatement statement to update/delete/add to db
     */
    public void updateStatementDB(String sqlStatement) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
}
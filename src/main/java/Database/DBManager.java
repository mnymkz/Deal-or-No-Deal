package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBManager manages database connections 
 * @author Michael
 */
public class DBManager {

    private static final String DB_URL = "jdbc:derby:mydb;create=true;user=pdc;password=pdc";
    Connection conn;
    
    public DBManager()
    {
        establishConnection();
    }

    public Connection getConn() {
        return conn;
    }
    
    public void establishConnection()
    {
        try {
            Class.forName(DB_URL);
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection Successful");
        } catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

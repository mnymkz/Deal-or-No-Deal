
package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * abstract data manager class contains shared fields for object managers  
 * used to initialize rows and tables in the database
 * 
 * @author Michael
 */
public abstract class tableManager  {

    protected final DBManager dbManager;
    protected final Connection connection;
    protected Statement statement;
    
    public tableManager() {
        this.dbManager = new DBManager();
        this.connection = dbManager.getConn();
        this.statement = null;
    }
    
    /**
     * tableExists checks if a table exists
     * 
     * @param tableName the name of the table
     * @return true if exists, else return false
     */
    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData metaData = this.connection.getMetaData();
            tableName = tableName.toUpperCase();

            //Get the list of tables in the database
            try (ResultSet resultSet = metaData.getTables(null, null, tableName, null)) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * createTable method creates a table in the database
     * 
     * @param tableName the name of the table
     * @param createTableSQL the statement used to create the table
     */
    protected void createTable(String tableName, String createTableSQL) {
        if (!tableExists(tableName)) {
            try {
                this.statement = this.connection.createStatement();

                // Define the SQL query to create the table

                // Execute the SQL query to create the table
                statement.executeUpdate(createTableSQL);

                // Close the statement
                statement.close();

                System.out.println(tableName + " created successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    //initTable method declaration
    public abstract void initTable();
}

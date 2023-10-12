
package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * abstract data manager class contains shared fields for object managers  
 * used to initialize rows and tables in the database
 * used to insert/update/delete rows
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
    
    /**
     * insertRow inserts a row into the table 
     * 
     * @param tableName the name of the table to insert to 
     * @param insertSQL the statement to insert row 
     */
    protected void insertRow(String tableName, String insertSQL) {
        try {
            this.statement = this.connection.createStatement();

            // Execute the SQL query to insert the row
            statement.executeUpdate(insertSQL);

            // Close the statement
            statement.close();

            System.out.println("Row inserted into " + tableName + " successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * updateRow updates a row in the table
     * 
     * @param tableName
     * @param updateSQL 
     */
    protected void updateRow(String tableName, String updateSQL) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateSQL);

            // Execute the SQL query to update the row
            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            System.out.println(rowsAffected + " row(s) updated in " + tableName + " successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * deleteRow deletes a row from the table based on the provided SQL
     * statement.
     *
     * @param tableName the name of the table
     * @param deleteSQL the SQL statement to delete the row(s)
     */
    protected void deleteRow(String tableName, String deleteSQL) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(deleteSQL);

            // Execute the SQL query to delete the row(s)
            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            System.out.println(rowsAffected + " row(s) deleted from " + tableName + " successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 
    
    //initTable method declaration
    public abstract void initTable();
}

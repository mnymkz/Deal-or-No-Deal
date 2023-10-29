package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static Database.initSQL.*;
import java.util.Arrays;
import java.util.List;

/**
 * GameDB class used manage the tables in the db
 *
 * @author Michael
 */
public class GameDB {

    protected final DBManager dbConnect;
    protected final Connection connection;
    private final List<String> tableNames;
    private final List<String> createTableStatements;
    
    //constructor
    public GameDB(DBManager dbConnect) {
        this.dbConnect = dbConnect;
        this.connection = dbConnect.getConnection();
        this.tableNames = Arrays.asList("PLAYER", "GAME", "ITEM");
        this.createTableStatements = Arrays.asList(CREATE_PLAYER_TABLE, CREATE_GAME_TABLE, CREATE_ITEM_TABLE);
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
    private void createTable(String tableName, String createTableSQL) {
        if (!tableExists(tableName)) {
            Statement statement = null;
            try {
                statement = this.connection.createStatement();
                statement.executeUpdate(createTableSQL);
                statement.close();
                System.out.println(tableName + " created successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(tableName + " already exists.");
        }
    }
    
    /**
     * checks if a table is empty - will be used to populate ITEM table
     * 
     * @param tableName the name of the table
     * @return true if empty, else false
     */
    public boolean isTableEmpty(String tableName) {
        tableName = tableName.toUpperCase();
        String query = "SELECT COUNT(*) FROM " + tableName;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            //check if the result set is empty
            resultSet.next();
            int numRows = resultSet.getInt(1);
            return numRows == 0; //return if numRows = 0
        } catch (SQLException e) {
            System.out.println("Error checking if the table is empty: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * initTables loads tables into the database if not loaded already
     * @throws java.sql.SQLException
     */
    public void initTables() throws SQLException {
        //create tables
        for (int i = 0; i < tableNames.size(); i++) {
            String tableName = tableNames.get(i);
            String createTableSQL = createTableStatements.get(i);
            createTable(tableName, createTableSQL);
        }
        if (isTableEmpty("ITEM"))
        {
            System.out.println("Loading items...");
            this.dbConnect.update(INSERT_ITEMS);
        }
    }
}
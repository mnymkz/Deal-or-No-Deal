
package Database;

/**
 * SQLStatements class used to store SQL statements 
 * @author Michael
 */
public class SQLStatements {

    //SQL statement to create items table
    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE ITEMS ("
            + "ItemID INT PRIMARY KEY,"
            + "ItemName VARCHAR(255) NOT NULL,"
            + "ItemPrice DOUBLE NOT NULL,"
            + "ItemDesc VARCHAR(255) NOT NULL)";
    
    //SQL Statement to create players table
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE ITEMS ("
            + "PlayerID INT PRIMARY KEY,"
            + "Username VARCHAR(255) NOT NULL,"
            + "Password VARCHAR(255) NOT NULL,"
            + "ItemPrice DOUBLE NOT NULL)";
    
    //SQL Statements to insert into items table 
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Michael
 */
public class DBManagerTest {
    
    private static DBManager dbManager = DBManager.getInstance();

    public DBManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
        // create a test table
        Statement statement = dbManager.getConnection().createStatement();
        statement.execute("CREATE TABLE TestTable (id INT, name VARCHAR(255))");
        statement.execute("INSERT INTO TestTable VALUES (1, 'TestName')");
        
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        Statement statement = dbManager.getConnection().createStatement(); //delete test table
        statement.execute("DROP TABLE TestTable");
        dbManager.closeConnections(); //disconnect
    }
    
    @AfterEach
    public void tearDown() {
        dbManager.closeConnections();
    }

    /**
     * Test of getConnection method, of class DBManager.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        assertNotNull(dbManager.getConnection());
    }

    /**
     * Test of getInstance method, of class DBManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(dbManager);
    }

    /**
     * Test of establishConnection method, of class DBManager.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        dbManager.closeConnections(); //make sure connection is closed
        assertNull(dbManager.getConnection()); 

        dbManager.establishConnection(); //create new connection
        assertNotNull(dbManager.getConnection()); // make sure connection not null
    }

    /**
     * Test of closeConnections method, of class DBManager.
     */
    @Test
    public void testCloseConnections() {
        System.out.println("closeConnections");
        dbManager.establishConnection(); //create new connection
        assertNotNull(dbManager.getConnection()); // make sure connection not null
        dbManager.closeConnections(); //make sure connection is closed
        assertNull(dbManager.getConnection());
    }

    /**
     * Test of query methods, of class DBManager.
     */
    @Test
    public void testQueryDB() throws Exception {
        
        System.out.println("queryDB");
        dbManager.establishConnection();
        assertNotNull(dbManager.getConnection()); //create new connection

        //query the test table
        ResultSet resultSet = dbManager.queryItems("SELECT * FROM TestTable");
        assertNotNull(resultSet); // check if result not null
        assertTrue(resultSet.next()); //check if result is in result set
        
        //query the test table with params
        Result result = dbManager.queryDB("SELECT * FROM TestTable WHERE name = ?", "TestName");
        ResultSet resultSet2 = result.getResultSet();  //get the resultSet from the query
        assertNotNull(resultSet2); //result should not be null
        assertTrue(resultSet2.next()); //result should not be empty
        
        //close result set
        resultSet.close();
        result.closeResult();
    }
    
    /**
     * Test of queryDB method, of Class DBManager where params are invalid
     */
    @Test
    public void testQueryDBParams() throws SQLException {
        System.out.println("QueryDB with invalid params");
        dbManager.establishConnection();
        assertNotNull(dbManager.getConnection()); //create new connection
        
        Result result = dbManager.queryDB("SELECT * FROM TestTable WHERE name = ?", "Invalid");
        ResultSet resultSet2 = result.getResultSet();  //get the resultSet from the query
        assertNotNull(resultSet2); //result should be null
        assertFalse(resultSet2.next()); //resultSet should be empty
        
        result.closeResult(); //close result
    }
    
    /**
     * Test of insert method, of Class DBManager.
     * 
     * @throws SQLException 
     */
    @Test 
    public void testInsert() throws SQLException {
        System.out.println("Insert test data");
        dbManager.insert("INSERT INTO TestTable (id, name) VALUES (?, ?)", "2", "TestName2");

        Statement statement = dbManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM TestTable WHERE id = 2");
        
        assertTrue(resultSet.next()); // Check if a row is returned
        assertEquals("TestName2", resultSet.getString("name")); // Check the inserted value
        resultSet.close();
    }

    /**
     * Test of insert method, of Class DBManager, with correct parameters
     * 
     * @throws Exception 
     */
    @Test
    public void testUpdateWithCorrectParams() throws Exception {
        System.out.println("Update row with correct params");
        Statement statement = dbManager.getConnection().createStatement();
        dbManager.insert("INSERT INTO TestTable (id, name) VALUES (?, ?)", "3", "TestName3");
        dbManager.update("UPDATE TestTable SET name = ? WHERE id = 3", "NewName"); //update table
        ResultSet resultSet = statement.executeQuery("SELECT * FROM TestTable WHERE id = 3"); //get resultSet
        assertTrue(resultSet.next()); //make sure a result is not null
        assertEquals("NewName", resultSet.getString("name")); //make sure name = newName
        resultSet.close(); 
    }

    /**
     * Test of insert method, of Class DBManager, with invalid statement
     * 
     * @throws Exception 
     */
    @Test(expected = SQLException.class)
    public void testUpdateWithIncorrectSQL() throws Exception {
        System.out.println("Insert into non existent table");
        dbManager.update("INSERT INTO NonExistentTable (id, name) VALUES (?, ?)", "1", "TestName");
    }

    /**
     * Test of insert method, of Class DBManager, with no rows affected
     * 
     * @throws Exception 
     */
    @Test
    public void testUpdateNoRowsAffected() throws Exception {
        System.out.println("Update table where no rows affected");
        dbManager.update("UPDATE TestTable SET name = ? WHERE id = ?", "NewName", "1"); 
        //should not throw an exception
    }
}

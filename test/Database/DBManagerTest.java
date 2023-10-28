/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Database;

import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;

/**
 *
 * @author Michael
 */
public class DBManagerTest {
    
    private final DBManager dbManager = DBManager.getInstance();

    public DBManagerTest() {
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
     * Test of queryDB method, of class DBManager.
     */
    @Test
    public void testQueryDB() throws Exception {
        
        System.out.println("queryDB");
        dbManager.establishConnection();
        assertNotNull(dbManager.getConnection()); //create new connection

        // create a test table
        Statement statement = dbManager.getConnection().createStatement();
        statement.execute("CREATE TABLE TestTable (id INT, name VARCHAR(255))");
        statement.execute("INSERT INTO TestTable VALUES (1, 'TestName')");

        //query the test table
        ResultSet resultSet = dbManager.queryDB("SELECT * FROM TestTable");
        assertNotNull(resultSet); // check if result not null

        // delete test table
        resultSet.close();
        statement.execute("DROP TABLE TestTable");
    }

    /**
     * Test of update method, of class DBManager.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        dbManager.establishConnection();
        assertNotNull(dbManager.getConnection()); //create new connection

        // create test table
        Statement statement = dbManager.getConnection().createStatement();
        statement.execute("CREATE TABLE TestTable (id INT, name VARCHAR(255))");
        dbManager.update("INSERT INTO TestTable VALUES (1, 'TestName')"); //add some test data

        //query the test table
        ResultSet resultSet = dbManager.queryDB("SELECT * FROM TestTable");
        assertNotNull(resultSet); // check if result not null

        
        // delete test table
        resultSet.close();
        statement.execute("DROP TABLE TestTable");
    }
    
}

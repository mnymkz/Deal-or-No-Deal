/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Login;

import Database.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael
 */
public class UserManagerTest {
    
    private static DBManager dbManager;
    private UserManager userManager;
    
    public UserManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
    }
    
    @AfterClass
    public static void tearDownClass() {
        dbManager.closeConnections();
    }
    
    @Before
    public void setUp() {
        userManager = new UserManager(dbManager);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of playerExists method, of class UserManager.
     * @throws java.sql.SQLException error deleting test
     */
    @Test
    public void testPlayerExists() throws SQLException {
        System.out.println("Testing if playerExists when player is not in database");
        User userTest = new User("userTest", "password"); //new user
        boolean result = userManager.playerExists(userTest); //get result
        assertFalse(result); //unregistered player should not be in database
        
        System.out.println("Testing if playerExists when player is in database");
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES ('userTest', 'password', 0.00)"); //add player to database
        boolean result2 = userManager.playerExists(userTest); //get the result
        assertTrue(result2); //registered player should be in database
        
        dbManager.update("DELETE FROM PLAYER WHERE username = 'userTest'"); //remove test
        System.out.println("Test passed.");
    }
    
    /**
     * Test of registerLogin method, of class UserManager.
     * @throws java.sql.SQLException error deleting test
     */
    @Test
    public void testRegisterLogin() throws SQLException {
        System.out.println("registerLogin");
        //register user
        User userTest = new User("userTest", "password"); //new user
        userManager.registerLogin(userTest); //register user
        
        assertTrue(userManager.playerExists(userTest)); //check user exists
        
        dbManager.update("DELETE FROM PLAYER WHERE username = 'userTest'"); //remove test
        System.out.println("Test passed.");
    }

    /**
     * Test of updateHighestEarnings method, of class UserManager.
     * @throws java.sql.SQLException error getting query/deleting test
     */
    @Test
    public void testUpdateHighestEarnings() throws SQLException {
        System.out.println("updateHighestEarnings");
        
        //check user in database
        User userTest = new User("userTest", "password"); //new user
        userManager.registerLogin(userTest); //register user
        assertTrue(userManager.playerExists(userTest)); //check user exists
        
        //test updateEarnings
        double expectedEarnings = 100.00;
        userManager.updateHighestEarnings(userTest, expectedEarnings);
        
        //assert getEarnings = newEarnings
        double actualEarnings = 0.0; 
        ResultSet rs = dbManager.queryDB("SELECT highestEarnings FROM PLAYER WHERE username = 'userTest'"); //get actual earnings
        if (rs.next()) {
            actualEarnings = rs.getDouble("highestEarnings");
        }
        double delta = 0.001; 
        assertEquals(expectedEarnings, actualEarnings, delta); //check actual = newEarnings
        
        dbManager.update("DELETE FROM PLAYER WHERE username = 'userTest'"); //remove test
        System.out.println("Test passed.");
    }

    /**
     * Test of getHighestEarnings method, of class UserManager.
     * @throws java.sql.SQLException error deleting test
     */
    @Test
    public void testGetHighestEarnings() throws SQLException {
        System.out.println("getHighestEarnings");
        //new user
        User userTest = new User("userTest", "password"); //new user
        userManager.registerLogin(userTest); //register user
        assertTrue(userManager.playerExists(userTest)); //check user exists
        
        //setHighestEarnings
        double expectedEarnings = 100.00;
        userManager.updateHighestEarnings(userTest, expectedEarnings);
        //getHighestEarnings
        double actualEarnings = userManager.getHighestEarnings(userTest);
        
        //assert getHighest earnigns == expected earnings 
        double delta = 0.001;
        assertEquals(expectedEarnings, actualEarnings, delta); //check actual = newEarnings
        
        dbManager.update("DELETE FROM PLAYER WHERE username = 'userTest'"); //remove test
        System.out.println("Test passed.");
    }

    /**
     * Test of Auth method, of class UserManager.
     * 
     * @throws java.sql.SQLException
     */
    @Test
    public void testAuth() throws SQLException {
        System.out.println("Auth");
        //create user 
        User userTest = new User("userTest", "password"); //new user
        userManager.registerLogin(userTest); //register user
        assertTrue(userManager.playerExists(userTest)); //check user exists
        
        //assert Auth
        boolean expected = true;
        boolean result = userManager.Auth(userTest);
        assertEquals(expected, result);
        
        dbManager.update("DELETE FROM PLAYER WHERE username = 'userTest'"); //remove test
        System.out.println("Test passed.");
    }
    
}

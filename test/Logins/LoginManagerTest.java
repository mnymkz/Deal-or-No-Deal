/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Logins;

import Database.DBManager;
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
public class LoginManagerTest {
    
    private static DBManager dbManager;
    private LoginManager loginManager;
    private static final Login login = new Login("test", "password");
    
    public LoginManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        String deleteLoginSQL = "DELETE FROM LOGIN WHERE username = 'test'";
        dbManager.update(deleteLoginSQL);
        dbManager.closeConnections();
    }
    
    @Before
    public void setUp() {
        loginManager = new LoginManager(dbManager);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of userExists method, of class LoginManager when user is not in database
     */
    @Test
    public void testNotUserExists() {
        System.out.println("!userExists");
        Login login2 = new Login("test2", "password");
        assertFalse(loginManager.userExists(login2));
        System.out.println("Test passed");
    }
    
    /**
     * Test of Auth method, of class LoginManager with a user that has not been registered
     */
    @Test
    public void testNotAuth() {
        System.out.println("!Auth");
        Login login2 = new Login("test2", "password");
        assertFalse(loginManager.Auth(login2));
        System.out.println("Test passed.");
    }

    /**
     * Test of registerLogin method, of class LoginManager.
     */
    @Test
    public void testRegisterLogin() {
        System.out.println("registerLogin");
        loginManager.registerLogin(login);// sign up a test user into LOGIN
        assertTrue(loginManager.userExists(login)); // check if user exists 
        System.out.println("Test passed.");
    }
    

    /**
     * Test of Auth method, of class LoginManager with a user that has been registered
     * @throws java.sql.SQLException
     */
    @Test
    public void testAuth() throws SQLException {
        System.out.println("Auth");
        Login login3 = new Login("test3", "password");
        loginManager.registerLogin(login3); //register a new user
        assertTrue(loginManager.Auth(login3)); //check if auth returns true
        String deleteLoginSQL = "DELETE FROM LOGIN WHERE username = 'test3'"; //delte user
        dbManager.update(deleteLoginSQL);
        System.out.println("Test passed.");
    }
}

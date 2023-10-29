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
public class PlayerManagerTest {
    
    private static DBManager dbManager;
    private static PlayerManager playerManager;
    private Player player;
    private Login login;
    
    public PlayerManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        String deletePlayerSQL = "DELETE FROM PLAYER WHERE playerName = 'testPlayer'";
        dbManager.update(deletePlayerSQL);
        String deleteLoginSQL = "DELETE FROM LOGIN WHERE username = 'testLogin'";
        dbManager.update(deleteLoginSQL);
        dbManager.closeConnections();
    }
    
    @Before
    public void setUp() {
        login = new Login("testLogin", "password");
        player = new Player("testPlayer");
        playerManager = new PlayerManager(dbManager);
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of playerExists method, of class PlayerManager when player is not in database.
     */
    @Test 
    public void testNotPlayerExists()
    {
        System.out.println("notPlayeExists");
        Player test2 = new Player("testPlayer2");
        assertFalse(playerManager.playerExists(test2));
        System.out.println("Test passed.");
    }
    
    /**
     * Test of addNewPlayer method, of class PlayerManager.
     */
    @Test
    public void testAddNewPlayer() {
        System.out.println("addNewPlayer");
        playerManager.addNewPlayer(player, login.getUserName()); //player added into the database
        assertTrue(playerManager.playerExists(player)); //check if player exists
        System.out.println("Test passed.");
    }

    /**
     * 
     */
    @Test
    public void testGetHighestEarnings() {
        System.out.println("getHighestEarnings");
        playerManager.updateHighestEarnings(player.getName(), 100.00);
        Double highestEarnings = playerManager.getHighestEarnings(player);
        System.out.println(highestEarnings);
        assertTrue(highestEarnings == 100.00);
        System.out.println("Test passed");
    }
    
    /**
     * Test of updateHighestEarnings method, of class PlayerManager.
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpdateHighestEarnings() throws SQLException {
        System.out.println("updateHighestEarnings");
        
        playerManager.addNewPlayer(player, login.getUserName());
        assert(playerManager.playerExists(player)); //check player exists first
        
        double newEarnings = 200.00;
        playerManager.updateHighestEarnings(player.getName(), newEarnings); //update highest earnings
        
        double updatedEarnings = playerManager.getHighestEarnings(player); //get highest earnings
        assertEquals(newEarnings, updatedEarnings, 0.01); //check if earnings are updated successfully
        
        System.out.println("Test passed.");
    }
    
    /**
     * Test of updateHighestEarnings method, of class PlayerManager when a player is not in the database.
     */
    @Test
    public void testNotUpdateHighestEarnings() {
        System.out.println("!updateHighestEarnings");
        Player player2 = new Player("testPlayer2");
        assertFalse(playerManager.updateHighestEarnings(player2.getName(), 100.00));
    }
    
    /**
     * Test of updateHighestEarnings method, of class PlayerManager with invalid input.
     */
    @Test
    public void testInvalidUpdateHighestEarnings() {
        System.out.println("!updateHighestEarnings");
        playerManager.addNewPlayer(player, login.getUserName());
        assertTrue(playerManager.playerExists(player)); //check player exists first
        assertFalse(playerManager.updateHighestEarnings(player.getName(), -1.0)); //update highest earnings
    }
}

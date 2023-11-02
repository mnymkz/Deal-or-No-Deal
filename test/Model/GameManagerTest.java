/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import Database.DBManager;
import Database.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael
 */
public class GameManagerTest {

    private static DBManager dBManager;
    private static GameManager gameManager;
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        dBManager = DBManager.getInstance();
        dBManager.establishConnection(); //connect db
        gameManager = new GameManager(dBManager); //new gameManager
        dBManager.insert("INSERT INTO player (username, password, highestEarnings) VALUES (?, ?, ?)", "testUser", "password", 0.00); //create a test player
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        dBManager.update("DELETE FROM PLAYER WHERE username = ?", "testUser"); //delete test player
        dBManager.update("DELETE FROM GAME"); //clear table
        dBManager.closeConnections(); // disconnect
    }
    
    /**
     * Test of getPlayerID method, of class GameManager.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPlayerID() throws Exception {
        System.out.println("getPlayerID");
        int playerID = -1;
        playerID = gameManager.getPlayerID("testUser");
        assertNotEquals(-1, playerID); //make sure a player id is retrived from db 
    }

    /**
     * Test of createNewGame method, of class GameManager.
     */
    @Test
    public void testCreateNewGame() throws Exception {
        System.out.println("createNewGame");
        //create game
        gameManager.createNewGame("testUser");
        int playerID = gameManager.getPlayerID("testUser"); //get player id
        
        //check if result not null
        Result result = dBManager.queryDB("SELECT * FROM GAME WHERE playerID = ?", playerID);
        ResultSet rs = result.getResultSet();
        assertNotNull(rs); // rs should not be empty
        assertTrue(rs.next()); //rs should have a result
    }

    /**
     * Test of updateCurrentEarnings method, of class GameManager.
     */
    @Test
    public void testUpdateCurrentEarnings() throws SQLException {
        System.out.println("updateCurrentEarnings");
        
        gameManager.createNewGame("testUser"); //create new game
        
        //update earnings
        double expectedEarnings = 150.00;
        gameManager.updateCurrentEarnings("testUser", expectedEarnings); //update user current earnings
        double actualEarnings = gameManager.getCurrentEarnings("testUser"); //get updated earnings from db
        System.out.println(actualEarnings);
        assertEquals(expectedEarnings, actualEarnings, 0.001); //make sure expected = actual
        
        gameManager.updateCurrentEarnings("testUser", 0.00); //set earnings back to default
    }

    /**
     * Test of updateCurrentRound method, of class GameManager.
     */
    @Test
    public void testUpdateCurrentRound() throws Exception {
        System.out.println("updateCurrentRound");
        //create new game
        gameManager.createNewGame("testUser");
        gameManager.updateCurrentRound("testUser", 2); //update round
        int getCurrentRound = gameManager.getCurrentRound("testUser"); //get round
        assertEquals(getCurrentRound, 2); //round should be 2 
    }

    /**
     * Test of getCurrentRound method, of class GameManager.
     */
    @Test
    public void testGetCurrentRound() throws Exception {
        System.out.println("getCurrentRound");
        gameManager.createNewGame("testUser");
        int round = gameManager.getCurrentRound("testUser"); //get current round
        int roundDefault = 1; //new game starts on round 1 
        assertEquals(round, roundDefault); //round should be 1 by default
    }

    /**
     * Test of getCurrentEarnings method, of class GameManager.
     */
    @Test
    public void testGetCurrentEarnings() throws Exception {
        System.out.println("getCurrentEarnings");
        gameManager.createNewGame("testUser"); //create new game
        double currentEarnings = gameManager.getCurrentEarnings("testUser"); //get current earnings
        double defaultEarnings = 0.0;
        assertEquals(defaultEarnings, currentEarnings, 0.01); //current earnings should be 0 by default
    }
}

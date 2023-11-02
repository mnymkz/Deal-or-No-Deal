/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Model;

import Database.DBManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael
 */
public class GameManagerTest {

    private static DBManager dBManager;
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        dBManager = DBManager.getInstance();
        dBManager.establishConnection(); //connect db
        dBManager.insert("INSERT INTO player (username, password, highestEarnings) VALUES (?, ?, ?)", "testUser", "password", 100.00);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    


    /**
     * Test of createNewGame method, of class GameManager.
     */
    @Test
    public void testCreateNewGame() throws Exception {
        System.out.println("createNewGame");

    }

    /**
     * Test of getPlayerID method, of class GameManager.
     */
    @Test
    public void testGetPlayerID() throws Exception {
        System.out.println("getPlayerID");

    }

    /**
     * Test of updateCurrentEarnings method, of class GameManager.
     */
    @Test
    public void testUpdateCurrentEarnings() {
        System.out.println("updateCurrentEarnings");

    }

    /**
     * Test of updateCurrentRound method, of class GameManager.
     */
    @Test
    public void testUpdateCurrentRound() throws Exception {
        System.out.println("updateCurrentRound");

    }

    /**
     * Test of getCurrentRound method, of class GameManager.
     */
    @Test
    public void testGetCurrentRound() throws Exception {
        System.out.println("getCurrentRound");

    }

    /**
     * Test of getCurrentEarnings method, of class GameManager.
     */
    @Test
    public void testGetCurrentEarnings() throws Exception {
        System.out.println("getCurrentEarnings");

    }
    
}

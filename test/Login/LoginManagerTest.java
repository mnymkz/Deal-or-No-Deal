package Login;

import Database.DBManager;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginManagerTest {

    LoginManager loginManager;
    DBManager dbManager; 

    @Before
    public void setUp() throws Exception {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
        loginManager = new LoginManager(dbManager);
    }

    @After
    public void tearDown() throws Exception {
        dbManager.update("DELETE FROM PLAYER"); //clear player table
        dbManager.closeConnections(); //close connection
    }

    @Test
    public void testRegisterLogin() throws Exception {
        Player player = new Player("testUser", "testPass");
        loginManager.registerLogin(player);

        assertTrue(loginManager.playerExists("testUser")); //player should exist 
    }

    @Test
    public void testPlayerExists() throws Exception {
        Player player = new Player("testUser", "testPass"); //create new player
        assertFalse(loginManager.playerExists("testUser")); //player should not exist
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)", 
                player.getUsername(), player.getPassword(), player.getHighestEarnings()); //add player
        assertTrue(loginManager.playerExists("testUser")); //player should exist
    }

    @Test
    public void testUpdateHighestEarnings() throws Exception {
        Player player = new Player("testUser", "testPass");
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)",
                player.getUsername(), player.getPassword(), player.getHighestEarnings()); //add player
        double newEarnings = 2000.0;
        loginManager.updateHighestEarnings(player, newEarnings);

        // Verify the highestEarnings has been updated
        assertEquals(newEarnings, loginManager.getHighestEarnings(player), 0.01);
    }

    @Test
    public void testGetHighestEarnings() throws Exception {
        Player player = new Player("testUser", "testPass"); //create new player
        player.setHighestEarnings(1000.0);
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)",
                player.getUsername(), player.getPassword(), player.getHighestEarnings()); //add player
        // Assuming the user's highest earnings is 1000 in the database
        assertEquals(1000, loginManager.getHighestEarnings(player), 0.01);
    }

    @Test
    public void testAuth() throws Exception {
        Player player = new Player("testUser", "testPass");
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)",
                player.getUsername(), player.getPassword(), player.getHighestEarnings()); //add player
        assertFalse(loginManager.Auth(player.getUsername(), "wrong password")); //wrong password should return false
        assertTrue(loginManager.Auth(player.getUsername(), "testPass")); //correct password should return true
    }

    @Test
    public void testGetPlayer() throws Exception {
        
        //add exisiting user to db
        Player player = new Player("testUser", "testPass");
        player.setHighestEarnings(1000.0);
        dbManager.update("INSERT INTO PLAYER (username, password, highestEarnings) VALUES (?, ?, ?)",
                player.getUsername(), player.getPassword(), player.getHighestEarnings()); //add player
        
        Player retrievedPlayer = loginManager.getPlayer("testUser"); //get player from db

        // make sure player has expected attributes
        assertNotNull(retrievedPlayer);
        assertEquals("testUser", retrievedPlayer.getUsername());
        assertEquals("testPass", retrievedPlayer.getPassword());
        assertEquals(1000.0, retrievedPlayer.getHighestEarnings(), 0.0);

        Player nonExistentPlayer = loginManager.getPlayer("nonExistentUser"); //get a player that does not exist
        assertNull(nonExistentPlayer); //nonexistent player should be null
    }

}

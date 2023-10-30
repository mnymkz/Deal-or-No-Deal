package Model;

import Database.DBManager;
import Login.User;
import java.sql.SQLException;

/**
 * Game manager class contains database operations for game
 *
 * @author Tabitha
 */
public class GameManager {

    private final DBManager dBManager;
    private final User player;
    
    public GameManager(DBManager dBManager, User player) {
        this.player = player;
        this.dBManager = dBManager;
    }

    //create a new game method
    public void createNewGame()
    {
        String username = player.getUsername();
        try
        {
            String query = "INSERT INTO Games (username, earnings, round) VALUES ('"+ username +"' 0, 1)";
            dBManager.update(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //update current earnings 
    public void updateCurrentEarnings(String username, double newEarnings)
    {
        try
        {
            String query = "UPDATE Games SET earnings=" + newEarnings + " WHERE username='" + username + "'";
            dBManager.update(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //update current round
    public void updateCurrentRound(String username, int round)
    {
        try
        {
            String query = "UPDATE Games SET round=" + round + " WHERE username='" + username + "'";
            dBManager.update(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
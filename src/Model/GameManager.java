package Model;

import Database.DBManager;
import Login.User;
import java.sql.*;

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
        String query = "INSERT INTO Games (currentRound, currentEarnings, playerID) "
                    + "VALUES (1, 0, (SELECT playerID FROM PLAYER WHERE username=?))";
        
        try (PreparedStatement prepStmt = dBManager.getConnection().prepareStatement(query))
        {
           prepStmt.setString(1, username);
           prepStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //update current earnings 
    public void updateCurrentEarnings(double newEarnings, int gameID)
    {
        String query = "UPDATE GAME SET currentEarnings=? WHERE gameID=?";
        
        try(PreparedStatement prepStmt = dBManager.getConnection().prepareStatement(query))
        {
            prepStmt.setDouble(1, newEarnings);
            //prepStmt.setInt(2, gameID);
            prepStmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //update current round
    public void updateCurrentRound(int round, int gameID)
    {
        String query = "UPDATE GAME SET currentRound=? WHERE gameID=?";
        
        try(PreparedStatement prepStmt = dBManager.getConnection().prepareStatement(query))
        {
            prepStmt.setInt(1, round);
            prepStmt.setInt(2, gameID);
            prepStmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    //get current round, to store round in 
    public int getCurrentRound(int gameID)
    {
        String query = "SELECT currentRound FROM GAME WHERE gameID=?";
        int round = 0;
        
        try(PreparedStatement prepStmt = dBManager.getConnection().prepareStatement(query))
        {
            prepStmt.setInt(1, gameID);
            ResultSet resultSet = prepStmt.executeQuery();
            
            if(resultSet.next())
            {
                round = resultSet.getInt("currentRound");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return round;
    }
    
    //get numChoices
}
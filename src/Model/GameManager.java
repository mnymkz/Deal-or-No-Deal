package Model;

import Database.DBManager;
import Login.User;

/**
 * Game manager class contains database operations for game
 *
 * @author Michael
 */
public class GameManager {

    private final DBManager dBManager;
    private final User player;
    
    public GameManager(DBManager dBManager, User player) {
        this.player = player;
        this.dBManager = dBManager;
    }

    //get current earnings 
    //update current earnings 
}

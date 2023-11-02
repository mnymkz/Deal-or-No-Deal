
package Model;

import Case.CaseLoader;
import Case.ItemGenerator;
import Database.DBManager;
import Database.GameDB;
import Login.LoginManager;
import Login.Player;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * model class loads game objects into the program.
 * 
 * @author Tabitha
 */
public class Model {
    
    private DBManager dBManager;
    private GameDB gameDB;
    private Game game;
    private Player currentPlayer;
    private LoginManager loginManager;
    private CaseLoader caseLoader;
    private GameManager gameManager;
    
    public Model() throws SQLException
    {
        //init database
        dBManager = DBManager.getInstance();
        dBManager.establishConnection();
        gameDB = new GameDB(dBManager);
        gameDB.initTables();
        //init managers
        this.loginManager = new LoginManager(dBManager);
        this.gameManager = new GameManager(dBManager);
        this.caseLoader = new CaseLoader(dBManager);
        this.currentPlayer = null;
        this.game = new Game(caseLoader);
    }
    
    /**
     * Create player creates a new login if not exists in database
     * 
     * @param username the player username from textfield
     * @param password the player password from textfield
     * @return true if successfully created and stored in database, else return false
     * @throws SQLException 
     */
    public boolean createPlayer(String username, String password) throws SQLException {
        Player player = new Player(username, password);
        if (loginManager.playerExists(username)) {
            System.out.println("Login already exists!");
            return false;
        } else {
            this.currentPlayer = player;
            System.out.println("New player created");
            loginManager.registerLogin(currentPlayer); //add user to database 
            return true;
        }
    }
    
    
    
    
    
//    //load game objects
//    public void gameObjectsLoad(ArrayList<Case> cases, Banker banker)
//    {
//        this.game = new Game(currentPlayer, banker, cases);
//    }
//    
//    //get user from the login page
//    public Player getUserFromLogin(String username, String password)
//    {
//        //Validate user credentials?
//        // -> If validation successful, create and return a new Player object
//        this.currentPlayer = new Player(username, password);
//        return this.currentPlayer;
//    }
//    
//    public void removeCase(int caseNumber)
//    {
//        game.removeCase(caseNumber);
//    }
//    
//    //Get the current game object
//    public Game geCurrentGame()
//    {
//        return this.game;
//    }
//    
//    //Set the current game object
//    public void setCurrentGame(Game game)
//    {
//        this.game = game;
//    }

    public DBManager getdBManager() {
        return dBManager;
    }

    public GameDB getGameDB() {
        return gameDB;
    }

    public Game getGame() {
        return game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public CaseLoader getCaseLoader() {
        return caseLoader;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
    
    
}

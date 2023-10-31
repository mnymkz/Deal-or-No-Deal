
package Model;

import Login.User;
import Model.Game;
import Banker.Banker;
import java.util.ArrayList;
import Case.Case;

/**
 * model class loads game objects into the program.
 * 
 * @author Tabitha
 */
public class Model {

    
    private User user;
    private Game game;
    
    public Model()
    {
        //Initialise the game or user ?
    }
    
    //create a test user 
    
    //load game objects
    public void gameObjectsLoad(ArrayList<Case> cases, Banker banker)
    {
        this.game = new Game(user, banker, cases);
    }
    
    //get user from the login page
    public User getUserFromLogin(String username, String password)
    {
        //Validate user credentials?
        // -> If validation successful, create and return a new User object
        this.user = new User(username, password);
        return this.user;
    }
    
    public void removeCase(int caseNumber)
    {
        game.removeCase(caseNumber);
    }
    
    //Get the current game object
    public Game geCurrentGame()
    {
        return this.game;
    }
    
    //Set the current game object
    public void setCurrentGame(Game game)
    {
        this.game = game;
    }
}

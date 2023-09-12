package Main;

import Rounds.GameLoop;
import Logins.LoginLoop;


/**
 * Main Class will serve as the entry point for the program - contains 
 * user login and game loop
 * 
 * @author Michael, Tab
 */
public class Main {
    
    public static void main(String[] args) {
        //login
        LoginLoop l = new LoginLoop();
        l.start();
        //if user did not quit
        if (!l.isQUIT())
        {
            //run game
            GameLoop g = new GameLoop();
            g.start();
        }
    }
}

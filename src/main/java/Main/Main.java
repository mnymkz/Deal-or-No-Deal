package Main;

import FileIO.CaseManager;
import Logins.LoginLoop;
import java.util.Random;


/**
 *
 * Main Class will serve as the entry point for the program - contains 
 * user login, game loop and i/o
 * 
 * @author Michael
 */
public class Main {
    
    public static void main(String[] args) {
        GameLoop g = new GameLoop();
        g.start();
    }
}

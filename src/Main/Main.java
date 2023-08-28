package Main;

import Logins.LoginLoop;


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

package Main;

import Banker.Banker;
import Banker.RandomBanker;
import FileIO.CaseManager;
import FileIO.PlayerManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Michael, Tabitha
 */
public class GameLoop {

    //scanner field 
    private Scanner scan = new Scanner(System.in);
    private CaseManager caseManager;
    private PlayerManager playerManager;
    private HashSet chosenNumbers;
    private Player player;
    
    //constructor
    public GameLoop() {
        this.caseManager = new CaseManager();
        caseManager.load();
        this.chosenNumbers = new HashSet();
        this.player = new Player();
    }

    /**
     * start method starts the game loop
     */
    void start() {

        while (true) {
            
            //get new player 
            System.out.println("WELCOME TO DEAL OR NO DEAL!");
            System.out.println("THE RULES ARE SIMPLE:\n");
            System.out.println("1. A PLAYER CHOOSES A RANDOM CASE IN WHICH THEY THINK HAS THE HIGHEST VALUE");
            System.out.println("2. IN EACH ROUND, A PLAYER GETS TO ELIMINATE CASES WHICH THEY THINK DO NOT HAVE THE HIGHEST VALUE");
            System.out.println("3. THE BANKER CAN MAKE AN OFFER TO THE PLAYER, ALLOWING THEM TO SAY DEAL (ACCEPT THE OFFER)");
            System.out.println("   OR NO DEAL (DECLINE THE OFFER).");
            System.out.println("4. THE GAME ENDS WHEN THE PLAYER REACHES THE FINAL ROUND OR ACCEPTS THE BANKERS OFFER.\n");
            System.out.println("PRESS X TO QUIT AT ANYTIME");
            System.out.println("PLEASE ENTER YOUR NAME!");
            String name = scan.nextLine().trim();
            //if x is pressed, break
            if (!checkPlayerName(name))
            {
                break;
            }
            this.player.setName(name);
            System.out.println("\nWELCOME "+name+"!");

            //run the first round
            Round firstRound = new FirstRound(caseManager, playerManager, chosenNumbers, player, 0, 0);
            firstRound.startRound();
            if (firstRound.getQUIT())
            {
                //break
                break;
            }
    
            //TODO use a loop HERE to run the rounds instead of recursive calling 
            Round r1 = new Round(caseManager, playerManager, chosenNumbers, player, 6, 1);
            r1.startRound();
            if (r1.getQUIT())
            {
                break;
            }
            
            //ask if user wants to play again
            System.out.println("\nThank you for playing Deal or No Deal " + this.player.getName() + "!\n");
            System.out.println(this.player.toString());
            System.out.println("\nPLAY AGAIN? Y/N");
            String input = scan.nextLine().trim();
            //if yes run loop, else break
            if (!playAgain(input)) {
                break;
            } else {
                System.out.println("STARTING NEW GAME...");
            }
        }
    }

    /**
     * checks if user input is x
     * 
     * @return -1 if x, else return 0 
     */
    private boolean checkPlayerName(String name) {
        while (true) {
            if (name.equalsIgnoreCase("x")) {
                //quit
                return false;
            } else {
                return true;
            }
        }
    }
    
    /**
     * playAgain processes user input and returns an int value
     * 
     * @param input
     * @return false if user presses x or n, true if user presses y
     */
    private boolean playAgain(String input)
    {
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                return false;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else if (input.equalsIgnoreCase("y")) {
                return true;
            }
            else {
                System.out.println("PLAY AGAIN? Y/N");
                input = scan.nextLine().trim();
            }
        }
    }
}

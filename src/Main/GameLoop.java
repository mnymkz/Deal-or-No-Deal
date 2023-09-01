package Main;

import Banker.Banker;
import Banker.RandomBanker;
import FileIO.CaseManager;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class GameLoop {

    //scanner field 
    private Scanner scan = new Scanner(System.in);
    private CaseManager cm;
    private HashSet chosenNumbers;
    private Player player;
    
    //constructor
    public GameLoop() {
        this.cm = new CaseManager();
        cm.load();
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
            System.out.println("THE RULES ARE SIMPLE:");
            System.out.println("1. A PLAYER CHOOSES A RANDOM CASE IN WHICH THEY THINK HAS THE HIGHEST VALUE");
            System.out.println("2. IN EACH ROUND, A PLAYER GETS TO ELIMINATE CASES WHICH THEY THINK DO NOT HAVE THE HIGHEST VALUE");
            System.out.println("3. THE BANKER CAN MAKE AN OFFER TO THE PLAYER, ALLOWING THEM TO SAY DEAL (ACCEPT THE OFFER)");
            System.out.println("   OR NO DEAL (DECLINE THE OFFER).");
            System.out.println("4. THE GAME ENDS WHEN THE PLAYER REACHES THE FINAL ROUND OR ACCEPTS THE BANKERS OFFER.");
            System.out.println("PLEASE ENTER YOUR NAME!");
            String name = scan.nextLine().trim();
            //if x is pressed, break
            if (!checkPlayerName(name))
            {
                break;
            }
            this.player.setName(name);
            System.out.println("WELCOME "+name+"!");
            
            
            //round implementation 
            //choose banker
            //round n start
                //if first round allow the player to choose their starting case
                //if last round allow the player to swap their current case with the last case
            //if round.quit is !quit, break
            Banker banker = new RandomBanker("John");
            Round roundTest = new FirstRound(cm, chosenNumbers, player, banker, 6);
            roundTest.startRound();
            if (roundTest.getQUIT())
            {
                break;
            }
            //TODO - implement all 10 rounds 
            
            
            
            //ask if user wants to play again
                //if yes run loop
                //else break
            System.out.println("CONGRATS " + this.player.getName() +"!");
            System.out.println("PLAY AGAIN? Y/N");
            String input = scan.nextLine().trim();
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
        }
    }
    
    //TODO generate bankers for rounds
}

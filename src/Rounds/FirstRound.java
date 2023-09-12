
package Rounds;

import Logins.Player;
import Banker.Banker;
import Case.Case;
import FileIO.CaseManager;
import FileIO.PlayerManager;
import java.util.HashSet;

/**
 * FirstRound class extends round 
 * Overrides the startRound method
 * 
 * @author Tabitha, Michael
 */
public class FirstRound extends Round {

    //constructor
    public FirstRound(CaseManager caseManager, PlayerManager playerManager, HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        super(caseManager, playerManager, chosenNumbers, player, numChoices, currentRound);
    }

    /**
     * for the first round, the user will only pick their briefcase
     */
    @Override
    public void startRound()
    {
        displayCases();
        //get player to select their starting case
        System.out.println("SELECT THE CASE WHICH YOU BELIEVE HAS THE HIGHEST AMOUNT");
        String userInput = scan.nextLine().trim();
        if (chooseFirstCase(userInput) == -1) {
            this.QUIT = !QUIT;
            return;
        }
    }
    
    /**
     * chooseStarting case method gets an input from the user 
     * checks to see if input valid, if valid
     * get the starting case
     * 
     * @param input the input from keyboard
     * @return 0 if successful, else return -1
     */
    protected int chooseFirstCase(String input) {
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                break;
            }
            try {
                int choice = Integer.parseInt(input)-1;
                if (choice >= 0 && choice <= 25) {
                    //FirstRound method implementation
                    chosenNumbers.add(choice);
                    //Set players first case to chosen case
                    Case firstCase = caseManager.getCases().get(choice);
                    caseManager.getCases().get(choice).openCase();
                    player.setFirstChoice(firstCase);
                    player.setEarnings(firstCase.getItem().getMoneyValue());
                    playerManager.getPlayerScores().put(player.getName(), player.getEarnings());
                    return 0;
                } else {
                    System.out.println("PLEASE SELECT A CASE BETWEEN 1 AND 26!");
                    input = scan.nextLine().trim();
                }
            } catch (NumberFormatException E) {
                System.out.println("PLEASE ENTER AN INTEGER BETWEEN 1 AND 26!");
                input = scan.nextLine().trim();
            }
        }
        //user pressed x, return -1;
        return -1;
    }
}

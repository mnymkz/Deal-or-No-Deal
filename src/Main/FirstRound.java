
package Main;

import Banker.Banker;
import Case.Case;
import FileIO.CaseManager;
import java.util.HashSet;

/**
 *
 * @author Michael, Tabitha
 */
public class FirstRound extends Round {

    public FirstRound(CaseManager cm, HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        super(cm, chosenNumbers, player, numChoices, currentRound);
    }

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

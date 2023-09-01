
package Main;

import Banker.Banker;
import Case.Case;
import FileIO.CaseManager;
import java.util.HashSet;

/**
 *
 * @author Michael
 */
public class FirstRound extends Round {

    public FirstRound(CaseManager cm, HashSet chosenNumbers, Player player, Banker banker, int numChoices) {
        super(cm, chosenNumbers, player, banker, numChoices);
    }
    
    @Override
    public void startRound()
    {
        displayCases();
        //get player to select their starting case
        System.out.println("SELECT THE CASE WHICH YOU BELIEVE HAS THE HIGHEST AMOUNT");
        String userInput = scan.nextLine().trim();
        chooseCase(userInput);
        if (chooseCase(userInput) == -1) {
            return;
        }

    }
    
    @Override
        protected int chooseCase(String input) {
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 26) {
                    //FirstRound method implementation
                    this.chosenNumbers.add(choice);
                    //Set players first case to chosen case
                    Case firstCase = cm.getCases().get(choice);
                    this.player.setFirstChosenCase(firstCase);
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

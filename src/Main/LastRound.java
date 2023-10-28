
package Main;

import Logins.Player;
import Banker.Banker;

import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Tabitha, Michael
 */
public class LastRound extends Round {

    public LastRound(CaseManager cm, PlayerManagerOld playerManager,HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        super(cm, playerManager, chosenNumbers, player, numChoices, currentRound);
    }

    
    // @Override
    public void startRound(int numChoices, int currentRound)
    {
        displayCases();
        //ask the user if they want to swap cases or keep their current case

        System.out.println("ROUND " + currentRound + "\n\n You have two cases left! Do you want to keep your briefcase or switch it?");

        
    }

}

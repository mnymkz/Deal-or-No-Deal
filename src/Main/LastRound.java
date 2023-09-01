
package Main;

import Banker.Banker;
import FileIO.CaseManager;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class LastRound extends Round {

    public LastRound(CaseManager cm, HashSet chosenNumbers, Player player, Banker banker, int numChoices) {
        super(cm, chosenNumbers, player, banker, numChoices);
    }
    
    @Override
    public void startRound()
    {
        //ask the user if they want to swap cases or keep their current case
    }

}

package Main;

import java.util.List;
import Banker.Banker;
import Case.Case;
import Player.Player;
import java.util.ArrayList;
/**
 *
 * @author Tabitha
 */
public class Round 
{
    private ArrayList<Case> casesRemaining = new ArrayList<Case>();
    private Player player;
    private Banker banker;

    //constructor
    public Round(ArrayList<Case> casesRemaining, Player player, Banker banker)
    {
        this.casesRemaining = new ArrayList<Case>();
        this.player = player;
        this.banker = banker;
    }

//     public double averageRemainingCases()
//     {
//         int remainingCases = 0;
//         double totalCasesValue = 0;

// <<<<<<< HEAD
//         //for(int i = 0; casesRemaining > 0; i++)
//         //{
//         //    
//         //}

// =======
// //        for(int i = 0; casesRemaining > 0; i++)
// //        {
// //            if()
// //        }
// >>>>>>> master
//         return 0;
//     }

    public boolean revealCase()
    {
        return true;
    }

    //entry point of the round
    public void startRound()
    {
        //Display information about the round such as the number of remaining cases
        //Player interaction
        //Case interaction

    }
}

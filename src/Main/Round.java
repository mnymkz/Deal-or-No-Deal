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

    public double averageRemainingCases()
    {
        int remainingCases = 0;
        double totalCasesValue = 0;

//        for(int i = 0; casesRemaining > 0; i++)
//        {
//            if()
//        }
        return 0;
    }

    public void start()
    {
        
    }
}


package Banker;

/**
 * Aggressive banker extends banker class 
 * gives more "aggressive" offers to the player
 * 
 * @author Tabitha
 */
public class AggressiveBanker extends Banker
{
    //constructor
    public AggressiveBanker(String name)
    {
        super(name, "Aggresive");
    }

    //calculate banker offer with the average of the item values 
    @Override
    public double bankerOffer(double average)
    {
        double finalOffer = average * 0.8;

        setOffer(finalOffer);
        printBankerOffer();
        
        return finalOffer;
    }
}

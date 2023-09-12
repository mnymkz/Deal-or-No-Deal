
package Banker;

/**
 * RandomBanker class extends banker
 * gives "random" offers to the player
 * 
 * @author Tabitha
 */
public class RandomBanker extends Banker
{
    //constructor
    public RandomBanker(String name)
    {
        super(name, "Random");
    } 

    //calculate banker offer with the average of the item values
    @Override
    public double bankerOffer(double average)
    {
        double randPercentage = Math.random() * 0.9;
        double finalOffer = average * randPercentage;

        setOffer(finalOffer);
        printBankerOffer();

        return finalOffer;
    }
}

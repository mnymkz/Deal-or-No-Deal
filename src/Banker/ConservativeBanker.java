
package Banker;

/**
 * Conservative banker class extends banker
 * gives more "conservative" offers to the player
 * 
 * @author Tabitha
 */
public class ConservativeBanker extends Banker
{
    //constructor
    public ConservativeBanker(String name)
    {
        super(name, "Conservative");
    }

    //calculate banker offer with the average of the item values
    @Override
    public double bankerOffer(double average)
    {
        double finalOffer =  average * 0.7;

        setOffer(finalOffer);
        printBankerOffer();
        
        return finalOffer;
    }
}

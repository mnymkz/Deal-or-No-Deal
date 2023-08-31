
package Banker;

/**
 *
 * @author Tabitha
 */
public class AggressiveBanker extends Banker
{
    public AggressiveBanker(String name)
    {
        super(name, "Aggresive");
    }

    @Override
    public double bankerOffer(double average)
    {
        double finalOffer = average * 0.8;

        setOffer(finalOffer);
        printBankerOffer();
        
        return finalOffer;
    }
}

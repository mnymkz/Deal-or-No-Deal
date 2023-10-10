
package Banker;

/**
 *
 * @author Tabitha
 */
public class RandomBanker extends Banker
{
    public RandomBanker(String name)
    {
        super(name, "Random");
    } 

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

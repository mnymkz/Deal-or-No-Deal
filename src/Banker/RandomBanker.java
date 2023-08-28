
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
        return average * Math.random();
    }
}

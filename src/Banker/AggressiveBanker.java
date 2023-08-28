
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
        return average * 0.8;
    }
}

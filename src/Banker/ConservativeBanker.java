
package Banker;

/**
 *
 * @author Tabitha
 */
public class ConservativeBanker extends Banker
{
    public ConservativeBanker(String name)
    {
        super(name, "Conservative");
    }

    @Override
    public double bankerOffer(double average)
    {
        return average * 0.6;
    }
}

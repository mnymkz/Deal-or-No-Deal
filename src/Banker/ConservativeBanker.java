
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
        double finalOffer =  average * 0.6;

        setOffer(finalOffer);
        printBankerOffer();
        
        return finalOffer;
    }
}

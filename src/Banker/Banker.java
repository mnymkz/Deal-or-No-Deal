
package Banker;

/**
 *
 * @author Tabitha
 */
public class Banker 
{
    private String name;
    private double offerDeal;
    private String strategy;

    public Banker(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public double getOfferDeal()
    {
        return offerDeal;
    }

    public void setOfferDeal(double offerdeal)
    {
        this.offerDeal = offerDeal;
    }

    //make a function to calculate logic of the game
    //consult about how we want the rules of the game to be, if we want to alter anything
}

package Banker;

/**
 *
 * @author Tabitha
 */
public class Banker 
{
    private String name;
    private double offer;

    public Banker(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public double getOffer()
    {
        return offer;
    }

    public void setOffer(double offer)
    {
        this.offer = offer;
    }

    //make a function to calculate logic of the game
    //consult about how we want the rules of the game to be, if we want to alter anything
}
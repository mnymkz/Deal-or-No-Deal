
package Banker;

/**
 *
 * @author Tabitha
 */
public class Banker 
{
    private String name;
    private double offer;
    private String strategy;

    //constructor
    public Banker(String name, String strategy)
    {
        this.name = name;
        this.strategy = strategy;
    }

    //get and set methods
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

    public String getStrategy()
    {
        return strategy;
    }

    public void setStrategy(String strategy)
    {
        this.strategy = strategy;
    }

    
    public double bankerOffer(double average)
    {
        return 0;
    }
}
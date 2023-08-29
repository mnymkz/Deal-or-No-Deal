
package Banker;

import java.util.ArrayList;
import java.util.List;

import Case.Case;
import Case.Item;

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


    public double createOffer(int round, int playerAmount, ArrayList<Case> cases)
    {
        int totalValueItems = 0;
        int numItems = 0;
        
        //iterating through every opened case
        for(Case currentCase : cases)
        {
            if(currentCase.isOpened())
            {
                Item items = currentCase.getItem();
                //why isnt items working???
                for (Item item : items) {
                    totalValueItems += item.getMoneyValue();
                    numItems++;
                }
            }
        }

        //calculating the average of the value of the items 
        double valueOfItemAverage;
        if(numItems > 0)
        {
            valueOfItemAverage = (double) totalValueItems / numItems;
        }
        else 
        {
            valueOfItemAverage = 0;
        }

        //make calculation based on the player on the round, player amount and the average value of the items 
    }
    
    //to be overriden by extended classes
    public double bankerOffer(double average)
    {
        return 0;
    }
}
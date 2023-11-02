
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


    public double createOffer(ArrayList<Case> cases)
    {
        int totalValueItems = 0;
        int numItems = 0;
        
        //iterating through every opened case
        for(Case currentCase : cases)
        {
            if(!currentCase.isOpened())
            {
                Item items = currentCase.getItem();
                
                if(items != null)
                {
                    totalValueItems += items.getMoneyValue();
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

        return valueOfItemAverage;
    }
    
    //to be overriden by extended classes
    public double bankerOffer(double average)
    {
        return 0;
    }

    //print banker offer
    public void printBankerOffer()
    {
        System.out.println("\nBanker's offer: $" +  Math.round(getOffer()));
    }
    
    /**
     * Calculate offer returns an offer based on the cases left 
     * 
     * @return an offer
     */
    public double calculateOffer()
    {
        System.out.println("TODO");
        return 0;
    }
}
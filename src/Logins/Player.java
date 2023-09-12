
package Logins;

import Case.Case;


/**
 * Player class holds details and methods for the player 
 * 
 * @author Michael, Tabitha
 */
public class Player {
    
    //player fields
    private String name;
    private double earnings;
    private Case firstChoice;
    
    /**
     * player constructor 
     * 
     */
    public Player()
    {
        this.name = "";
        this.earnings = 0;
        this.firstChoice = null;
    }

    
    /**
     * player constructor generates a new player with a blank high score
     * @param name the name of the new player
     */
    public Player(String name)
    {
        this.name = name;
        this.earnings = 0;
        this.firstChoice = null;
    }

    /**
     * toString returns name and earnings as a string
     * 
     * @return name and earnings 
     */
    public String toString()
    {
        if(this.getEarnings() == 0.01)
        {
            return this.getName() + "'s earnings: $" + this.getEarnings();
        }
        return this.getName() + "'s earnings: $" + Math.round(this.getEarnings());
    }
    
    /**
     * toCSV returns name and earnings in csv format for fileIO
     * 
     * @return csv formatted name and earnings
     */
    public String toCSV()
    {
        return this.name+","+this.earnings;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }

    public Case getFirstChoice() {
        return firstChoice;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public void setFirstChoice(Case firstChoice) {
        this.firstChoice = firstChoice;
    }
}

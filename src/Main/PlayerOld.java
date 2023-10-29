
package Main;

import Case.Case;


/**
 *
 * @author Michael, Tabitha
 */
public class PlayerOld {
    
    //player fields
    private String name;
    private double earnings;
    private Case firstChoice;
    
    /**
     * player constructor 
     * 
     */
    public PlayerOld()
    {
        this.name = "";
        this.earnings = 0;
        this.firstChoice = null;
    }

    
    /**
     * player constructor generates a new player with a blank high score
     * @param name the name of the new player
     */
    public PlayerOld(String name)
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
        return this.getName() + "'s earnings: " + this.getEarnings();
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

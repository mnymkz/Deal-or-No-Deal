
package Main;

import Case.Case;


/**
 *
 * @author Michael, Tabitha
 */
public class Player implements PlayerInterface {
    
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
    
    //player interface methods
    @Override
    public void updateCurrentEarning(double amount) {
        setEarnings(amount);
    }

    @Override
    public void setFirstChosenCase(Case firstCase) {
        this.firstChoice = firstCase;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

}

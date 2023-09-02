
package Main;

import Case.Case;


/**
 *
 * @author Michael, Tabitha
 */
public class Player implements PlayerInterface {
    
    //player fields
    private String name;
    private double currentEarnings;
    private Case firstChoice;
    
    /**
     * player constructor 
     * 
     */
    public Player()
    {
        this.name = "";
        this.currentEarnings = 0;
        this.firstChoice = null;
    }

    
    /**
     * player constructor generates a new player with a blank high score
     * @param name the name of the new player
     */
    public Player(String name)
    {
        this.name = name;
        this.currentEarnings = 0;
        this.firstChoice = null;
    }

    //player interface methods
    @Override
    public void updateCurrentEarning(double amount) {
        setCurrentEarnings(amount);
    }

    @Override
    public void setFirstChosenCase(Case firstCase) {
        this.firstChoice = firstCase;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public double getCurrentEarnings() {
        return currentEarnings;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentEarnings(double currentEarnings) {
        this.currentEarnings = currentEarnings;
    }

@Override
    public String toString()
    {
        return this.getName() + "'s earnings: " + this.getCurrentEarnings();
    }
}

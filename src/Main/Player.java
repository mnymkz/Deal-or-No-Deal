
package Main;


/**
 *
 * @author Michael
 */
public class Player implements PlayerInterface {
    
    //player fields
    private String name;
    private double currentEarnings;
    private double highestEarnings;
    
    /**
     * player constructor generates a new player with a blank high score
     * @param name the name of the new player
     */
    public Player(String name)
    {
        this.name = name;
        this.currentEarnings = 0;
        this.highestEarnings = 0;
    }

    @Override
    public boolean dealOrNoDeal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int chooseCase(String input) {
        return 0;
    }
}

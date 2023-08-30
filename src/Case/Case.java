
package Case;

/**
 * Abstract class case contains common attributes for normal and special cases
 * 
 * @author Tabitha
 */
public abstract class Case 
{
    //protected attributes 
    protected int number;
    protected Item item;
    protected boolean opened;
    
    //constructor
    public Case(int number, Item item)
    {
        this.number = number;
        this.item = item;
        this.opened = false; //closed by default
    }

    //getters and setters
    public int getNumber()
    {
        return number;
    }

    public Item getItem() {
        return item;
    }

    public boolean isOpened() {
        return opened;
    }

    public Item getObject()
    {
        return item;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    /**
     * Override toString method to return a string displaying case attributes
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return number + ": " + item.toString();
    }
    
    /**
     * openCase method sets opened to true
     */
    public void openCase()
    {
        this.opened = true;
    }
    
    /**
     * toCSV method returns the attributes of the case as a String in CSV format for 
     * file reading and file writing. Instead of using ',' case will use ':' as the
     * identifier to make loading items easier 
     * 
     * @return the attributes in CSV format 
     */
    public String toCSV()
    {
        return this.number + ":" + this.opened+ ":" + this.item.toCSV();
    }
}

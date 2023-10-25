
package Case;

import javafx.scene.control.Button;

/**
 * Abstract class case contains common attributes for normal and special cases
 * 
 * @author Michael, Tabitha
 */
public abstract class Case 
{
    //protected attributes 
    protected int number;
    protected Item item;
    protected boolean opened;
    
    //constructor
    public Case(int number, Item item, String buttonText)
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
    public String toString() {
        return String.format("%02d: %s", number, item.toString());
    }
    
    /**
     * openCase method sets opened to true
     */
    public void openCase()
    {
        this.opened = true;
    }
    
    /**
     * display case method returns a string to display to user 
     * shows the user what cases are remaining 
     * 
     * @return formatted string displaying case number and case object
     */
    public String displayCase() {
    if (!this.opened) {
        return String.format("[ %02d ]", this.number);
    } else {
        return "[ -- ]";
    }
    }

    // Method to display the money value of the case
    public void displayMoneyValue() 
    {
        if (!isOpened()) {
            Item caseItem = getItem();
            double caseValue = caseItem.getMoneyValue();
                
            System.out.println("$" + caseValue);       
        }
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

    public interface CaseListner
    {
        void onCaseClicked(String caseNumber);
    }

    private CaseListner caseListner;

    public void setCaseListener(CaseListner caseListner)
    {
        this.caseListner = caseListner;
    }

    protected void handleCaseClick(String caseNumber)
    {
        if(caseListner != null)
        {
            caseListner.onCaseClicked(caseNumber);
        }
    }
}

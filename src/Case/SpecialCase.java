
package Case;

/**
 * Special cases have a 10% of being generated. Special cases will have a random modifier
 * to the price of the item inside.
 * 
 * @author Michael
 */
public class SpecialCase extends Case {

    //custom special case fields
    double multiplier;

    //constructor
    public SpecialCase(int number, Item item, double multiplier) {
        super(number, item);
        this.multiplier = multiplier;
    }

    //Override methods 
    @Override
    public String toString()
    {
        return super.toString() + "[MULTIPLIER: " +this.multiplier+"]";
    }
    
    @Override
    public String toCSV()
    {
        return super.toCSV()+","+this.multiplier;
    }
    
    /**
     * updatePrice method updates the money value of an item inside the case with the multiplier
     */
    public void updatePrice()
    {
        this.item.setMoneyValue(multiplier*this.item.getMoneyValue());
    }
}

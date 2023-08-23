
package Case;

/**
 * Item class contains attributes for items found in briefcases 
 * 
 * @author Michael
 */
public class Item {

    //item fields 
    private String name;
    private double moneyValue;
    private String description;

    //item constructor (empty description)
    public Item(String name, double moneyValue) {
        this.name = name;
        this.moneyValue = moneyValue;
        this.description = "";
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getMoneyValue() {
        return moneyValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMoneyValue(double moneyValue) {
        this.moneyValue = moneyValue;
    }
    
    //Item toString 
    @Override 
    public String toString()
    {
        return this.name + ", $" + this.moneyValue + " - " + this.description;
    }
    
}

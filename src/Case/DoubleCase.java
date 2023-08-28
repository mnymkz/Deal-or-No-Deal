
package Case;

/**
 * Double Cases store two items - therefore double the value
 * Cases can get promoted to double/empty cases in a double or nothing round
 * 
 * @author Michael
 */
public class DoubleCase extends Case {

    private Item item2;
    
    public DoubleCase(int number, Item item) {
        super(number, item);
        this.item = item;
    }
    
}


package Case;

/**
 * Normal cases contain the standard case behavior. They can be opened and contain 
 * an item and are numbered. 
 * 
 * @author Michael
 */
public class NormalCase extends Case {

    public NormalCase(int number, Item item) {
        super(number, item);
    }

    @Override
    public void openCase() {
        this.opened = true;
    }
    
}

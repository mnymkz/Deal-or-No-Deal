
package Case;

/**
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

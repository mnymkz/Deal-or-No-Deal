
package Case;

/**
 *
 * @author Michael
 */
public class SpecialCase extends Case {

    //custom special case fields
    double multiplier;

    public SpecialCase(double multiplier, int number, Item item) {
        super(number, item);
        this.multiplier = multiplier;
    }

    //Special behaviour to be decided 
    @Override
    public void openCase() {
        this.opened = true;
    }
}

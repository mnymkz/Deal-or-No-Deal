
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

    public SpecialCase(double multiplier, int number, Item item) {
        super(number, item);
        this.multiplier = multiplier;
    }
}

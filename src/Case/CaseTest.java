
package Case;

/**
 * Testing case behaviors 
 * 
 * @author Michael
 */
public class CaseTest {
    
    public static void main(String[] args) {
        //items
        Item a = new Item("Garbage", 0.50);
        Item b = new Item("Gold", 1000);
        a.setDescription("This item is worth nothing");
        b.setDescription("This item is worth a lot");
        
        //Case polymorphism
        Case normalCase = new NormalCase(1, a);
        Case specialCase = new SpecialCase(1.5, 2, b);
        Case doubleCase = new DoubleCase(3, b);
        System.out.println(normalCase.toString());
        System.out.println(specialCase.toString());
        System.out.println("");
        
        //open and closing cases
        normalCase.openCase();
        specialCase.openCase();
        System.out.println(normalCase.isOpened());
        System.out.println(specialCase.isOpened());
    }
}

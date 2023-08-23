
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
        Case normal = new NormalCase(1, a);
        Case special = new SpecialCase(1.5, 2, b);
        System.out.println(normal.toString());
        System.out.println(special.toString());
        
        //open and closing cases
        normal.openCase();
        special.openCase();
        System.out.println(normal.isOpened());
        System.out.println(special.isOpened());
    }
}

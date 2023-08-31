
package Case;

import FileIO.CaseManager;

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
        Item c = new Item("Silver", 500);
        a.setDescription("This item is worth nothing");
        b.setDescription("This item is worth a lot");
        c.setDescription("This item is worth a bit");
        
        //Case polymorphism
        Case normalCase = new NormalCase(1, a);
        Case specialCase = new SpecialCase(2, b, 1.5);
        Case doubleCase = new DoubleCase(3, b);
        Case emptyCase = new EmptyCase(4);
        System.out.println(normalCase.toString());
        System.out.println(specialCase.toString());
        System.out.println(doubleCase.toString());
        System.out.println(emptyCase.toString());
        
        //open and closing cases
        normalCase.openCase();
        specialCase.openCase();
        doubleCase.openCase();
        emptyCase.openCase();
        System.out.println(normalCase.isOpened());
        System.out.println(specialCase.isOpened());
        System.out.println(doubleCase.isOpened());
        System.out.println(emptyCase.isOpened());

        //testing csv 
        System.out.println(a.toCSV());
        System.out.println(b.toCSV());
        System.out.println(c.toCSV());
        System.out.println(normalCase.toCSV());
        System.out.println(specialCase.toCSV());
        System.out.println(doubleCase.toCSV());
        System.out.println(emptyCase.toCSV());

        //case generation and item loading 
        CaseManager cm = new CaseManager();
        cm.load();
        for (Case C: cm.getCases())
        {
            System.out.println(C.toString());
        }
        
        for (Case C: cm.getCases())
        {
            System.out.println(C.displayCase());
        }
    }
}

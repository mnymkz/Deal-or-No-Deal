
package FileIO;

import Case.Case;
import Case.Item;
import Case.NormalCase;
import Case.SpecialCase;
import static FileIO.FileIO.readFromFile;
import java.util.ArrayList;
import java.util.HashSet;
import FileIO.ObjectLoader;
import java.util.Iterator;
import java.util.Random;

/**
 * CaseManager class implements object loader
 * used to manage cases in the game loop + fileIO operations for cases
 * 
 * @author Michael, Tabitha
 */
public class CaseManager implements ObjectLoader {

    //methods for loading items
    //methods for generating cases 
    //arrayList to store 26 cases
    private ArrayList<Case> cases;
    private HashSet<Item> items;
    private final String FILE_PATH = "resources/items.txt";
    private final int NUM_CASES = 26;

    //constructor
    public CaseManager() {
        this.cases = new ArrayList<>();
        this.items = new HashSet();
    }
    
    /**
     * load Items loads items from text file into hashSet
     */
    private void loadItems() {
        String data = readFromFile(FILE_PATH);
        
        String[] items = data.split("\n");
        for (String item: items)
        {
            createItem(item);
        }
    }
    
    /**
     * createItem method creates item objects from data read in from file 
     * and adds to hashSet of items
     * @param itemAttributes item name, value and description in CSV format
     */
    private void createItem(String itemAttributes)
    {
        String[] attributes = itemAttributes.split(",");
        String itemName = attributes[0];
        Double itemValue = Double.parseDouble(attributes[1]);
        String itemDesc = attributes[2];
        Item item  = new Item(itemName, itemValue, itemDesc);
        this.items.add(item);
    }
    
    /**
     * generateCases method generates 26 cases
     */
    private void generateCases()
    {
        //create 26 cases
        for (int i = 0; i < NUM_CASES; i++)
        {
            int caseNo = i+1;
            //create a case
            int randomCase = generateRandomCase();
            //generate a random item
            Item item = getItem();
            
            switch (randomCase)
            {
                case 0:
                    Case normalCase = new NormalCase(caseNo, item);
                    this.cases.add(normalCase);
                    break;
                case 1:
                    double multiplier = generateRandomMultiplier();
                    Case specialCase = new SpecialCase(caseNo, item, multiplier);
                    this.cases.add(specialCase);
                    break;
                case -1:
                    System.out.println("Error generating case.");
                    break;
                default:
                    System.out.println("Error generating case.");
                    break;                    
            }
        }
    }
    
    /**
     * generateRandomCase rolls a random number generator to choose what type of case
     * is generated. Normal = 85% chance, special/double/empty = 5% chance
     * 
     * @return 0 for normal, 1 for special
     */
    private int generateRandomCase() {
        double randomValue = Math.random();
        //5% probability of a case to be special
        if (randomValue >= 0.90) {
            return 1;
        }  //90% probability of a case to be normal
        else {
            return 0;
        }
    }
    
    /**
     * getItem method gets an item from the item set. Once a random item is chosen, getItem
     * removes the item from the set
     * 
     * @return an Item to put into a case
     */
    private Item getItem()
    {
        //check if set is empty
        if (items.isEmpty())
        {
            throw new IllegalArgumentException("No items in set.");
        }
        
        //get a random index 
        int randomIndex = new Random().nextInt(items.size());
        Iterator<Item> it = items.iterator();
        //declare a random item to return
        Item randomItem = null;
        
        //loop through set while i is less than randomIndex and while iterator has next
        for (int i = 0; i <= randomIndex && it.hasNext(); i++)
        {
            randomItem = it.next();
        }
        
        //if not null
        if (randomItem != null)
        {
            //remove the item from the set
            it.remove();
            //return the item
            return randomItem;
        } else //error - there should not be a null item in the set
        {
            throw new IllegalStateException("Unable to choose random item.");
        }
    }
    
    /**
     * generateRandomMultiplier generates a random between 0-2
     * 
     * @return a random multiplier
     */
    private double generateRandomMultiplier() {
        Random random = new Random();
        return random.nextDouble() * 2.0;
    }

    /**
     * getLastUnopenedCase gets the last remaining briefcase by iteration
     * 
     * @return a random multiplier
     */
    public Case getLastUnopenedCase() {
        //iterate through the cases list in reverse order to get the last unopened case
        for (int i = cases.size() - 1; i >= 0; i--) 
        {
            Case currentCase = cases.get(i);
            if (!currentCase.isOpened()) 
            {
                return currentCase;
            }
        }
        //if all cases are opened, return null or throw an exception
        return null;
    }
    
    //Override ObjectLoader interface methods
    @Override
    public void load() {
        loadItems();
        generateCases();
    }

    //unused save method
    @Override
    public void save() {
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //get methods
    public ArrayList<Case> getCases() {
        return cases;
    }

    public HashSet<Item> getItems() {
        return items;
    }

    public int getNUM_CASES() {
        return NUM_CASES;
    }
}

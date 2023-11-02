
package Case;

import Database.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class ItemGenerator {

    private final HashSet<Item> items;
    private final DBManager dbManager;
    private static final String SELECT_ITEMS = "SELECT itemName, itemValue, itemDesc FROM ITEM";

    public ItemGenerator(DBManager dbManager) {
        this.items = new HashSet();
        this.dbManager = dbManager;
    }
    
    public void loadItems() throws SQLException {
        //iterate through result set
        ResultSet rs = dbManager.queryItems(SELECT_ITEMS);
        
        while (rs.next()) {
            String name = rs.getString("itemName"); //get item fields
            Double moneyValue = rs.getDouble("itemValue");
            String desc = rs.getString("itemDesc");

            Item item = new Item(name, moneyValue, desc); //create item
            this.items.add(item); //add item
        }
    }

    public Item getRandomItem()
    {
        if (items.isEmpty()) { //check if set is empty
            throw new IllegalArgumentException("No items in set.");
        }

        int randomIndex = new Random().nextInt(items.size()); //get a random index
        Iterator<Item> it = items.iterator();
        Item randomItem = null; //create instance of item to return
        
        //loop through set while i is less than randomIndex and while iterator has next
        for (int i = 0; i <= randomIndex && it.hasNext(); i++)
        {
            randomItem = it.next();
        }
        
        //if not null
        if (randomItem != null) {
            it.remove(); //remove the item from the set
            return randomItem; //return the item
        } else {
            //error - there should not be a null item in the set
            throw new IllegalStateException("Unable to choose random item.");
        }
    }

    //get method
    public HashSet<Item> getItems() {
        return items;
    }
}

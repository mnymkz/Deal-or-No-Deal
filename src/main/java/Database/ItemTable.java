package Database;

import static Database.SQLStatements.CREATE_ITEMS_TABLE;


/**
 * Item table class used to load items table extends tableManager superclass
 *
 * @author Michael
 */
public class ItemTable extends tableManager {
    
    private final String TABLE_NAME = "ITEMS";
    
    /**
     * create new table 
     */
    @Override
    public void initTable() {
        createTable(TABLE_NAME, CREATE_ITEMS_TABLE);
        //add item rows here
    }
}

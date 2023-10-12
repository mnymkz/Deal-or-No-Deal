
package Database;

import static Database.SQLStatements.CREATE_PLAYERS_TABLE;

/**
 * Player table class used to load items table extends tableManager superclass
 * 
 * @author Michael
 */
public class PlayerTable extends tableManager {

    private final String TABLE_NAME = "PLAYERS";
    
    @Override
    public void initTable() {
        createTable(TABLE_NAME, CREATE_PLAYERS_TABLE);
        //no need to add rows, user will do that through the model
    }

}

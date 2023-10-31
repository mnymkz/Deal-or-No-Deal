
package Main;

import Controller.Controller;
import Database.DBManager;
import Database.GameDB;
import View.View;
import java.sql.SQLException;

/**
 *
 * @author Michael
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        DBManager dBManager = DBManager.getInstance();
        dBManager.establishConnection();
        GameDB gameDB = new GameDB(dBManager);
        gameDB.initTables();
        
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setVisible(true);
    }
}

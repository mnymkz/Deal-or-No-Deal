
package Main;

import Model.Model;
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
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);
        view.setVisible(true);
    }
}

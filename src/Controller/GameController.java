
package Controller;

import View.GamePanel;
import Model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Tabitha
 */
public class GameController implements ActionListener{
    private GamePanel view;
    private Model model;

    public GameController(GamePanel view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addCaseButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int caseNumber = Integer.parseInt(command);
        
        model.geCurrentGame().removeCase(caseNumber); // method to remove a case in your Game class
        view.removeBriefcase(caseNumber);
    }
}

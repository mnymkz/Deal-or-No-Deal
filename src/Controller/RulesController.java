
package Controller;

import View.RulesPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class RulesController {

    private RulesPanel rulesPanel;
    private View mainFrame;

    public RulesController(RulesPanel rulesPanel, View mainFrame) {
        this.rulesPanel = rulesPanel;
        this.mainFrame = mainFrame;
        this.rulesPanel.addReturnButtonActionListener(new ReturnButtonListener());
    }
    
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Clicked, returning");
            mainFrame.switchPanel("HomePanel");
        }
    }
    
    
}

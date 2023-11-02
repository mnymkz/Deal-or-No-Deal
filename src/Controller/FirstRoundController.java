package Controller;

import View.FirstRoundPanel;
import Model.Model;
import Model.Game;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author Tabitha
 */
public class FirstRoundController {
    
    private FirstRoundPanel firstRoundPanel;
    private View mainFrame;
    private Model model; 
    
    
    public FirstRoundController(FirstRoundPanel firstRoundPanel, View mainFrame, Model model) {
        this.mainFrame = mainFrame;
        this.firstRoundPanel = firstRoundPanel;
        this.model = model;
        this.firstRoundPanel.addCaseButtonListener(new CaseButtonListener());
    }

    /**
     * Action listener for the firstRound
     */
    private class CaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked");
            String command = e.getActionCommand(); //get string from button 
            int caseNumber = Integer.parseInt(command); //get caseNumber

            firstRoundPanel.removeBriefcase(caseNumber);
            //set user first case
            firstRoundPanel.updateStatusLabel("You've chosen case " + caseNumber + " as your first case!");
            System.out.println("You have selected your first case, switching to bankerPanel");
            mainFrame.switchPanel("BankerPanel");
        }
    }
}
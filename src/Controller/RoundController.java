package Controller;

import View.RoundPanel;
import Model.Model;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This controller processes user input from the RoundPanel.
 */
public class RoundController {

    private RoundPanel roundPanel;
    private View mainFrame;
    private Model model;
    private int numChoices;
    private int choicesMade;

    public RoundController(RoundPanel roundPanel, View mainFrame, Model model) {
        this.mainFrame = mainFrame;
        this.roundPanel = roundPanel;
        this.model = model;
        this.roundPanel.addCaseButtonListener(new CaseButtonListener());
        this.numChoices = 6;
        this.choicesMade = 0;
    }
    
    private class CaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int caseNumber = Integer.parseInt(e.getActionCommand());
            roundPanel.removeBriefcase(caseNumber); //remove case from the view 
            model.getGame().removeCase(caseNumber); //remove case from the model
            choicesMade++;
            if (choicesMade < numChoices) {
                roundPanel.updateStatusLabel(numChoices - choicesMade); //display choices remaining 
            }
            else {
                if (choicesMade == numChoices) {
                    resetRound();
                }
                roundPanel.updateStatusLabel(numChoices-choicesMade);
                System.out.println("All cases selected for this round, proceeding to next step.");
                try {
                    int currentRound = model.getGameManager().getCurrentRound(model.getCurrentPlayer().getUsername());
                    model.getGameManager().updateCurrentRound(model.getCurrentPlayer().getUsername(), currentRound+1);
                    if (currentRound == 10) {
                        mainFrame.switchPanel("GameOverPanel");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RoundController.class.getName()).log(Level.SEVERE, null, ex);
                }
                mainFrame.switchPanel("BankerPanel");
            }
        }
    }
    
    
    private void resetRound() {
        this.choicesMade = 0;
        this.numChoices--;
    }
}

package Controller;

import View.GamePanel;
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
public class GameController {
    
    private View mainFrame;
    private GamePanel gamePanel;
    private Model model; 
    private Game game;
    private String username;

    public GameController(GamePanel view, Model model) {
        this.gamePanel = view;
        this.model = model;
        this.game = model.getGame();
        this.username = model.getCurrentPlayer().getUsername();
        this.gamePanel.addCaseButtonListener(new CaseButtonListener());
    }

    /**
     * Action listener for game 
     */
    private class CaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked");
            String command = e.getActionCommand(); //get string from button 
            int caseNumber = Integer.parseInt(command); //get caseNumber
            int currentRound = 0; //initialise current round 

            try {
                currentRound = game.getGameManager().getCurrentRound(username); //get current round
                int numCasesToOpen = 6 - currentRound;  //get numChoices

                if (numCasesToOpen <= 0) {
                    //no more cases to choose from
                    // End the round and prepare for the next one
                    game.endRound();
                    gamePanel.updateStatusLabel("Round " + (currentRound + 1) + " Start!");
                }
            } catch(SQLException ex) {
                System.out.println("Error updating round: " + ex.getMessage());
            }
        }
    }
}
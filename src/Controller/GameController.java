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
    
    private GamePanel gamePanel;
    private View mainFrame;
    private Model model; 
    private Game game;
    private String username;
    
    
    public GameController(GamePanel gamePanel, View mainFrame, Model model) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.model = model;
        this.game = model.getGame();
        this.username = model.getCurrentPlayer().getUsername();
        this.gamePanel.addCaseButtonListener(new CaseButtonListener());
    }

//    /**
//     * Action listener for game 
//     */
//    private class CaseButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Button clicked");
//            String command = e.getActionCommand(); //get string from button 
//            int caseNumber = Integer.parseInt(command); //get caseNumber
//            int currentRound = 0; //initialise current round 
//
//            try {
//                currentRound = model.getGame().getGameManager().getCurrentRound(model.getCurrentPlayer().getUsername()); //get current round
//                int numCasesToOpen = 6 - currentRound;  //get numChoices
//
//                if (numCasesToOpen <= 0) {
//                    //no more cases to choose from
//                    // End the round and prepare for the next one
//                    model.getGame().endRound();
//                    gamePanel.updateStatusLabel("Round " + (currentRound + 1) + " Start!");
//                }
//            } catch(SQLException ex) {
//                System.out.println("Error updating round: " + ex.getMessage());
//            }
//        }
//    }
    /**
     * Action listener for normal rounds 
     */
    private class CaseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked");
            String command = e.getActionCommand(); //get string from button 
            int caseNumber = Integer.parseInt(command); //get caseNumber

            if (gamePanel.getIsFirstRound()) {
            gamePanel.setIsFirstRound(false);
            gamePanel.removeBriefcase(caseNumber);
            gamePanel.updateStatusLabel("You've chosen case " + caseNumber + " as your first case!");
            // Here you can also perform any other game logic for the first round
            return; // Exit since the first round logic is done
            }
            
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
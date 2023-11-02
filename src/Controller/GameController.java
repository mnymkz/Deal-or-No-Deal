
package Controller;

import View.GamePanel;
import Model.Model;
import Model.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *
 * @author Tabitha
 */
public class GameController implements ActionListener{
    private GamePanel view;
    private Model model; 
    private Game game;
    private String username;

    public GameController(GamePanel view, Model model) {
        this.view = view;
        this.game = game;
        this.model = model;
        this.username = game.getUser().getUsername();
        this.view.addCaseButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int caseNumber = Integer.parseInt(command);
        
        try {
            int currentRound = game.getGameManager().getCurrentRound(username);
            int numCasesToOpen = 6 - currentRound; 
            
            if (numCasesToOpen <= 0) {
                // End the round and prepare for the next one
                game.endRound();
                view.updateStatusLabel("Round " + (currentRound + 1) + " Start!");
            }
        } catch(SQLException ex) {
            System.out.println("Error updating round: " + ex.getMessage());
        }     
    }
}

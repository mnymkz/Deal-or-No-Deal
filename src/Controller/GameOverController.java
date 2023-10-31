
package Controller;

import View.GameOverPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class GameOverController {
    
    private GameOverPanel gameOverPanel;
    private View mainFrame;

    public GameOverController(GameOverPanel gameOverPanel, View mainFrame) {
        this.gameOverPanel = gameOverPanel;
        this.mainFrame = mainFrame;
        this.gameOverPanel.addExitGameButtonActionListener(new ExitGameButtonActionListener());
        this.gameOverPanel.addPlayAgainButtonActionListener(new PlayAgainButtonActionListener());
    }
    
    private class ExitGameButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Exit button clicked, exiting game");
            //TODO handle event 
            //save current earnigns 
            //disconnect db
            //close frame
            mainFrame.dispose();
        }
    }

    /**
     * no deal button action listener
     */
    private static class PlayAgainButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Play again button clicked, creating new game");
            //TODO create new game 
        }
    }
    
}

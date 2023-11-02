
package Controller;

import Model.Model;
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
    private Model model;

    public GameOverController(GameOverPanel gameOverPanel, View mainFrame, Model model) {
        this.gameOverPanel = gameOverPanel;
        this.mainFrame = mainFrame;
        this.model = model;
        this.gameOverPanel.addExitGameButtonActionListener(new ExitGameButtonActionListener());
        this.gameOverPanel.addPlayAgainButtonActionListener(new PlayAgainButtonActionListener());
    }
    /**
     * Exit game button action listener
     */
    private class ExitGameButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Exit button clicked, exiting game");
            model.getdBManager().closeConnections();
            mainFrame.dispose(); //close frame 
        }
    }

    /**
     * no deal button action listener
     */
    private class PlayAgainButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Play again button clicked, creating new game");
            mainFrame.switchPanel("HomePanel");
        }
    }
    
}

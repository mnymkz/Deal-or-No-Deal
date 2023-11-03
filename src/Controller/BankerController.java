package Controller;

import Login.Player;
import Model.Model;
import View.BankerPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class BankerController {

    private BankerPanel bankerPanel;
    private View mainFrame;
    private Model model;

    //constructor
    public BankerController(BankerPanel bankerPanel, View mainFrame, Model model) {
        this.mainFrame = mainFrame;
        this.bankerPanel = bankerPanel;
        this.model = model;
        this.bankerPanel.addDealButtonActionListener(new DealButtonActionListener());
        this.bankerPanel.addNoDealButtonActionListener(new NoDealActionListener());
        this.bankerPanel.setBankerOffer(model.getBankerOffer()); //set banker offer text
    }

    public void fetchBankerOffer() {
        model.getBankerOffer();
        bankerPanel.setBankerOffer(model.getBankerOffer());
    }
    
    /**
     * deal button action listener
     */
    private class DealButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Deal Button Clicked, accepting offer");
            //set the earnings as highestEarnings 
            double offer = model.getBankerOffer(); //model.getOffer
            Player player = model.getCurrentPlayer(); //get player
            try {
                System.out.println("updating earnings...");
                model.getLoginManager().updateHighestEarnings(player, offer); //update earnings 
                mainFrame.switchPanel("GameOverPanel"); //take user to game over screen
            } catch (SQLException ex) {
                Logger.getLogger(BankerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * no deal button action listener
     */
    private class NoDealActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("No Deal Button Clicked, rejecting offer");
            //TODO handle event 
            mainFrame.switchPanel("RoundPanel");
         }
    }
}

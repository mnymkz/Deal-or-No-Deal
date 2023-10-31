package Controller;

import Model.Model;
import View.BankerPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    /**
     * deal button action listener
     */
    private class DealButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Deal Button Clicked, accepting offer");
            //TODO handle event 
            //update user final earnings
            //take user to end game screen
            //end of game 
        }
    }

    /**
     * no deal button action listener
     */
    private static class NoDealActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("No Deal Button Clicked, rejecting offer");
            //TODO handle event 
            //continue onto new round 
            //user earnings stay the same
        }
    }
}

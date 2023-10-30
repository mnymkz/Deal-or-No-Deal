
package Controller;

import View.LoginPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class LoginController {

    private LoginPanel loginPanel;
    private View mainFrame;

    /**
     * LoginController constructor
     * 
     * @param homePanel the home panel
     * @param mainFrame the View JFrame
     */
    public LoginController(LoginPanel homePanel, View mainFrame) {
        this.loginPanel = homePanel;
        this.mainFrame = mainFrame;
        this.loginPanel.addLoginButtonActionListener(new LoginButtonListener());
        this.loginPanel.addReturnButtonActionListener(new ReturnButtonListener());
    }

    /**
     * loginButton action listener
     */
    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login Button Clicked, authenticating");
            //TODO authenticate user
        }
    }
    
    /**
     * return button action listener
     */
    private class ReturnButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Clicked, returning");
            //TODO call create user 
            mainFrame.switchPanel("HomePanel");
        }
    }
}

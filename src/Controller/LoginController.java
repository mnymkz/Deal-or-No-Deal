
package Controller;

import Login.Player;
import Model.Model;
import View.LoginPanel;
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
public class LoginController {

    private LoginPanel loginPanel;
    private View mainFrame;
    private Model model;

    /**
     * LoginController constructor
     * 
     * @param homePanel the home panel
     * @param mainFrame the View JFrame
     */
    public LoginController(LoginPanel homePanel, View mainFrame, Model model) {
        this.loginPanel = homePanel;
        this.mainFrame = mainFrame;
        this.model = model;
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
            String username = loginPanel.getUsername();
            char[] password = loginPanel.getPassword();
            
            try {
                //check if user exists
                if (!model.getLoginManager().playerExists(username)) {
                    System.out.println("Player does not exist");
                    loginPanel.setErrorMessage(username + " does not exist.");
                } else {
                    try { //authenticate user details
                        //if successful 
                        if (model.getLoginManager().Auth(username, new String(password))) {
                            //get player from db 
                            Player currentPlayer = model.getLoginManager().getPlayer(username);
                            model.setCurrentPlayer(currentPlayer);
                            System.out.println("Login successful");
                            mainFrame.switchPanel("GamePanel"); 
                        } else {
                            System.out.println("Error - wrong details. Double check.");
                            loginPanel.setErrorMessage("Error loging in. Double check username and password.");
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error authenticating: " + ex.getMessage()); //error calling auth method 
                        loginPanel.setErrorMessage("Error authenticating details.");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * return button action listener
     */
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Clicked, returning");
            mainFrame.switchPanel("HomePanel");
        }
    }
}

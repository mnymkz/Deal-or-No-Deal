
package Controller;

import Login.Player;
import Model.Model;
import View.SignUpPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Michael
 */
public class SignUpController {
   
    private SignUpPanel signUpPanel;
    private View mainFrame;
    private Model model;

    public SignUpController(SignUpPanel signUpPanel, View mainFrame, Model model) {
        this.signUpPanel = signUpPanel;
        this.mainFrame = mainFrame;
        this.model = model;
        this.signUpPanel.addSignUpButtonActionListener(new SignUpButtonListener());
        this.signUpPanel.addReturnButtonActionListener(new ReturnButtonListener());
    }
    
    /**
     * handles sign up button click
     */
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Sign Up Button Clicked, creating user");
            //get player params
            String username = signUpPanel.getUsername();
            char[] password = signUpPanel.getPassword();
            char[] passwordConfirm = signUpPanel.getPasswordConfirmation();
            System.out.println(username + " " + password);
            
            if (Arrays.equals(password, passwordConfirm)) {
                try {
                    //if username exists 
                    if (model.getLoginManager().playerExists(new Player(username, new String(password)))) {
                        try {
                            model.createPlayer(username, new String(password)); //create new player in model
                            System.out.println("Player successfully created"); 
                            mainFrame.switchPanel("GamePanel"); //switch to game panel
                        } catch (SQLException ex) {
                            System.out.println("Error creating new user: " + ex.getMessage());
                            signUpPanel.setErrorMessage("Error creating new user");
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error checking if player exists");
                    signUpPanel.setErrorMessage("Error checking if user exists");
                }
            } else {
                 System.out.println("Passwords do not match.");
                 signUpPanel.setErrorMessage("Passwords do not match.");
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
            mainFrame.switchPanel("HomePanel"); //return to home panel
        }
    }
    
}

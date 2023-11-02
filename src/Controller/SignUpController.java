
package Controller;

import Model.Model;
import View.SignUpPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            try {
                //if player exists
                if (model.getLoginManager().playerExists(username)) { 
                    System.out.println("Login already exists");
                    signUpPanel.setErrorMessage("Login already exists"); //display error message to user
                } 
                
                //if player does not exist
                if (!model.getLoginManager().playerExists(username)) { 
                    //check if passwords match 
                    if (Arrays.equals(password, passwordConfirm)) {
                        System.out.println("Passwords match");
                        //create new player in model
                        try {
                            model.createPlayer(username, new String(password));
                            mainFrame.switchPanel("GamePanel"); 
                        } catch (SQLException ex) {
                            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        //display error to user
                        System.out.println("Passwords do not match");
                        signUpPanel.setErrorMessage("Passwords do not match.");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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

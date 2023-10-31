
package Controller;

import Model.Model;
import View.SignUpPanel;
import View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Sign Up Button Clicked, creating user");
            //TODO call create user 
        }
    }
    
    private class ReturnButtonListener implements ActionListener {
    @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Return Button Clicked, returning");
            //TODO call create user 
            mainFrame.switchPanel("HomePanel");
        }
    }
    
}

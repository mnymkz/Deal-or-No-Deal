package Controller;

import Model.Model;
import View.HomePanel;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Home controller handles actions for sign in and sign up button clicks
 * 
 * @author Michael
 */
public class HomeController {

    //gui instances
    private HomePanel homePanel;
    private View mainFrame;
    private Model model;

    //constructor
    public HomeController(HomePanel homePanel, View mainFrame, Model model) {
        this.homePanel = homePanel;
        this.mainFrame = mainFrame;
        this.model = model;
        this.homePanel.addSignInButtonActionListener(new SignInButtonListener()); //add action listner
        this.homePanel.addSignUpButtonActionListener(new SignUpButtonListener()); //add action listener 
    }

    /**
     * ActionListener for sign in button
     */
    private class SignInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //when clicked, display sign in panel
            mainFrame.switchPanel("LoginPanel");
            System.out.println("Sign In Button Clicked, taking user to sign in page");
        }
    }

    /**
     * ActionListener for sign up button
     */
    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //when clicked, display sign up panel
            mainFrame.switchPanel("SignUpPanel");
            System.out.println("Sign Up Button Clicked, taking user to sign up page");
        }
    }
}

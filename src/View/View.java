package View;

import Controller.Controller;
import Controller.LoginController;
import Controller.SignUpController;
import java.awt.*;
import javax.swing.*;

public class View extends JFrame {

    //panel instances
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HomePanel homePanel;
    private LoginPanel loginPanel; 
    private SignUpPanel signUpPanel;

    //JFrame constructor 
    public View() {
        initViewFrame();
        addPanels();
    }

    /**
     * init JFrame
     */
    private void initViewFrame() {
        this.setTitle("Deal or No Deal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
    }
    
    /**
     * adds Panels to the frame 
     */
    private void addPanels() {
        cardLayout = new CardLayout(); //card layout
        mainPanel = new JPanel(cardLayout); //new mainPanel parent container

        //init Panels 
        homePanel = new HomePanel(); 
        loginPanel = new LoginPanel(); 
        signUpPanel = new SignUpPanel();

        //add panels to parent container
        mainPanel.add(homePanel, "HomePanel");
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(signUpPanel, "SignUpPanel");

        //add parent container to the frame
        this.add(mainPanel);
    }
    
    /**
     * switchPanel switches between panels
     * 
     * @param panelName the panel to display
     */
    public void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    //getters
    public HomePanel getHomePanel() {
        return homePanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public SignUpPanel getSignUpPanel() {
        return signUpPanel;
    }
    
}

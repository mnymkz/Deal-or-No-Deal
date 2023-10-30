package View;

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
        // Instantiate HomeController and associate it with the HomePanel
        Controller.HomeController homeController = new Controller.HomeController(homePanel, this);
        Controller.LoginController loginController = new LoginController(loginPanel, this);
        Controller.SignUpController signUpController = new SignUpController(signUpPanel, this);
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

    //entry 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }
}

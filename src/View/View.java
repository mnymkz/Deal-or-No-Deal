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
    private BankerPanel bankerPanel;
    private GameOverPanel gameOverPanel;
    
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

        initPanels(); //init panel components
        addViewPanels(); //add panel components to mainPanel
        
        this.add(mainPanel); //add mainPanel to the JFrame
    }
    
    private void initPanels() {
        this.homePanel = new HomePanel();
        this.loginPanel = new LoginPanel();
        this.signUpPanel = new SignUpPanel();
        this.bankerPanel = new BankerPanel();
        this.gameOverPanel = new GameOverPanel();
    }
    
    private void addViewPanels() {
//        mainPanel.add(homePanel, "HomePanel");
//        mainPanel.add(loginPanel, "LoginPanel");
//        mainPanel.add(signUpPanel, "SignUpPanel");
//        mainPanel.add(bankerPanel, "BankerPanel");
        mainPanel.add(gameOverPanel, "GameOverPanel");
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

    public BankerPanel getBankerPanel() {
        return bankerPanel;
    }

    public GameOverPanel getGameOverPanel() {
        return gameOverPanel;
    }
}

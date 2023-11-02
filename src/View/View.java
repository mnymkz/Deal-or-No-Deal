package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private GamePanel gamePanel;
    private RulesPanel rulesPanel;
    
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
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        //add window listener to implement on close operations
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                handleExit();
            }
        });        
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
        this.gamePanel = new GamePanel();
        this.gameOverPanel = new GameOverPanel();
        this.rulesPanel = new RulesPanel();
    }
    
    private void addViewPanels() {
        mainPanel.add(homePanel, "HomePanel");
        mainPanel.add(loginPanel, "LoginPanel");
        mainPanel.add(signUpPanel, "SignUpPanel");
        mainPanel.add(rulesPanel, "RulesPanel");
        mainPanel.add(gamePanel, "GamePanel");
        mainPanel.add(bankerPanel, "BankerPanel");
        mainPanel.add(gameOverPanel, "GameOverPanel");
    }
    
    /**
     * handleExit handles exit window behavior
     */
    public void handleExit() {
        System.out.println("Exit button clicked, exiting game");
        // TODO: handle event
        // save current earnings
        // disconnect db
        this.dispose();
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public RulesPanel getRulesPanel() {
        return rulesPanel;
    }
    
}

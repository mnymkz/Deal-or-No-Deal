package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*; 

/**
 *
 * @author Michael
 */
public class HomePanel extends JPanel implements viewInterface {

    private JButton signInButton;
    private JButton signUpButton;
    private JButton rulesButton;
    private JLabel welcomeLabel;

    public HomePanel() {
        //init components
        initComponents();
        setLayout();
    }

    /**
     * init components
     */
    @Override
    public void initComponents() {
        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Roboto", Font.BOLD, 20));
        signUpButton = new JButton("Sign up");
        signUpButton.setFont(new Font("Roboto", Font.BOLD, 20));
        rulesButton = new JButton("Rules"); 
        rulesButton.setFont(new Font("Roboto", Font.BOLD, 20)); 
        welcomeLabel = new JLabel("Welcome to Deal or No Deal");
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 24)); // Applying custom font
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // Center aligning the text

    }
    
    //GPT assisted layout 
    @Override
    public void setLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Making the label span two columns
        gbc.insets = new Insets(5, 5, 15, 5); // Padding
        gbc.anchor = GridBagConstraints.CENTER; // Making the components centered
        add(welcomeLabel, gbc); // Adding the welcome label to the panel

        gbc.gridy = 1;
        gbc.gridwidth = 1; // Each button in one column
        gbc.gridx = 0;
        add(signInButton, gbc); // Adding the sign-in button

        gbc.gridx = 1; // Moving to the next column
        gbc.anchor = GridBagConstraints.EAST; // Right aligning the sign-up button
        add(signUpButton, gbc); // Adding the sign-up button
        
        gbc.gridy++; // Move to the next row
        gbc.gridx = 0; // Reset to the first column
        gbc.gridwidth = 2; // Span the button across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make the button stretch to fill the space
        add(rulesButton, gbc); // Add the rules button to the panel
    }
    
    //actionListeners for the buttons
    public void addSignInButtonActionListener(ActionListener listener) {
        signInButton.addActionListener(listener);
    }

    public void addSignUpButtonActionListener(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }
    
    public void addRulesButtonActionListener(ActionListener listener) {
        rulesButton.addActionListener(listener);
    }
}

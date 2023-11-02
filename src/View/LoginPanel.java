
package View;

import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Michael, Tabitha
 */
public class LoginPanel extends JPanel implements viewInterface {
    private JLabel signUpLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel; 
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton returnButton;
    
    public LoginPanel() {
        //init components
        initComponents();
        setLayout();
    }

    /**
     * init components
     */
    @Override
    public void initComponents() {
        signUpLabel = new JLabel("Log in to Play Deal or No Deal");
        signUpLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 20));

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Roboto", Font.BOLD, 20));
        
        errorLabel = new JLabel();
        errorLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        errorLabel.setForeground(Color.RED);
    }

    //set layout
    @Override
    public void setLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 15, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(returnButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(errorLabel, gbc);
    }

    /**
     * gets username from text field
     * @return the username 
     */
    public String getUsername() {
        return usernameField.getText();
    }
    
    /**
     * gets password from password field
     * @return the password
     */
    public char[] getPassword() {
        return passwordField.getPassword();
    }
    
    
    //getters
    public JTextField getUsernameField() {
        return usernameField;
    }
    
    public JPasswordField getPasswordField() {
        return passwordField;
    }
    
    public JButton getLoginButton() {
        return loginButton;
    }
    
    public JButton getReturnButton() {
        return returnButton;
    }
    
    //actionListener for the login button
    public void addLoginButtonActionListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
    
    //actionListener for the return button
    public void addReturnButtonActionListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
    
    /**
     * Test methods
     */
    public void signUpButtonClick(){
        for (ActionListener e : loginButton.getActionListeners()){
            e.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }
    
    public void returnButtonClick(){
        for (ActionListener e : loginButton.getActionListeners()){
            e.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }
    
    /**
     * set error message to errorMessage - used in controller
     * 
     * @param errorMessage the error message to display 
     */
    public void setErrorMessage(String errorMessage) {
        errorLabel.setText(errorMessage);
    }
}

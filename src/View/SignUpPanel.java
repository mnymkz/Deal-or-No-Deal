package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUpPanel extends JPanel implements viewInterface {

    //panel components
    private JLabel signUpLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel passwordLabel2; 
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField passwordField2; 
    private JButton signUpButton;
    private JButton returnButton;

    public SignUpPanel() {
        //init components
        initComponents();
        setLayout();
    }

    /**
     * init components
     */
    @Override
    public void initComponents() {
        signUpLabel = new JLabel("Sign Up to Play Deal or No Deal");
        signUpLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        signUpLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        passwordLabel2 = new JLabel("Re-enter password:");
        passwordLabel2.setFont(new Font("Roboto", Font.PLAIN, 18));
        
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField2 = new JPasswordField(15);

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Roboto", Font.BOLD, 20));
        
        returnButton = new JButton("Go Back");
        returnButton.setFont(new Font("Roboto", Font.BOLD, 20));
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
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel2, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(signUpButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(returnButton, gbc);
    }

    //actionListener for the sign-up button
    public void addSignUpButtonActionListener(ActionListener listener) {
        signUpButton.addActionListener(listener);
    }
    
    public void addReturnButtonActionListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
}

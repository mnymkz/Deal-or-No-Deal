package view;

import components.Container;
import components.GameButton;
import components.Text;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author Tabitha
 */
public class ViewLogin extends JPanel
{
    private final Text loginLabel;
    private final JTextField usernNameField;
    private final JPasswordField passwordField;
    private final GameButton loginButton;

    public ViewLogin()
    {
        setLayout(new BorderLayout());

        //Labels for Title
        loginLabel = new Text("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel, BorderLayout.CENTER);

        //Container for Input
        Container container = new Container(FlowLayout.CENTER, 100, 30);
        usernNameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        container.add(new JLabel("USERNAME: "));
        container.add(usernNameField);
        container.add(new JLabel("PASSWORD:"));
        container.add(passwordField);

        add(container, BorderLayout.CENTER);

        //Login Button
        this.loginButton = new GameButton("Login", Color.yellow);
        add(loginButton, BorderLayout.EAST);
    }

    public String getUserName()
    {
        return usernNameField.getText();
    }


    public String getPassword()
    {
        return new String(passwordField.getPassword());
    }

}
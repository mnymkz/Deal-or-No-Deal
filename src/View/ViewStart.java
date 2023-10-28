package view;

import components.Container;
import components.GameButton;
import components.Text;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Tabitha
 */
public class ViewStart extends JPanel
{
    private Text welcomeLabel;
    private GameButton signInButton;
    private GameButton signUpButton;

    public ViewStart()
    {
        setLayout(new BorderLayout());

        welcomeLabel = new Text("WELCOME TO DEAL OR NO DEAL!\n\nNEW USER?");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
        Container butContainer = new Container(FlowLayout.CENTER, 150, 35);
        
        this.signInButton = new GameButton("SIGN IN", java.awt.Color.BLUE);
        this.signInButton = new GameButton("SIGN IN", java.awt.Color.BLUE);
        add(butContainer, BorderLayout.CENTER);
    }

    public void setSignInButtonListner(ActionListener listner)
    {
        signInButton.addActionListener(listner);
    }

    public void setSignUpButtonListner(ActionListener listner)
    {
        signUpButton.addActionListener(listner);
    }

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("Deal or No Deal");
    //     ViewStart viewStart = new ViewStart();

    //     // Example ActionListener for sign in button
    //     viewStart.setSignInButtonListner(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             // Handle sign in button click event
    //             System.out.println("Sign In button clicked!");
    //         }
    //     });

    //     // Example ActionListener for sign up button
    //     viewStart.setSignUpButtonListner(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             // Handle sign up button click event
    //             System.out.println("Sign Up button clicked!");
    //         }
    //     });

    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.getContentPane().add(viewStart);
    //     frame.pack();
    //     frame.setLocationRelativeTo(null); // Center the frame on the screen
    //     frame.setVisible(true);
    //}
}
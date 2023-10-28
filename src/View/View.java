package View;

import javax.swing.*;
import components.Container;
import components.GameButton;
import static java.awt.Color.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;

/**
 * 
 * @author Tabitha
 */

public class View {
    
    private JFrame frame;
    private GameButton dealButton;
    private GameButton noDealButton;
    private Container buttonContainer;

    public View()
    {
        frame = new JFrame("Deal or No Deal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 700);
        
        this.dealButton = new GameButton("DEAL", GRAY);
        dealButton.setPreferredSize(new Dimension(80, 40));
        
        noDealButton = new GameButton("NO DEAL", GRAY);
        noDealButton.setPreferredSize(new Dimension(80, 40));
        
        this.buttonContainer = new Container(OPAQUE, OPAQUE, OPAQUE, dealButton, noDealButton);

        frame.add(buttonContainer);
    }
    
    public void display()
    {
        frame.setVisible(true);
    }

    public void dealButtonListener(ActionListener listner)
    {
        dealButton.addActionListener(listner);
    }

    public void noDealButtonListener(ActionListener listner)
    {
        noDealButton.addActionListener(listner);
    }
}
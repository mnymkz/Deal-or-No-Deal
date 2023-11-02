
package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @author Michael, Tabitha
 */
public class BankerPanel extends JPanel implements viewInterface {
    private JLabel textLabel;
    private JLabel offerLabel;
    private JButton dealButton;
    private JButton noDealButton;

    public BankerPanel() {
        initComponents();
        setLayout();
    }

    //init components 
    @Override
    public void initComponents() {
        this.offerLabel = new JLabel();
        this.textLabel = new JLabel("Do you accept?");
        this.dealButton = new JButton("Deal");
        this.dealButton.setFont(new Font("Roboto", Font.BOLD, 20));
        this.noDealButton = new JButton("No Deal");
        this.noDealButton.setFont(new Font("Roboto", Font.BOLD, 20));
    }

    //GPT assisted layout
    @Override
    public void setLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(offerLabel, gbc);
        
        gbc.gridy = 1;
        add(textLabel, gbc); // Adding textLabel to the layout
        
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 10, 5);
        gbc.anchor = GridBagConstraints.EAST;
        add(dealButton, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        add(noDealButton, gbc);
    }
    
    //actionListener for the deal button
    public void addDealButtonActionListener(ActionListener listener) {
        dealButton.addActionListener(listener);
    }
    
    //actionListener for the no deal button
    public void addNoDealButtonActionListener(ActionListener listener) {
        noDealButton.addActionListener(listener);
    }
    
    /**
     * setBanker offer 
     * @param offer the offer to set the text to 
     */
    public void setBankerOffer(double offer) {
        offerLabel.setText("Banker offers: " + String.format("$%,.2f", offer));
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Banker Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the frame
        
        BankerPanel bankerPanel = new BankerPanel();
        frame.add(bankerPanel);
        
        frame.setVisible(true);
    }
}
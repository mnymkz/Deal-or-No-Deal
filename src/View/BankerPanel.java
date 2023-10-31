
package View;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Michael
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
        this.offerLabel = new JLabel("Banker offers:");
        this.textLabel = new JLabel("Do you accept?");
        this.dealButton = new JButton("Deal");
        this.dealButton.setFont(new Font("Roboto", Font.BOLD, 20));
        this.noDealButton = new JButton("No Deal");
        this.noDealButton.setFont(new Font("Roboto", Font.BOLD, 20));
    }

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
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 10, 5);
        gbc.anchor = GridBagConstraints.EAST;
        add(dealButton, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 5, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        add(noDealButton, gbc);
    }
}
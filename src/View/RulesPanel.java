
package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Michael
 */
public class RulesPanel extends JPanel implements viewInterface {

    private JTextArea textArea;
    private JButton returnButton;
    
    public RulesPanel() {
        initComponents();
        setLayout();
    }
    
    @Override
    public void initComponents() {
        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Roboto", Font.BOLD, 20));        
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        
        String rulesText = "Game Rules:\n\n" +
                           "1. Choose one briefcase from the available cases.\n" +
                           "2. Open a set number of cases to remove them from the game.\n" +
                           "3. After each round, the Banker will make an offer to buy your case.\n" +
                           "4. Decide whether to accept the offer (Deal) or continue playing (No Deal).\n" +
                           "5. The game continues until you accept a deal or open all the cases.\n" +
                           "6. If you open all cases, the amount in your original case is your final prize.";
        
        textArea.setText(rulesText);
        
        //scrollpane 
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void setLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Constraints for the text area
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        gbc.gridwidth = GridBagConstraints.REMAINDER; // End row
        gbc.fill = GridBagConstraints.BOTH; // Fill entire space
        gbc.weightx = 1.0; // Take up all extra horizontal space
        gbc.weighty = 1.0; // Take up all extra vertical space
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Add a scroll pane around the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane, gbc);

        // Constraints for the return button
        gbc.gridx = 0; // First column
        gbc.gridy = 1; // Second row
        gbc.gridwidth = 1; // One column wide
        gbc.fill = GridBagConstraints.NONE; // Do not resize
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.weightx = 0.0; // Set weight to 0 for button
        gbc.weighty = 0.0; // Set weight to 0 for button
        gbc.insets = new Insets(0, 10, 10, 10); // Padding

        this.add(returnButton, gbc);
    }

    //actionListener for the return button
    public void addReturnButtonActionListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
}

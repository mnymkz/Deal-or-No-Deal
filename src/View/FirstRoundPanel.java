
package View;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Tabitha
 */
public class FirstRoundPanel extends JPanel implements viewInterface {

    private ArrayList<JButton> briefCaseButtons;
    private JLabel label;

    //case components 
    public FirstRoundPanel() {
        initComponents();
        setLayout();
    }

    @Override
    public void initComponents() {
        briefCaseButtons = new ArrayList<>();
        
        for(int i = 1; i <= 26; i++)
        {
            JButton caseButton = new JButton("CASE " + i);
            caseButton.setActionCommand(String.valueOf(i));
            briefCaseButtons.add(caseButton);
        }
        label = new JLabel("DEAL OR NO DEAL");
        label.setHorizontalAlignment(JLabel.CENTER);
    }
    
    @Override
    public void setLayout() {
        this.setLayout(new BorderLayout());
        
        add(label, BorderLayout.NORTH);
        
        JPanel briefCasePanel = new JPanel();
        briefCasePanel.setLayout(new GridLayout(5, 6));
        
        //Centering the added case buttons
        for (JButton caseButton : briefCaseButtons)
        {
            briefCasePanel.add(caseButton);
        }
        
        add(briefCasePanel, BorderLayout.CENTER);
        
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new BoxLayout(moneyPanel, BoxLayout.Y_AXIS));
        
        String[] prizes = {
                "$0.01", "$1", "$5", "$10", "$25",
                "$50", "$75", "$100", "$200", "$300",
                "$400", "$500", "$750", "$1,000", "$5,000",
                "$10,000", "$25,000", "$50,000", "$75,000", "$100,000",
                "$200,000", "$300,000", "$400,000", "$500,000", "$750,000",
                "$1,000,000"
        };

        // Adding each prize amount as a label to the prizePanel
        for (String prize : prizes) {
            JLabel prizeLabel = new JLabel(prize);
            prizeLabel.setHorizontalAlignment(JLabel.CENTER);
            moneyPanel.add(prizeLabel);
        }

        // Adding the prizePanel to the east side of the main FirstRoundPanel
        this.add(moneyPanel, BorderLayout.EAST); 
        moneyPanel.setBorder(new EmptyBorder(0, 10, 0, 10)); 
        label.setBorder(new EmptyBorder(10, 0, 10, 0));
    }
    
    //add case button action listener
    public void addCaseButtonListener(ActionListener listener) {
        for (JButton caseButton : briefCaseButtons) {
            caseButton.addActionListener(listener);
        }
    }
    
    public void removeBriefcase(int caseNumber) {
        briefCaseButtons.get(caseNumber-1).setVisible(false);
    }
 
    public void updateStatusLabel(String text) {
        label.setText(text);
    }
}

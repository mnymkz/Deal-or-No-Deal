package View;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This panel allows the user to pick a certain number of cases to remove.
 */
public class RoundPanel extends JPanel implements viewInterface {

    private ArrayList<JButton> briefCaseButtons;
    private JLabel statusLabel;

    public RoundPanel() {
        initComponents();
        setLayout();
    }

    /**
     * init components
     */
    @Override
    public void initComponents() {
        briefCaseButtons = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            JButton caseButton = new JButton("CASE " + i);
            caseButton.setActionCommand(String.valueOf(i));
            briefCaseButtons.add(caseButton);
        }
        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void setLayout() {
        this.setLayout(new BorderLayout());
        add(statusLabel, BorderLayout.NORTH);
        
        JPanel briefCasePanel = new JPanel(new GridLayout(5, 6, 5, 5));
        briefCasePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        for (JButton caseButton : briefCaseButtons) {
            briefCasePanel.add(caseButton);
        }
        
        add(briefCasePanel, BorderLayout.CENTER);
    }
 
    //add action listeners to the buttons
    public void addCaseButtonListener(ActionListener listener) {
        for (JButton caseButton : briefCaseButtons) {
            caseButton.addActionListener(listener);
        }
    }

    //set case to hidden
    public void removeBriefcase(int caseNumber) {
        briefCaseButtons.get(caseNumber - 1).setEnabled(false);
    }

    //update status 
    public void updateStatusLabel(int choicesLeft) {
        statusLabel.setText("Pick " + choicesLeft + " more cases to remove");
    }
}

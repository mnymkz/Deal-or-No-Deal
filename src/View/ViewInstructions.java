package view;

import components.Text;
import components.Label;
import javax.swing.JPanel;

/**
 * 
 * @author Tabitha
 */

public class ViewInstructions extends JPanel
{
    private Text instructionsText;
    private Label scoreLabel;

    public ViewInstructions()
    {
        instructionsText = new Text("DEAL OR NO DEAL!");
        scoreLabel = new Label("Earnings: $");

        this.add(instructionsText);
        this.add(scoreLabel);
    }
}
package View;

import Banker.Banker;
import javax.swing.JPanel;
import components.Text;
import Case.Case;
import components.GameButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * 
 * @author Tabitha
 */
public class ViewBankerOffer extends JPanel
{
    private Case selectedCase;
    private Text bankerOfferText;
    private Banker banker;

    public ViewBankerOffer(Case selectedCase, Banker banker)
    {
        this.selectedCase = selectedCase;
        initializeComponents();
        setupLayout();
        this.banker = banker;
    }

    public void initializeComponents() 
    {
        bankerOfferText = new Text("BANKER OFFERS...");

        double offerAmount = banker.calculateOffer(); // Implement this method to calculate the banker's offer
        Text offerAmountText = new Text("$" + String.format("%.2f", offerAmount));

        GameButton acceptButton = new GameButton("acceptButton", Color.GREEN);
        acceptButton.setText("Accept Offer");
        GameButton rejectButton = new GameButton("declineButton", Color.RED);
        acceptButton.setText("Decline Offer");
             

        this.add(bankerOfferText);
        this.add(offerAmountText);
        this.add(acceptButton);
        this.add(rejectButton);
    }

    public void setupLayout() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(400, 200));
    }
}
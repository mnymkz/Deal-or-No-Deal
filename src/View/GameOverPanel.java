
package View;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Michael
 */
public class GameOverPanel extends JPanel implements viewInterface {
    
    private JLabel gameOverLabel;
    private JLabel totalEarningsLabel;
    private JButton playAgainButton;
    private JButton exitButton;

    public GameOverPanel() {
        initComponents();
        setLayout();
    }

    //init components
    @Override
    public void initComponents() {
        gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

        totalEarningsLabel = new JLabel("Total Earnings: $0");
        totalEarningsLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        totalEarningsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Roboto", Font.BOLD, 20));

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Roboto", Font.BOLD, 20));
    }

    //GPT assisted layout
    @Override
    public void setLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        add(gameOverLabel, gbc);

        gbc.gridy++;
        add(totalEarningsLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 20, 10);
        add(playAgainButton, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(10, 10, 20, 20);
        add(exitButton, gbc);
    }
}

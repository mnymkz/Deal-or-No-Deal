package components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;


/**
 * 
 * @author Tabitha
 */

public class GameButton extends JButton {
    public GameButton(String name, Color color) {
		super(name);
		this.setBackground(color);
		this.setBorderPainted(false);
	}

	public GameButton(String name, Color color, int width, int height) {
		this(name, color);
		this.setPreferredSize(new Dimension(width, height));
	}
}
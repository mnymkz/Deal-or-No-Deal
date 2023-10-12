package Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * 
 * @author Tabitha
 */

public class Button extends JButton
{
    public Button(String name, String color) {
		super(name);
		this.setBackground(Color.decode(color));
		this.setBorderPainted(false);
	}

	public Button(String name, String color, int width, int height) {
		this(name, color);
		this.setPreferredSize(new Dimension(width, height));
	}

    //a constructor to align the text?
}

package Components;

import java.awt.Font;
import javax.swing.JLabel;

/**
 * 
 * @author Tabitha
 */

public class Text extends JLabel
{
    /**
     * Creates a Text label with a specified text
     * @param text
     */
    public Text(String text) 
    {
        super(text);
    }

    /**
     * Creates a Text label with a specified text and font
     * @param text
     */
    public Text(String text, Font font) 
    {
        super(text);
        setFont(font);
    }
    
}

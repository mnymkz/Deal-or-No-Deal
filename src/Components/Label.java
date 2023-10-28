
package components;

import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author Tabitha
 */

public class Label extends JLabel
{
    public Label(String text)
    {
        super(text);
    }
    
    public Label(String text, Font font)
    {
        super(text);
        setFont(font);
    }
}
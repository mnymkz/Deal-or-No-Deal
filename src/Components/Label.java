package Components;

import javax.swing.JLabel;
import java.awt.font;
import java.awt.color;

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

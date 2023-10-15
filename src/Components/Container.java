package Components;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;


public class Container 
{
    public Container(int width, int height)
    {
        setPrefferedSize(new Dimension(width, height));
        setBackground(Color.darkGray);
    }
}

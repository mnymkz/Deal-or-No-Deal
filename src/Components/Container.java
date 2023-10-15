package Components;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;


public class Container 
{
    /**
     * Creates a Container panel including the specified width and height
     * Sets backgorund to dark grey
     * 
     * @param width
     * @param height
     */
    public Container(int width, int height)
    {
        setPrefferedSize(new Dimension(width, height));
        this.setBackground(Color.darkGray);
    }

    /**
     * Creates a Container panel including specified alignment, width and height
     * Components are added with specific alignment within the panel
     * 
     * @param align
     * @param width
     * @param height
     * @param components
     */
    public Container(int align, int width, int height, JComponent... components)
    {
        this(width, height);
        this.setLayout(new FlowLayout(align));
        for (JComponent i : components) this.add(i);
    }


}

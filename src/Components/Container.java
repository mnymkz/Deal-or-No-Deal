package components;

import javax.swing.*;
import java.awt.*;


/**
 * 
 * @author Tabitha
 */

public class Container extends JPanel
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
        setPreferredSize(new Dimension(width, height));
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
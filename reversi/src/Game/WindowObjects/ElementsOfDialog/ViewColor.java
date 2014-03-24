package Game.WindowObjects.ElementsOfDialog;

import javax.swing.*;
import java.awt.*;

public class ViewColor extends JComponent{
    
    private Color color;

    /**
     * view color
     * @param color color
     * @param dimension dimension
     */
    public ViewColor(Color color, Dimension dimension){
        this.color = color;
        this.setPreferredSize(dimension);
    }

    /**
     * set color
     * @param color color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * paint component
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

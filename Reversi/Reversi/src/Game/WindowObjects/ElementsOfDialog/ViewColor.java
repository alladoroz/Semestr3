package Game.WindowObjects.ElementsOfDialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 08.03.12
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class ViewColor extends JComponent{
    
    private Color color;
    
    public ViewColor(Color color, Dimension dimension){
        this.color = color;
        this.setPreferredSize(dimension);
    }

    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

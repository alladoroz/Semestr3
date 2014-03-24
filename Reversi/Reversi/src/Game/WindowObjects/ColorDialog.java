package Game.WindowObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 08.03.12
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */
public class ColorDialog extends JDialog{
    
    private JColorChooser colorChooser;
    private boolean changeColor;

    public ColorDialog(JDialog owner, String title, boolean modal){
        super(owner, title, modal);
        changeColor = false;
        colorChooser = new JColorChooser();
        this.setLocation(300, 200);
        this.setSize(500, 420);

        Container contentPane = this.getContentPane();
        contentPane.add(colorChooser, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ButtonListener(true));
        panel.add(ok);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ButtonListener(false));
        panel.add(cancel);
        
        contentPane.add(panel, BorderLayout.SOUTH);
    }
    
    public boolean isChangeColor(){
        boolean clone = changeColor;
        changeColor = false;
        return clone;
    }

    public Color getColor(){
        return colorChooser.getColor();
    }

    private class ButtonListener implements ActionListener {

        private boolean value;

        public ButtonListener(boolean value){
            this.value = value;
        }

        public void actionPerformed(ActionEvent e) {
            changeColor = value;
            setVisible(false);
        }
    }
}

package Game.WindowObjects.ElementsOfDialog;

import Game.StandardConfig;
import Game.WindowObjects.ColorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 07.03.12
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class PlayerBox {
    
    private Box box;
    private JTextField playerName;
    private JComboBox botDifficulty;
    private ViewColor viewColor;
    private int numberOfPlayer;
    private Color playerColor;
    private ColorDialog colorDialog;

    public PlayerBox(int numberOfPlayer, ColorDialog colorDialog){
        this.colorDialog = colorDialog;
        this.numberOfPlayer = numberOfPlayer;
        box = Box.createVerticalBox();
        playerName = new JTextField("Player " + numberOfPlayer);
        playerColor = StandardConfig.playerColors[numberOfPlayer - 1];

        createBoxStrut();

        JPanel panel = new JPanel();
        panel.add(new JLabel("Player " + numberOfPlayer));
        panel.add(playerName);
        box.add(panel);

        createBoxStrut();

        box.add(createRadioButtonGroup());

        botDifficulty = createComboBox();
        box.add(botDifficulty);

        createBoxStrut();

        if (colorDialog != null){
            box.add(createColorChangePanel());
        }

        createBoxStrut();
    }
    
    private void createBoxStrut(){
        box.add(Box.createVerticalStrut(15));
    }

    private JPanel createColorChangePanel(){
        JPanel panel = new JPanel();
        JButton button = new JButton("change color");

        button.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                colorDialog.setVisible(true);
                if (colorDialog.isChangeColor()){
                    setPlayerColor(colorDialog.getColor());
                }
            }
        });

        viewColor = new ViewColor(playerColor, new Dimension(30, 30));
        panel.add(viewColor);
        panel.add(button);
        return panel;
    }

    private void setPlayerColor(Color color){
        viewColor.setColor(color);
        playerColor = color;
        viewColor.repaint();
    }
    
    public Color getPlayerColor(){
        return playerColor;
    }

    private JComboBox createComboBox(){
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("very easy");
        comboBox.addItem("easy");
        comboBox.addItem("normal");
        comboBox.addItem("hard");
        comboBox.addItem("expert");
        comboBox.setEnabled(false);
        return comboBox;
    }

    private JPanel createRadioButtonGroup(){
        JPanel group = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton player = new JRadioButton("human", true);
        JRadioButton bot = new JRadioButton("bot", false);
        group.add(player);
        group.add(bot);
        buttonGroup.add(player);
        buttonGroup.add(bot);
        player.addActionListener(new RadioButtonListener(false));
        bot.addActionListener(new RadioButtonListener(true));
        return group;
    }
    
    public Box getBox(){
        return box;
    }
    
    public String getPlayerName(){
        if (playerName.getText().equals("")){
            playerName.setText("Player " + numberOfPlayer);
        }
        return playerName.getText();
    }
    
    public int getDifficulty(){
        return botDifficulty.getSelectedIndex();
    }
    
    public boolean isBot(){
        return botDifficulty.isEnabled();
    }

    private class RadioButtonListener extends AbstractAction {

        private boolean value;

        private RadioButtonListener(boolean value){
            this.value = value;
        }

        public void actionPerformed(ActionEvent e) {
            botDifficulty.setEnabled(value);
        }
    }
}

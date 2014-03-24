
package Game.WindowObjects.ElementsOfDialog;

import Game.StandardConfig;
import Game.WindowObjects.ColorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Player box
 */
public class PlayerBox {
    
    private Box box;
    private JTextField playerName;
    private JComboBox botDifficulty;
    private ViewColor viewColor;
    private int numberOfPlayer;
    private Color playerColor;
    private ColorDialog colorDialog;

    /**
     * constructor
     * @param numberOfPlayer number of players
     * @param colorDialog color dialog
     */
    public PlayerBox(int numberOfPlayer, ColorDialog colorDialog) {
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

    /**
     * get player color
     * @return player color
     */
    public Color getPlayerColor(){
        return playerColor;
    }

    private JComboBox createComboBox(){
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("very easy");
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

    /**
     * box
     * @return box
     */
    public Box getBox(){
        return box;
    }

    /**
     * get player name
     * @return player name
     */
    public String getPlayerName(){
        if (playerName.getText().equals("")){
            playerName.setText("Player " + numberOfPlayer);
        }
        return playerName.getText();
    }

    /**
     * get difficulty
     * @return bot difficulty
     */
    public int getDifficulty(){
        return botDifficulty.getSelectedIndex();
    }

    /**
     * Whether player is bot
     * @return boolean information about player
     */
    public boolean isBot(){
        return botDifficulty.isEnabled();
    }

    /**
     * button listener
     */
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

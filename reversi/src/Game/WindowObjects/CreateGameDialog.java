package Game.WindowObjects;

import Game.Players.*;
import Game.WindowObjects.ElementsOfDialog.PlayerBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create game
 */
public class CreateGameDialog extends JDialog {

    private boolean createNewGame;
    protected Player player_1;
    protected Player player_2;

    /**
     * Constructor
     * @param owner owner
     * @param title title
     * @param player_1 first player
     * @param player_2 second player
     */
    public CreateGameDialog(Frame owner, String title, Player player_1, Player player_2){
        super(owner, title, true);
        this.player_1 = player_1;
        this.player_2 = player_2;
        createNewGame = false;
    }

    /**
     * Create new game
     * @return current state
     */
    public boolean isCreateNewGame() {
        boolean current = createNewGame;
        createNewGame = false;
        return current;
    }

    /**
     * Button panel constructor
     * @return button panel
     */
    protected JPanel createButtonPanel() {
        JButton ok = new JButton("OK");
        ok.addActionListener(new ButtonListener(true));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ButtonListener(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        return buttonPanel;
    }

    /**
     * Player creator
     * @param playerBox player box
     * @param color color
     * @param rival player
     * @return player
     */
    protected Player createPlayer(PlayerBox playerBox, Color color, Player rival){
        Player player;
        String name = playerBox.getPlayerName();
        if (playerBox.isBot()) {
            switch (playerBox.getDifficulty()) {
                case 0: player = new VeryEasyBot(color, name, rival);
                    break;
                case 1: player = new NotSoEasyBot(color, name, rival);
                    break;
                default: player = new VeryEasyBot(color, name, rival);
                    break;
            }
        }
        else {
            player = new Player(color, playerBox.getPlayerName(), true);
        }
        return player;
    }

    /**
     * button listener
     */
    private class ButtonListener implements ActionListener {

        private boolean value;

        public ButtonListener(boolean value){
            this.value = value;
        }

        public void actionPerformed(ActionEvent e) {
            createNewGame = value;
            setVisible(false);
        }
    }
}

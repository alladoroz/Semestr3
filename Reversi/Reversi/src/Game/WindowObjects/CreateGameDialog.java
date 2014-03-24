package Game.WindowObjects;

import Game.Players.*;
import Game.WindowObjects.ElementsOfDialog.PlayerBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 12.05.12
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class CreateGameDialog extends JDialog {

    private boolean createNewGame;
    protected Player player_1;
    protected Player player_2;

    public CreateGameDialog(Frame owner, String title, Player player_1, Player player_2){
        super(owner, title, true);
        this.player_1 = player_1;
        this.player_2 = player_2;
        createNewGame = false;
    }

    public boolean isCreateNewGame(){
        boolean current = createNewGame;
        createNewGame = false;
        return current;
    }

    protected JPanel createButtonPanel(){
        JButton ok = new JButton("OK");
        ok.addActionListener(new ButtonListener(true));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ButtonListener(false));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ok);
        buttonPanel.add(cancel);

        return buttonPanel;
    }

    protected Player createPlayer(PlayerBox playerBox, Color color, Player rival){
        Player player;
        String name = playerBox.getPlayerName();
        if (playerBox.isBot()){
            switch (playerBox.getDifficulty()){
                case 0: player = new VeryEasyBot(color, name, rival);
                    break;
                case 1: player = new EasyBot(color, name, rival);
                    break;
                case 2: player = new NormalBot(color, name, rival);
                    break;
                case 3: player = new HardBot(color, name, rival);
                    break;
                default: player = new ExpertBot(color, name, rival);
                    break;
            }
        }
        else {
            player = new Player(color, playerBox.getPlayerName(), true);
        }
        return player;
    }


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

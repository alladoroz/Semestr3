package Game.WindowObjects;

import Game.Players.Player;
import Game.WindowObjects.ElementsOfDialog.PlayerBox;

import javax.swing.*;
import java.awt.*;

/**
 * Create local game
 */
public class CreateLocalGameDialog extends CreateGameDialog {

    private static final int strutSize = 15;

    private PlayerBox playerBox_1;
    private PlayerBox playerBox_2;

    public CreateLocalGameDialog(Frame owner, String title, Player player_1, Player player_2) {
        super(owner, title, player_1, player_2);
        this.setLocation(owner.getLocation().x + 100, owner.getLocation().y + 100);
        this.setSize(new Dimension(400, 300));
        ColorDialog colorDialog = new ColorDialog(this, "Change Color", true);
        
        Container contentPane = this.getContentPane();

        playerBox_1 = new PlayerBox(1, colorDialog);
        playerBox_2 = new PlayerBox(2, colorDialog);

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(Box.createVerticalStrut(strutSize));
        mainBox.add(playerBox_1.getBox());
        mainBox.add(Box.createVerticalStrut(strutSize));
        mainBox.add(playerBox_2.getBox());
        mainBox.add(Box.createVerticalStrut(strutSize));
        contentPane.add(mainBox,BorderLayout.NORTH);

        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Get player
     * @param number Player number
     * @return Player
     */
    public Player getPlayer(int number){
        if (number == 1){
            player_1 = createPlayer(playerBox_1, playerBox_1.getPlayerColor(), player_2);
            return player_1;
        }
        player_2 = createPlayer(playerBox_2, playerBox_2.getPlayerColor(), player_1);
        return player_2;
    }
}

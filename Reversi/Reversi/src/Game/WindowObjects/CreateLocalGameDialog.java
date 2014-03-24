package Game.WindowObjects;

import Game.Players.Player;
import Game.WindowObjects.ElementsOfDialog.PlayerBox;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 03.03.12
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
public class CreateLocalGameDialog extends CreateGameDialog {

    private static final int strutSize = 15;

    private PlayerBox playerBox_1;
    private PlayerBox playerBox_2;
    private ColorDialog colorDialog;

    public CreateLocalGameDialog(Frame owner, String title, Player player_1, Player player_2) {
        super(owner, title, player_1, player_2);
        this.setLocation(owner.getLocation().x + 100, owner.getLocation().y + 100);
        this.setSize(new Dimension(400, 300));
        colorDialog = new ColorDialog(this, "Change Color", true);
        
        Container contentPane = this.getContentPane();

        playerBox_1 = new PlayerBox(1, colorDialog);
        playerBox_2 = new PlayerBox(2, colorDialog);

        Box mainBox=Box.createHorizontalBox();
        mainBox.add(Box.createVerticalStrut(strutSize));
        mainBox.add(playerBox_1.getBox());
        mainBox.add(Box.createVerticalStrut(strutSize));
        mainBox.add(playerBox_2.getBox());
        mainBox.add(Box.createVerticalStrut(strutSize));
        contentPane.add(mainBox,BorderLayout.NORTH);

        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    public Player getPlayer(int number){
        if (number == 1){
            player_1 = createPlayer(playerBox_1, playerBox_1.getPlayerColor(), player_2);
            return player_1;
        }
        player_2 = createPlayer(playerBox_2, playerBox_2.getPlayerColor(), player_1);
        return player_2;
    }
}

package Game.WindowObjects;

import Game.Players.Player;
import Game.StandardConfig;
import Game.WindowObjects.ElementsOfDialog.PlayerBox;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 27.04.12
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class CreateServerDialog extends CreateGameDialog {

    private PlayerBox playerBox;

    public CreateServerDialog(Frame owner, String title, Player player_1, Player player_2) {
        super(owner, title, player_1, player_2);
        this.setLocation(owner.getLocation().x + 100, owner.getLocation().y + 100);
        this.setSize(new Dimension(200, 300));

        Container contentPane = this.getContentPane();
        playerBox = new PlayerBox(1, null);
        JPanel panel = new JPanel();
        panel.add(playerBox.getBox());
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    public Player getPlayer(){
        return createPlayer(playerBox, StandardConfig.playerColors[0], player_2);
    }
}

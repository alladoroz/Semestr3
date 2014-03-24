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
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
public class JoinServerDialog extends CreateGameDialog {

    private JTextField host;
    private PlayerBox playerBox;

    public JoinServerDialog(Frame owner, String title, Player player_1, Player player_2) {
        super(owner, title, player_1, player_2);
        this.setLocation(owner.getLocation().x + 100, owner.getLocation().y + 100);
        this.setSize(new Dimension(200, 300));

        Container contentPane = this.getContentPane();
        playerBox = new PlayerBox(2, null);
        JPanel boxPanel = new JPanel();
        boxPanel.add(playerBox.getBox());
        contentPane.add(boxPanel, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        host = new JTextField(StandardConfig.host);
        panel.add(new JLabel("address:"));
        panel.add(host);
        contentPane.add(panel);
        contentPane.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    public Player getPlayer(){
        return createPlayer(playerBox, StandardConfig.playerColors[1], player_1);
    }

    public String getHost(){
        return host.getText();
    }
}

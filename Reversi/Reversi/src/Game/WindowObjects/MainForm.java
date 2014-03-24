package Game.WindowObjects;

import Game.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 27.02.12
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public class MainForm{

    private GameManager gameManager;
    private JFrame frame;

    public MainForm(){
        //Create frame
        frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(416, 590);
        frame.setLocation(200, 100);

        MainMenuBar menu = new MainMenuBar();

        gameManager = new GameManager(frame, menu);

        menu.getNewLocalGameItem().addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                gameManager.newLocalGame();
            }
        });

        menu.getUndoItem().addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                gameManager.pressUndo();
            }
        });

        menu.getCreateServerItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameManager.newServerGame();
            }
        });

        menu.getJoinServerItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameManager.joinServerGame();
            }
        });

        menu.getDisconnectItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameManager.pressDisconnect();
            }
        });

        frame.setJMenuBar(menu.getMenuBar());
        frame.setVisible(true);
    }
}

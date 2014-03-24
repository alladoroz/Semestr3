package Game.WindowObjects;

import Game.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class of main form
 */
public class MainForm{

    private GameManager gameManager;
    private JFrame frame;

    /**
     * Main form creator
     */
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

        frame.setJMenuBar(menu.getMenuBar());
        frame.setVisible(true);
    }
}

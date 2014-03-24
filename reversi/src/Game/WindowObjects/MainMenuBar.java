package Game.WindowObjects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Main menu bar class
 */
public class MainMenuBar {

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newLocalGameItem;
    private JMenu movesMenu;

    private JMenuItem undoItem;

    /**
     * Main menu bar constructor
     */
    public MainMenuBar(){
        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        newLocalGameItem = new JMenuItem("New Game");
        newLocalGameItem.setMnemonic(KeyEvent.VK_N);
        gameMenu.add(newLocalGameItem);

        Action exitAction = new AbstractAction("Exit"){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        JMenuItem exitItem = new JMenuItem(exitAction);
        exitItem.setMnemonic(KeyEvent.VK_E);
        gameMenu.add(exitItem);

        movesMenu = new JMenu("Moves");
        movesMenu.setMnemonic(KeyEvent.VK_M);
        menuBar.add(movesMenu);

        undoItem = new JMenuItem("Undo");
        undoItem.setMnemonic(KeyEvent.VK_U);
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.CTRL_MASK));

        movesMenu.add(undoItem);
    }

    /**
     * New local game item
     * @return local game bar
     */
    public JMenuItem getNewLocalGameItem(){
        return newLocalGameItem;
    }

    /**
     * Menu bar
     * @return  menu bar
     */
    public JMenuBar getMenuBar(){
        return menuBar;
    }

    /**
     * Undo item
     * @return  undo bar
     */
    public JMenuItem getUndoItem(){
        return undoItem;
    }

}

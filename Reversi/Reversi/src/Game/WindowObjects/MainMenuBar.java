package Game.WindowObjects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 03.03.12
 * Time: 11:48
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuBar {

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newLocalGameItem;
    private JMenu movesMenu;
    private JMenuItem undoItem;
    private JMenuItem createServerItem;
    private JMenuItem joinServerItem;
    private JMenuItem disconnectItem;

    public MainMenuBar(){
        menuBar = new JMenuBar();

        gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        menuBar.add(gameMenu);

        newLocalGameItem = new JMenuItem("New Game");
        newLocalGameItem.setMnemonic(KeyEvent.VK_N);
        gameMenu.add(newLocalGameItem);

        gameMenu.addSeparator();

        createServerItem = new JMenuItem("Create Server");
        createServerItem.setMnemonic(KeyEvent.VK_C);
        gameMenu.add(createServerItem);

        joinServerItem = new JMenuItem("Join Server");
        joinServerItem.setMnemonic(KeyEvent.VK_J);
        gameMenu.add(joinServerItem);

        disconnectItem = new JMenuItem("Disconnect");
        disconnectItem.setMnemonic(KeyEvent.VK_D);
        gameMenu.add(disconnectItem);

        gameMenu.addSeparator();

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

    public JMenuItem getNewLocalGameItem(){
        return newLocalGameItem;
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
    
    public JMenuItem getUndoItem(){
        return undoItem;
    }

    public JMenuItem getCreateServerItem(){
        return createServerItem;
    }

    public JMenuItem getJoinServerItem(){
        return joinServerItem;
    }

    public JMenuItem getDisconnectItem(){
        return disconnectItem;
    }

}

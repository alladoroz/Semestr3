package Game;

import Game.Players.Player;
import Game.WindowObjects.CreateLocalGameDialog;
import Game.WindowObjects.InfoField;
import Game.WindowObjects.MainMenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Game manager class
 */
public class GameManager{

    private JFrame frame;
    private GameSession currentGame;
    private Container container;
    private CreateLocalGameDialog localGameDialog;
    private JMenuItem undoItem;
    private JMenuItem localGameItem;

    //Players
    private Player player_1;
    private Player player_2;

    /**
     * Constructor
     * @param frame is frame
     * @param menu is menu
     */
    public GameManager(JFrame frame, MainMenuBar menu){
        this.frame = frame;
        undoItem = menu.getUndoItem();
        undoItem.setEnabled(false);
        localGameItem = menu.getNewLocalGameItem();
        this.container = frame.getContentPane();
        localGameDialog = new CreateLocalGameDialog(frame, "Create New Game", player_1, player_2);
    }


    /**
     * Create new game
     * @param undoItem Undo bar
     */
    private void newGame(JMenuItem undoItem){
        InfoField info = new InfoField(player_1.getName(), player_2.getName());

        if (undoItem != null)
            undoItem.setEnabled(false);

        container.removeAll();

        currentGame = new GameSession(player_1, player_2, info, undoItem);
        resizeGame(new Dimension(400, 400));

        container.add(currentGame, BorderLayout.CENTER);
        container.add(info.getInfoBox(), BorderLayout.SOUTH);
    }

    /**
     * Create new local game
     */
    public void newLocalGame(){
        localGameDialog.setLocation(frame.getLocation().x + 100, frame.getLocation().y + 100);
        localGameDialog.setVisible(true);
        if (!localGameDialog.isCreateNewGame()){
            return;
        }
        player_1 = localGameDialog.getPlayer(1);
        player_2 = localGameDialog.getPlayer(2);
        newGame(undoItem);
    }

    /**
     * Resizes window
     * @param dimension is dimension
     */
    public void resizeGame(Dimension dimension){
        currentGame.resizeGameWindow(dimension);
    }

    /**
     * Undo action
     */
    public void pressUndo(){
        if (currentGame != null && !currentGame.hasSaveMoves()){
            return;
        }
        currentGame.moveBack();
        undoItem.setEnabled(currentGame.hasSaveMoves());
    }
}



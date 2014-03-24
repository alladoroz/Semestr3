package Game;

import Game.Players.Player;
import Game.Server.ReversiClient;
import Game.Server.ReversiServer;
import Game.WindowObjects.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 28.02.12
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class GameManager{

    private JFrame frame;
    private GameSession currentGame;
    private Container container;
    private CreateLocalGameDialog localGameDialog;
    private JMenuItem undoItem;
    private JMenuItem createServerItem;
    private JMenuItem joinServerItem;
    private JMenuItem disconnectItem;
    private JMenuItem localGameItem;
    private CreateServerDialog serverDialog;
    private JoinServerDialog joinServerDialog;
    private Thread thread;
    private boolean isServerThread;

    //Players
    private Player player_1;
    private Player player_2;

    public GameManager(JFrame frame, MainMenuBar menu){
        this.frame = frame;
        undoItem = menu.getUndoItem();
        undoItem.setEnabled(false);
        disconnectItem = menu.getDisconnectItem();
        disconnectItem.setEnabled(false);
        createServerItem = menu.getCreateServerItem();
        joinServerItem = menu.getJoinServerItem();
        localGameItem = menu.getNewLocalGameItem();
        this.container = frame.getContentPane();
        localGameDialog = new CreateLocalGameDialog(frame, "Create New Game", player_1, player_2);
        serverDialog = new CreateServerDialog(frame, "Create Server", player_1, player_2);
        joinServerDialog = new JoinServerDialog(frame, "Join To Server", player_1, player_2);
    }

    //Create new game
    private void newGame(JMenuItem undoItem){
        InfoField info = new InfoField(player_1.getName(), player_2.getName());

        if (undoItem == null){
            createServerItem.setEnabled(false);
            joinServerItem.setEnabled(false);
            if (isServerThread){
                disconnectItem.setEnabled(true);
            }
            else {
                disconnectItem.setEnabled(false);
            }
        }
        else {
            undoItem.setEnabled(false);
        }

        container.removeAll();

        currentGame = new GameSession(player_1, player_2, info, undoItem, disconnectItem);
        resizeGame(new Dimension(400, 400));

        container.add(currentGame, BorderLayout.CENTER);
        container.add(info.getInfoBox(), BorderLayout.SOUTH);
    }

    //Create new local game
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

    //Create new server
    public void newServerGame(){
        serverDialog.setLocation(frame.getLocation().x + 100, frame.getLocation().y + 100);
        serverDialog.setVisible(true);
        if (!serverDialog.isCreateNewGame()){
            return;
        }
        player_1 = serverDialog.getPlayer();
        player_2 = new Player(Color.BLACK, "client", false);
        localGameItem.setEnabled(false);
        newGame(null);

        thread = new ServerThread();
        isServerThread = true;
        thread.start();
    }

    //Join to server
    public void joinServerGame(){
        joinServerDialog.setLocation(frame.getLocation().x + 100, frame.getLocation().y + 100);
        joinServerDialog.setVisible(true);
        if (!joinServerDialog.isCreateNewGame()){
            return;
        }
        player_1 = new Player(Color.WHITE, "server", false);
        player_2 = joinServerDialog.getPlayer();
        localGameItem.setEnabled(false);
        newGame(null);
        thread = new ClientThread();
        isServerThread = false;
        thread.start();
    }

    public void resizeGame(Dimension dimension){
        currentGame.resizeGameWindow(dimension);
    }

    public void pressUndo(){
        if (currentGame != null && !currentGame.hasSaveMoves()){
            return;
        }
        currentGame.moveBack();
        undoItem.setEnabled(currentGame.hasSaveMoves());
    }

    public void pressDisconnect(){
        disconnect();
        if (thread == null){
            return;
        }
        thread.interrupt();
        if (isServerThread && ((ServerThread) thread).getAcceptingStatus()){
            ReversiClient rc = new ReversiClient(frame, currentGame);
            try {
                rc.startClient(StandardConfig.host);
            } catch (Exception e) {
            }
        }
        thread = null;
    }

    private void disconnect(){
        disconnectItem.setEnabled(false);
        createServerItem.setEnabled(true);
        joinServerItem.setEnabled(true);
        localGameItem.setEnabled(true);
        currentGame = null;
        container.removeAll();
        frame.repaint();
    }

    private class ServerThread extends Thread {
        private ReversiServer rs;

        public void run() {
            rs = new ReversiServer(frame, currentGame);
            try {
                rs.startServer();
            } catch (Exception e) {
                disconnect();
            }
        }

        public boolean getAcceptingStatus(){
            return rs.getAcceptingStatus();
        }
    }

    private class ClientThread extends Thread {
        private ReversiClient rc;

        public void run() {
            rc = new ReversiClient(frame, currentGame);
            try {
                rc.startClient(joinServerDialog.getHost());
            } catch (Exception e) {
                disconnect();
            }
        }
    }
}



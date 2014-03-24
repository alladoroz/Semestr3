package Game;

import Game.GamePaintTools.Board;
import Game.Players.Bot;
import Game.Players.Player;
import Game.ServiceTools.Cells.Chip;
import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsKeeper;
import Game.ServiceTools.MouseListenerForGame;
import Game.WindowObjects.InfoField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 28.02.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class GameSession extends JComponent{

    //Players
    private Player player_1;
    private Player player_2;
    private Player currentPlayer;

    private InfoField infoField;
    private JMenuItem undoItem;
    private JMenuItem disconnectItem;
    private LocationsKeeper locationsKeeper;
    private volatile Coordinates lastMoveCoordinates;

    //GamePaintTools
    private volatile Board board;

    private volatile boolean gameEnd;

    public GameSession(Player player_1, Player player_2, InfoField infoField,
                       JMenuItem undoItem, JMenuItem disconnectItem){
        this.infoField = infoField;
        this.player_1 = player_1;
        this.player_2 = player_2;
        this.undoItem = undoItem;
        this.disconnectItem = disconnectItem;

        lastMoveCoordinates = null;

        Chip.setChips(new Chip(player_1), new Chip(player_2));

        gameEnd = false;
        currentPlayer = player_2;

        board = new Board();
        board.initialFilling(player_1, player_2);
        infoField.setMoveLabel(currentPlayer.getName());
        locationsKeeper = new LocationsKeeper();

        //Add listener
        MouseListenerForGame mouseListenerForGame = new MouseListenerForGame(this);
        this.addMouseListener(mouseListenerForGame);
        this.addMouseMotionListener(mouseListenerForGame);

        //repaint
        new Thread(new Runnable() {
            public void run() {
                repaint();
            }
        }).start();

        if (currentPlayer.isBot()){
            doMoveBot();
        }
    }

    private synchronized void doMove(Coordinates coordinates){
        if (undoItem != null){
            locationsKeeper.saveMove(board.getLocations(), currentPlayer);
            undoItem.setEnabled(true);
        }
        board.move(currentPlayer, coordinates);
        changePlayer();
        infoField.setMoveLabel(currentPlayer.getName());
        checkEndGameAndMoves();
        this.repaint();
        if(currentPlayer.isBot()){
            doMoveBot();
        }
    }

    private synchronized void doMoveBot(){
        if (gameEnd){
            return;
        }
        if (board.hasAnyMove(currentPlayer)){
            Coordinates tmp;
            tmp = ((Bot) currentPlayer).botMove(board.getLocations());
            doMove(tmp);
            lastMoveCoordinates = tmp;
            return;
        }
        checkEndGameAndMoves();
    }

    private synchronized void checkEndGameAndMoves(){
        if (!board.hasAnyMove(currentPlayer)){
            changePlayer();
            infoField.setOtherInfo("");
            if (board.hasAnyMove(currentPlayer)){
                changePlayer();
                String text = currentPlayer.getName() + " has not moves!";
                changePlayer();
                infoField.setMoveLabel(currentPlayer.getName());
                infoField.setOtherInfo(text);
            }
            else{
                int checksPlayer_1 = board.getNumberOfChecks(player_1);
                int checksPlayer_2 = board.getNumberOfChecks(player_2);
                if (checksPlayer_1 == checksPlayer_2){
                    infoField.setOtherInfo("End Game. Drawn Game");
                    setHasNotMovesField();
                }
                else {
                    if (checksPlayer_1 > checksPlayer_2){
                        infoField.setOtherInfo("End Game. " + player_1.getName() + " is winner");
                    }
                    else {
                        infoField.setOtherInfo("End Game. " + player_2.getName() + " is winner");
                    }
                    setHasNotMovesField();
                }
                gameEnd = true;
                if (undoItem != null){
                    undoItem.setEnabled(false);
                }
            }
            repaint();
        }
    }

    private synchronized void setHasNotMovesField(){
        infoField.setMoveLabel("");
    }

    public synchronized void humanMove(int x, int y){
        if (!gameEnd && !currentPlayer.isBot() && currentPlayer.isLocal()){
            Coordinates coordinates = makeCoordinate(x, y);
            if (board.canPut(currentPlayer, coordinates)){
                doMove(coordinates);
                lastMoveCoordinates = coordinates;
            }
        }
    }

    public synchronized void doInternetMove(Coordinates coordinates){
        if (board.canPut(currentPlayer, coordinates)){
            doMove(coordinates);
            repaint();
        }
    }

    public synchronized Coordinates makeCoordinate(int x, int y){
        int sizeCell = 400 / 8;
        int X = x / sizeCell;
        int Y = y / sizeCell;
        return new Coordinates(X, Y);
    }

    public synchronized void currentMouseCoordinate(int x, int y){
        if (currentPlayer.isBot() || !currentPlayer.isLocal()){
            return;
        }
        Coordinates coordinates = makeCoordinate(x, y);
        if (board.canPut(currentPlayer, coordinates)) {
            board.changePhantom(currentPlayer.getColorOfChecks(), coordinates);
        }
        else {
            board.delPhantom();
        }
        repaint();
    }

    private synchronized void changePlayer(){
        if (currentPlayer == player_1){
            currentPlayer = player_2;
        }
        else {
            currentPlayer = player_1;
        }
    }

    public synchronized void resizeGameWindow(Dimension dimension){
        this.setSize(dimension);
        board.setSize(dimension);
        this.repaint();
    }

    @Override
    protected synchronized void paintComponent(Graphics g){
        board.paintComponent(g);

        infoField.setChipsInfo_1("" + board.getNumberOfChecks(player_1));
        infoField.setChipsInfo_2("" + board.getNumberOfChecks(player_2));
    }

    public synchronized boolean hasSaveMoves(){
        return locationsKeeper.hasSaveMoves();
    }

    public void moveBack(){
        board.setLocationsChecks(locationsKeeper.getLocationBoard());
        currentPlayer = locationsKeeper.getOwnerMove();
        locationsKeeper.delLastSaveMove();
        board.repaint();
    }

    public synchronized String getServerName() {
        return player_1.getName();
    }

    public synchronized String getClientName(){
        return player_2.getName();
    }

    public synchronized boolean isGameEnd(){
        return gameEnd;
    }

    public synchronized Coordinates getLastMoveCoordinates(){
        return lastMoveCoordinates;
    }

    public synchronized void setLastMoveCoordinateNull(){
        lastMoveCoordinates = null;
    }

    public synchronized void setServerName(String name){
        player_1.setName(name);
        infoField.setPlayerName_1(name);
        infoField.setMoveLabel(currentPlayer.getName());
        disconnectItem.setEnabled(true);
        repaint();
    }

    public synchronized void setClientName(String name){
        player_2.setName(name);
        infoField.setPlayerName_2(name);
        infoField.setMoveLabel(currentPlayer.getName());
        repaint();
    }
}

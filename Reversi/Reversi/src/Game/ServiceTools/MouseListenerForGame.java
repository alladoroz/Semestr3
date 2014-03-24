package Game.ServiceTools;

import Game.GameSession;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 29.02.12
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class MouseListenerForGame extends MouseAdapter {

    private GameSession gameSession;

    public MouseListenerForGame(GameSession gameSession){
        this.gameSession = gameSession;
    }

    @Override
    public void mouseMoved(MouseEvent e){
        gameSession.currentMouseCoordinate(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gameSession.humanMove(e.getX(), e.getY());
    }
}

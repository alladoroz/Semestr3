package Game.ServiceTools;

import Game.Players.Player;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 18.03.12
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class MovesKeeper {
    
    private ArrayList<Coordinates> moves;
    private ArrayList<Player> players;
    
    public MovesKeeper(){
        moves = new ArrayList<Coordinates>();
        players = new ArrayList<Player>();
    }

    public void saveMove(Coordinates move, Player player){
        moves.add(move);
        players.add(player);
    }

    public ArrayList<Player> getPayers(){
        return players;
    }

    public ArrayList<Coordinates> getCoordinates(){
        return moves;
    }

    public void delLastSavedMove(){
        players.remove(players.size() - 1);
        moves.remove(moves.size() - 1);
    }
}

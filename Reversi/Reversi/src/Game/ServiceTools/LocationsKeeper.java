package Game.ServiceTools;

import Game.Players.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 22.04.12
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public class LocationsKeeper {

    private ArrayList<LocationsChips> boards;
    private ArrayList<Player> ownerMove;

    public LocationsKeeper(){
        boards = new ArrayList<LocationsChips>();
        ownerMove = new ArrayList<Player>();
    }

    public void saveMove(LocationsChips board, Player owner){
        boards.add(board.clone());
        ownerMove.add(owner);
    }

    public LocationsChips getLocationBoard(){
        return boards.get(boards.size() - 1);
    }

    public Player getOwnerMove(){
        return ownerMove.get(ownerMove.size() - 1);
    }

    public void delLastSaveMove(){
        boards.remove(boards.size() - 1);
        ownerMove.remove(ownerMove.size() - 1);
    }

    public boolean hasSaveMoves(){
        return !boards.isEmpty();
    }
}

package Game.ServiceTools;

import Game.Players.Player;

import java.util.ArrayList;

/**
 * Location keeper
 */
public class LocationsKeeper {

    private ArrayList<LocationsChips> boards;
    private ArrayList<Player> ownerMove;

    /**
     * constructor
     */
    public LocationsKeeper() {
        boards = new ArrayList<LocationsChips>();
        ownerMove = new ArrayList<Player>();
    }

    /**
     * save move
     * @param board chips location
     * @param owner owner
     */
    public void saveMove(LocationsChips board, Player owner){
        boards.add(board.clone());
        ownerMove.add(owner);
    }

    /**
     * get location board
     * @return chips
     */
    public LocationsChips getLocationBoard(){
        return boards.get(boards.size() - 1);
    }

    /**
     * get owner moves
     * @return moves
     */
    public Player getOwnerMove(){
        return ownerMove.get(ownerMove.size() - 1);
    }

    /**
     * delete last move
     */
    public void delLastSaveMove(){
        boards.remove(boards.size() - 1);
        ownerMove.remove(ownerMove.size() - 1);
    }

    /**
     * check for saved moves
     * @return boolean info
     */
    public boolean hasSaveMoves(){
        return !boards.isEmpty();
    }
}

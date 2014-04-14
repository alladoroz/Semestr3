package Game.ServiceTools.Cells;

import Game.Players.Player;

/**
 * chip class
 */
public class Chip extends Cell {

    private static Chip chipPlayer_1;
    private static Chip chipPlayer_2;

    //Owner of cell
    private Player owner;

    /**
     * set chips
     * @param chip_1 first chip
     * @param chip_2 second chip
     */
    public static void setChips(Chip chip_1, Chip chip_2){
        chipPlayer_1 = chip_1;
        chipPlayer_2 = chip_2;
    }

    /**
     * constructor
     * @param owner owner
     */
    public Chip(Player owner){
        this.owner = owner;
        this.isWall = false;
    }

    /**
     * get owner
     * @return owner
     */
    public Player getOwner(){
        return owner;
    }

    /**
     * set owner
     * @param owner owner to set
     * @return chip
     */
    public static Chip setOwner(Player owner){
        if (owner == chipPlayer_1.getOwner()){
            return chipPlayer_1;
        }
        return chipPlayer_2;
    }

    /**
     * checks if the owner is the player
     * @param player
     * @return
     */
    public boolean isOwner(Player player){
        return owner == player;
    }
}

package Game.ServiceTools.Cells;

import Game.Players.Player;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 20.02.12
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class Chip extends Cell {

    private static Chip chipPlayer_1;
    private static Chip chipPlayer_2;

    //Owner of cell
    private Player owner;

    public static void setChips(Chip chip_1, Chip chip_2){
        chipPlayer_1 = chip_1;
        chipPlayer_2 = chip_2;
    }

    public Chip(Player owner){
        this.owner = owner;
        this.isWall = false;
    }

    public Player getOwner(){
        return owner;
    }

    public static Chip setOwner(Player owner){
        if (owner == chipPlayer_1.getOwner()){
            return chipPlayer_1;
        }
        return chipPlayer_2;
    }

    public boolean isOwner(Player player){
        return owner == player;
    }
}

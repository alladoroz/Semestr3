package Game.Players;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 07.03.12
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */
public class ExpertBot extends NormalBot {

    private final int exitConst = -100000;

    private ArrayList<Integer> basicPrices;

    public ExpertBot(Color color, String name, Player rival) {
        super(color, name, rival);
        depth = 8;
        bonus = 100;
    }
/*

    @Override
    public Coordinates botMove(LocationsChips locations) {
        ArrayList<Coordinates> border = locations.getBorder();
        ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
        for (int i = 0; i < border.size(); i++){
            if (locations.canPut(this, border.get(i))){
                moves.add(border.get(i));
            }
        }
        basicPrices = new ArrayList<Integer>();
        for (int i = 0; i < moves.size(); i++){
            basicPrices.add(recursion(this, locations, border.get(i), 0));
        }
    }


    private int recursion(Player player, LocationsChips locationsChips, Coordinates coordinate, int depth){
        if (this.depth == depth){
            return exitConst;
        }
        if (isAngle(coordinate, player, locationsChips)){

        }
        depth++;
        LocationsChips virtual = locationsChips.clone();
        virtual.move(player, coordinate);
        changePlayer(player, virtual);
        if (player == null){
            if (virtual.getNumberChecks(this) >= virtual.getNumberChecks(rival)){

            }
            else {

            }
            return exitConst;
        }
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        ArrayList<Coordinates> border = virtual.getBorder();
        for (int i = 0; i < border.size(); i++){
            if (virtual.canPut(player, border.get(i))){
                tmp.add(recursion(player, virtual, border.get(i), depth));
            }
        }


    }

    private int getNumberPermanentChips(Player player){

    }
*/


}

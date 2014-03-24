package Game.Players;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 07.03.12
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class HardBot extends NormalBot{

    public HardBot(Color color, String name, Player rival) {
        super(color, name, rival);
        depth = 6;
    }

/*    @Override
    protected int rec(Player player, LocationsChips locationsChips, Coordinates coordinate, int weight, int depth){
        if (this.depth == depth){
            return weight;
        }
        depth++;
        LocationsChips virtual = locationsChips.clone();
        virtual.move(player, coordinate);
        if (depth == 1 && isAngle(coordinate, player, virtual)){
            weight += bonus * 100;
        }
        player = changePlayer(player, virtual);
        if (player == null){
            if (virtual.getNumberChecks(this) >= virtual.getNumberChecks(rival)){
                weight += 100 * bonus * (this.depth - depth);
            }
            else {
                weight -= 110 * bonus * (this.depth - depth);
            }
            return weight;
        }
        ArrayList<Coordinates> border = virtual.getBorder();
        if (depth == 2 && player == rival){
            for (int i = 0; i < border.size() - 1; i++){
                if (isAngle(border.get(i), player, virtual)){
                    weight -= bonus * 100000;
                    return weight;
                }
            }
        }
        for (int i = 0; i < border.size(); i++){
            if (!virtual.canPut(player, border.get(i))){
                continue;
            }
            if (isAngle(border.get(i), player, virtual)){
                if (player == this){
                    weight += bonus * (this.depth - depth);
                }
                else {
                    weight -= bonus * (this.depth - depth);
                }
            }
            weight += rec(player, virtual, border.get(i), weight, depth);
        }
        return weight;
    }*/

}

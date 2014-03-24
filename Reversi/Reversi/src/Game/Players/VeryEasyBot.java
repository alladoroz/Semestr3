package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 01.03.12
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public class VeryEasyBot extends Bot{

    private Random random = new Random();

    public VeryEasyBot(Color color, String name, Player rival) {
        super(color, name, rival);
    }

    @Override
    public Coordinates botMove(LocationsChips locations) {
        ArrayList<Coordinates> border = locations.getBorder();
        int i = random.nextInt(border.size());
        Coordinates coordinates;
        do{
            if (i >= border.size() - 1){
                i = 0;
            }
            else {
                i++;
            }
            coordinates = border.get(i);
        } while (!locations.canPut(this, coordinates));
        return coordinates;
    }


}

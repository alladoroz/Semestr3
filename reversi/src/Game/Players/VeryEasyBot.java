//(EA) approved

package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * very easy bot
 */
public class VeryEasyBot extends Bot {

    private Random random = new Random();

    /**
     * constructor
     * @param color color
     * @param name name
     * @param rival rival
     */
    public VeryEasyBot(Color color, String name, Player rival) {
        super(color, name, rival);
    }

    /**
     * bot move
     * @param locations chips locations
     * @return move
     */
    @Override
    public Coordinates botMove(LocationsChips locations) {
        ArrayList<Coordinates> border = locations.getBorder();
        int i = random.nextInt(border.size());
        Coordinates coordinates;
        do {
            if (i >= border.size() - 1) {
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

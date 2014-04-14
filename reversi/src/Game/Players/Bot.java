
package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;

/**
 * bot class
 */
public abstract class Bot extends Player {

    protected Player rival;

    /**
     * constructor
     * @param color color
     * @param name name
     * @param rival rivel
     */
    public Bot(Color color, String name, Player rival) {
        super(color, name, true);
        this.rival = rival;
    }

    /**
     * checks if it is a bot
     * @return true
     */
    @Override
    public boolean isBot(){
        return true;
    }

    /**
     * move
     * @param locations chips locations
     * @return coordinates
     */
    public abstract Coordinates botMove(LocationsChips locations);
}

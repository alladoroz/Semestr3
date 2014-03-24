package Game.Players;

import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 01.03.12
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class Bot extends Player{

    protected Player rival;

    public Bot(Color color, String name, Player rival) {
        super(color, name, true);
        this.rival = rival;
    }

    @Override
    public boolean isBot(){
        return true;
    }

    public abstract Coordinates botMove(LocationsChips locations);
}

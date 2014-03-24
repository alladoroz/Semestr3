package Game.Players;

import Game.ServiceTools.Cells.Chip;
import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 03.03.12
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class EasyBot extends Bot {

    private LocationsChips locations;
    protected int bonus;
    protected Random random;
    protected ArrayList<Integer> numbersOfQuarters;

    public EasyBot(Color color, String name, Player rival){
        super(color, name, rival);
        random = new Random();
        bonus = 10;
    }

    @Override
    public Coordinates botMove(LocationsChips locations) {
        this.locations = locations;
        Coordinates coordinate = this.locations.getBorder().get(0);
        int indexPower = assessment(coordinate);
        ArrayList<Coordinates> coordinateses = new ArrayList<Coordinates>();
        coordinateses.add(coordinate);

        for (int i = 1; i < locations.getBorder().size(); i++){
            if (!locations.canPut(this, locations.getBorder().get(i))){
                continue;
            }
            int tmp = assessment(locations.getBorder().get(i));
            if (tmp > indexPower){
                indexPower = tmp;
                coordinate = locations.getBorder().get(i);
                coordinateses = new ArrayList<Coordinates>();
                coordinateses.add(coordinate);
            }
            if (tmp == indexPower){
                coordinateses.add(locations.getBorder().get(i));
            }
        }
        return coordinateses.get(random.nextInt(coordinateses.size()));
    }

    protected int assessment(Coordinates coordinates){
        LocationsChips virtual = locations.clone();
        virtual.move(this, coordinates);
        int result = virtual.getNumberChecks(this) - virtual.getNumberChecks(rival);
        if (isAngle(coordinates, this, locations)){
            result += bonus;
        }
        return result;
    }
    
    protected boolean isAngleWithQuarters(Coordinates coordinates, Player owner, LocationsChips locationsChips){
        numbersOfQuarters = new ArrayList<Integer>();
        boolean flag = false;
        if (findAngle(0, 0, coordinates.getX(), coordinates.getY(), coordinates, owner, locationsChips)){
            flag = true;
            numbersOfQuarters.add(0);
        }
        if (findAngle(0, coordinates.getY(), coordinates.getX(), 7, coordinates, owner, locationsChips)){
            flag = true;
            numbersOfQuarters.add(2);
        }
        if (findAngle(coordinates.getX(), 0, 7, coordinates.getY(), coordinates, owner, locationsChips)){
            flag = true;
            numbersOfQuarters.add(1);
        }
        if (findAngle(coordinates.getX(), coordinates.getY(), 7, 7, coordinates, owner, locationsChips)){
            flag = true;
            numbersOfQuarters.add(3);
        }
        return flag;
    }

    protected boolean isAngle(Coordinates coordinates, Player owner, LocationsChips locationsChips){
        LocationsChips virtual = locationsChips.clone();
        virtual.move(owner, coordinates);
        return findAngle(0, 0, coordinates.getX(), coordinates.getY(), coordinates, owner, virtual) ||
                findAngle(0, coordinates.getY(), coordinates.getX(), 7, coordinates, owner, virtual) ||
                findAngle(coordinates.getX(), 0, 7, coordinates.getY(), coordinates, owner, virtual) ||
                findAngle(coordinates.getX(), coordinates.getY(), 7, 7, coordinates, owner, virtual);
    }

    private boolean findAngle(int x1, int y1, int x2, int y2, Coordinates point, Player owner, LocationsChips locationsChips){
        Chip chip;
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                chip = locationsChips.getChip(new Coordinates(i, j));
                if (point.equals(new Coordinates(i, j))){
                    continue;
                }
                if (chip == null){
                    return false;
                }
                if (!chip.isOwner(owner)){
                    return false;
                }
            }
        }
        return true;
    }
}

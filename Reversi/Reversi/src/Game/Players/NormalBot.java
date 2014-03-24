package Game.Players;

import Game.ServiceTools.Cells.Chip;
import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 04.03.12
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class NormalBot extends EasyBot{

    protected final int endRecourseConst = -1000;
    //protected final int endRecourseConst = -1000;
    /*private final int[][] weights = {{1000, 1, 50, 50, 50, 50, 1, 1000},
                                     {1, 1, 30, 10, 10, 30, 1, 1},
                                     {50, 30, 30, 10, 10, 30, 30, 50},
                                     {50, 10, 10, 10, 10, 10, 10, 50},
                                     {50, 10, 10, 10, 10, 10, 10, 50},
                                     {50, 30, 30, 10, 10, 30, 30, 50},
                                     {1, 1, 30, 10, 10, 30, 1, 1},
                                     {1000, 1, 50, 50, 50, 50, 1, 1000}};*/
    private final int[][] weights =
            {{1000000, -1000, 40, 20, 20, 40, -1000, 1000000},
            {-1000, -1000, 30, 0, 0, 30, -1000, -1000},
            {40, 30, 30, 0, 0, 30, 30, 40},
            {20, 0, 0, 0, 0, 0, 0, 20},
            {20, 0, 0, 0, 0, 0, 0, 20},
            {40, 30, 30, 0, 0, 30, 30, 40},
            {-1000, -1000, 30, 0, 0, 30, -1000, -1000},
            {100000, -1000, 40, 20, 20, 40, -1000, 1000000}};

    private int lastDiscardedIndex;
    protected int depth;
    private ArrayList<Integer> discardedIndexes;

    public NormalBot(Color color, String name, Player rival) {
        super(color, name, rival);
        depth = 4;
    }

    @Override
    public Coordinates botMove(LocationsChips locationsChips){
        ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
        ArrayList<Integer> weights = new ArrayList<Integer>();
        ArrayList<Coordinates> border = locationsChips.getBorder();
        lastDiscardedIndex = -1;
        discardedIndexes = new ArrayList<Integer>();
        for (int i = 0; i < border.size(); i++){
            if (locationsChips.canPut(this, border.get(i))){
                moves.add(border.get(i));
                weights.add(rec(this, locationsChips, border.get(i), i, 0));
            }
        }
        return moves.get(findIndex(weights));
    }

    private int findIndex(ArrayList<Integer> arrayList){
        int tmp = arrayList.get(0);
        int index = 0;
        for (int i = 0; i < arrayList.size(); i++){
            if (discardedIndexes.contains(i)){
                continue;
            }
            if (tmp > arrayList.get(i)){
                tmp = arrayList.get(i);
                index = i;
            }
        }
        return index;
    }

    private int rec(Player player, LocationsChips locationsChips, Coordinates coordinates, int index, int depth){
        if (this.depth == depth){
            return assessment(locationsChips);
        }
        depth++;
        LocationsChips virtual = locationsChips.clone();
        ArrayList<Coordinates> border = virtual.getBorder();
        virtual.move(player, coordinates);
        player = changePlayer(player, virtual);
        int result = -1000000;
        if (player == this){
            for (int i = 0; i < border.size(); i++){
                if (virtual.canPut(player, border.get(i))){
                    int tmp = rec(player, virtual, border.get(i), index, depth);
                    if (result < tmp){
                        result = tmp;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < border.size(); i++){
                if (virtual.canPut(player, border.get(i))){
                    int tmp = rec(player, virtual, border.get(i), index, depth);
                    if (result > tmp){
                        result = tmp;
                    }
                }
            }
        }
        return result;
    }

    private int assessment(LocationsChips locationsChips){
        int myWeight = 0;
        int rivalWeight = 0;
        Chip chip;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                chip = locationsChips.getChip(i, j);
                if (chip == null){
                    continue;
                }
                if (chip.isOwner(this)){
                    myWeight += weights[i][j];
                    continue;
                }
                rivalWeight += weights[i][j];
            }
        }
        return rivalWeight - myWeight;
    }

    /*@Override
    public Coordinates botMove(LocationsChips locationsChips){
        ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
        ArrayList<Coordinates> border = locationsChips.getBorder();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        discardedIndexes = new ArrayList<Integer>();
        discardedIndexes.add(-1000);
        ArrayList<Coordinates> firstMove = new ArrayList<Coordinates>();
        for (int i = 0; i < border.size(); i++){
            if (locationsChips.canPut(this, border.get(i))){
                moves.add(border.get(i));
                if (isAngle(border.get(i), this, locationsChips)){
                    firstMove.add(border.get(i));
                }
            }
        }
        if (!firstMove.isEmpty()){
            return firstMove.get(random.nextInt(firstMove.size()));
        }
        for (int i = 0; i < moves.size(); i++){
            tmp.add(rec(this, locationsChips, moves.get(i), 0, i, 0));
        }
        return moves.get(findIndex(tmp));
    }


    private int findIndex(ArrayList<Integer> arrayList){
        int tmp = arrayList.get(0);
        int index = 0;
        for (int i = 0; i < arrayList.size(); i++){
            if (discardedIndexes.contains(i)){
                continue;
            }
            if (tmp > arrayList.get(i)){
                tmp = arrayList.get(i);
                index = i;
            }
        }
        return index;
    }

    private int findMin(ArrayList<Integer> arrayList){
        int tmp = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++){
            if (tmp > arrayList.get(i)){
                tmp = arrayList.get(i);
            }
        }
        return tmp;
    }

    private int findMax(ArrayList<Integer> arrayList){
        int tmp = arrayList.get(0);
        for (int i = 0; i < arrayList.size(); i++){
            if (tmp < arrayList.get(i)){
                tmp = arrayList.get(i);
            }
        }
        return tmp;
    }

    protected int rec(Player player, LocationsChips locationsChips, Coordinates coordinate, int bonus, int index, int depth){
        if (discardedIndexes.get(discardedIndexes.size() - 1) == index){
            return discardedConst;
        }
        if (this.depth == depth){
            return assessment(locationsChips) + bonus;
        }
        LocationsChips virtual = locationsChips.clone();
        virtual.move(player, coordinate);
        player = changePlayer(player, virtual);
        if (player == null){
            return assessment(virtual) + bonus;
        }
        depth++;
        ArrayList<Integer> minMax = new ArrayList<Integer>();
        ArrayList<Coordinates> border = virtual.getBorder();
        for (int i = 0; i < border.size(); i++){
            if (discardedIndexes.get(discardedIndexes.size() - 1) == index){
                return discardedConst;
            }
            if (virtual.canPut(player, border.get(i))){
                if (isAngle(border.get(i), player, virtual)){
                    if (player == this){
                        bonus += 20;
                    }
                    else {
                        discardedIndexes.add(index);
                        return discardedConst;
                    }
                }
                minMax.add(rec(player, virtual, border.get(i), bonus, index, depth));
            }
        }
        if (discardedIndexes.get(discardedIndexes.size() - 1) == index){
            return discardedConst;
        }
        if (player == this){
            return findMax(minMax);
        }
        else {
            return findMin(minMax);
        }
    }

    private int assessment(LocationsChips locationsChips){
        return locationsChips.getNumberChecks(this) - locationsChips.getNumberChecks(rival);
    }*/


    /*@Override
    public Coordinates botMove(LocationsChips locationsChips){
        ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
        ArrayList<Coordinates> border = locationsChips.getBorder();
        ArrayList<Coordinates> angleMoves = new ArrayList<Coordinates>();
        discardedIndexes = new ArrayList<Integer>();
        weights = new ArrayList<Integer>();
        for (int i = 0; i < border.size(); i++){
            if (locationsChips.canPut(this, border.get(i))){
                moves.add(border.get(i));
                weights.add(0);
                if (isAngle(border.get(i), this, locationsChips)){
                    angleMoves.add(border.get(i));
                }
            }
        }
        if (!angleMoves.isEmpty()){
            return findBeneficialCoordinate(angleMoves, locationsChips);
        }
        for (int i = 0; i < moves.size(); i++){
            rec(this, locationsChips, moves.get(i), i, 0);
        }
        return moves.get(findMax());
    }

    private Coordinates findBeneficialCoordinate(ArrayList<Coordinates> arrayList, LocationsChips locationsChips){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int maxValue = -1000;
        indexes.add(0);
        int tmp;
        for (int i = 0; i < arrayList.size(); i++){
            tmp = locationsChips.getNumberChecks(this) - locationsChips.getNumberChecks(rival);
            if (maxValue < tmp){
                if (maxValue == tmp){
                    indexes.add(i);
                }
                else {
                    indexes = new ArrayList<Integer>();
                    maxValue = tmp;
                    indexes.add(i);
                }
            }
        }
        return arrayList.get(indexes.get(random.nextInt(indexes.size())));
    }

    private int findMax(){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int max = weights.get(0);
        indexes.add(0);
        for (int i = 0; i < weights.size(); i++){
            if (max < weights.get(i)){
                if (max == weights.get(i)){
                    indexes.add(i);
                }
                else {
                    indexes = new ArrayList<Integer>();
                    max = weights.get(i);
                    indexes.add(i);
                }
            }
        }
        return indexes.get(random.nextInt(indexes.size()));
    }

    protected void rec(Player player, LocationsChips locationsChips, Coordinates coordinate, int index, int depth){
        if (this.depth == depth || discardedIndexes.get(discardedIndexes.size() - 1) == index){
            return;
        }
        depth++;
        if (isAngle(coordinate, player, locationsChips)){
            if (player == this){
                weights.set(index, weights.get(index) + 3 *(this.depth - depth));
            }
            else {
                discardedIndexes.add(index);
                weights.set(index, weights.get(index) - 100);
                return;
            }
        }
        LocationsChips virtual = locationsChips.clone();
        virtual.move(player, coordinate);
        player = changePlayer(player, virtual);
        if (player == null){
            return;
        }
        ArrayList<Coordinates> border = virtual.getBorder();
        for (int i = 0; i < border.size(); i++){
            if (virtual.canPut(player, border.get(i))){
                rec(player, virtual, border.get(i), index, depth);
                if (discardedIndexes.get(discardedIndexes.size() - 1) == index){
                    return;
                }
            }
        }
    }*/

    protected Player changePlayer(Player current, LocationsChips locationsChips){
        if (this == current){
            if (locationsChips.hasAnyMove(rival)){
                return rival;
            }
            if (locationsChips.hasAnyMove(this)){
                return this;
            }
            return null;
        }
        else {
            if (locationsChips.hasAnyMove(this)){
                return this;
            }
            if (locationsChips.hasAnyMove(rival)){
                return rival;
            }
            return null;
        }
    }

}

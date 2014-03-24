package Game.ServiceTools;

import Game.Players.Player;
import Game.ServiceTools.Cells.Chip;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 29.02.12
 * Time: 22:02
 * To change this template use File | Settings | File Templates.
 */
public class LocationsChips {

    //private static final int[][] directions = {{0, -1, -1, -1, 0, 1, 1, 1}, {-1, -1, 0, 1, 1, 1, 0, -1}};

    private Chip[][] chips;
    private ArrayList<Coordinates> border;

    private int count;

    public LocationsChips(){
        chips = new Chip[8][8];
        border = new ArrayList<Coordinates>();
    }

    public void setCheck(Player owner, Coordinates coordinates){
        chips[coordinates.getX()][coordinates.getY()] = Chip.setOwner(owner);
    }

    public void addBorder(Coordinates coordinates){
        border.add(coordinates);
    }

    //есть ли ходы
    public boolean hasAnyMove(Player player){
        if (border.isEmpty()){
            return false;
        }

        for (int i = 0; i < border.size(); i++){
            if (canPut(player, border.get(i))){
                return true;
            }
        }
        return false;
    }

    //могу ли поставить фишку
    public boolean canPut(Player player, Coordinates coordinates){
        for (int i = 0; i < border.size(); i++){
            if (coordinates.equals(border.get(i))){
                return findDirections(player, coordinates, false);
            }
        }
        return false;
    }

    // for bots
    public boolean tryMove(Player player, Coordinates coordinates){
        if (!findDirections(player, coordinates, true)){
            return false;
        }
        chips[coordinates.getX()] [coordinates.getY()] = Chip.setOwner(player);

        int row;
        int column;

        //del check from border
        for (int i =0; i < border.size(); i++){
            if (coordinates.equals(border.get(i))){
                border.remove(i);
                break;
            }
        }

        boolean flag;
        Coordinates borderCoordinates;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                row = coordinates.getX() + i;
                column = coordinates.getY() + j;
                flag = true;

                borderCoordinates = new Coordinates(row, column);

                if ((row >= 0 && row < 8 && column >= 0 && column < 8) && (chips[row] [column] == null)){
                    for (int k = 0; k < border.size(); k++){
                        if (borderCoordinates.equals(border.get(k))){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        border.add(borderCoordinates);
                    }
                }
            }
        }
        return true;
    }

    //ход, установка фишки
    public void move(Player player, Coordinates coordinates){
        if (!findDirections(player, coordinates, true)){
            return;
        }

        chips[coordinates.getX()] [coordinates.getY()] = Chip.setOwner(player);

        int row;
        int column;

        //del check from border
        for (int i =0; i < border.size(); i++){
            if (coordinates.equals(border.get(i))){
                border.remove(i);
                break;
            }
        }

        boolean flag;
        Coordinates borderCoordinates;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                row = coordinates.getX() + i;
                column = coordinates.getY() + j;
                flag = true;

                borderCoordinates = new Coordinates(row, column);

                if ((row >= 0 && row < 8 && column >= 0 && column < 8) && (chips[row] [column] == null)){
                    for (int k = 0; k < border.size(); k++){
                        if (borderCoordinates.equals(border.get(k))){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        border.add(borderCoordinates);
                    }
                }
            }
        }
    }

    private boolean findDirections(Player player, Coordinates coordinates, boolean doMove) {
        int row;
        int column;
        boolean isFind;
        boolean result = false;

        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                isFind = false;
                count = 0;
                row = coordinates.getX() + i;
                column = coordinates.getY() + j;

                while (row >= 0 && row < 8 && column >= 0 && column < 8){
                    if (chips[row][column] == null){
                        break;
                    }
                    if (chips[row][column].isOwner(player)){
                        if (isFind){
                            if (doMove){
                                doRevers(player, coordinates, i, j);
                            }
                            else {
                                return true;
                            }
                            result = true;
                        }
                        break;
                    }
                    else {
                        count++;
                        isFind = true;
                    }
                    row += i;
                    column += j;
                }
            }
        }
        return result;
    }

    private void doRevers(Player owner, Coordinates coordinates, int directionRow, int directionColumn){
        int row = coordinates.getX();
        int column = coordinates.getY();
        for (int k = 0; k < count; k++){
            row += directionRow;
            column += directionColumn;
            chips[row][column] = Chip.setOwner(owner);
        }
    }

    public Chip getChip(Coordinates coordinates){
        return chips[coordinates.getX()][coordinates.getY()];
    }

    public Chip getChip(int x, int y) {
        return chips[x][y];
    }

    public int getNumberChecks(Player owner){
        int number = 0;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (chips[i][j] != null){
                    if (chips[i][j].isOwner(owner)){
                        number++;
                    }
                }
            }
        }
        return number;
    }

    public ArrayList<Coordinates> getBorder(){
        return border;
    }

    @Override
    public LocationsChips clone(){
        LocationsChips clone = new LocationsChips();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (this.chips[i][j] != null){
                    clone.chips[i][j] = this.chips[i][j];
                }
            }
        }
        for (int k = 0; k < this.border.size(); k++){
            clone.border.add(this.border.get(k).clone());
        }
        return clone;
    }


}

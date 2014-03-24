package Game.GamePaintTools;

import Game.Players.Player;
import Game.ServiceTools.Cells.Chip;
import Game.ServiceTools.Coordinates;
import Game.ServiceTools.LocationsChips;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 20.02.12
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class Board extends JComponent {

    //finals
    private final int numberOfSquares = 8;

    //Game board with locationsChecks
    private LocationsChips locationsChecks;

    //Phantom check
    private Color phantomColor;
    private Coordinates phantomCoordinates;

    public LocationsChips getLocations(){
        return locationsChecks;
    }

    public void setLocationsChecks(LocationsChips locationsChecks){
        this.locationsChecks = locationsChecks;
    }

    public int getNumberOfChecks(Player player){
        return locationsChecks.getNumberChecks(player);
    }

    public boolean hasAnyMove(Player player){
        return locationsChecks.hasAnyMove(player);
    }

    public boolean canPut(Player player, Coordinates coordinates){

        return locationsChecks.canPut(player, coordinates);
    }

    public void move(Player player, Coordinates coordinates) {
        locationsChecks.move(player, coordinates);
    }

    public void initialFilling(Player player_1, Player player_2){
        locationsChecks = new LocationsChips();
        locationsChecks.setCheck(player_1, new Coordinates(3, 3));
        locationsChecks.setCheck(player_1, new Coordinates(4, 4));
        locationsChecks.setCheck(player_2, new Coordinates(4, 3));
        locationsChecks.setCheck(player_2, new Coordinates(3, 4));
        for (int i = 2; i < 6; i++){
            locationsChecks.addBorder(new Coordinates(i, 2));
            locationsChecks.addBorder(new Coordinates(i, 5));
        }
        for (int i = 3; i < 5; i++) {
            locationsChecks.addBorder(new Coordinates(2, i));
            locationsChecks.addBorder(new Coordinates(5, i));
        }
    }

    public void changePhantom(Color color, Coordinates coordinates){
        phantomColor = color;
        phantomCoordinates = coordinates;
    }

    public void delPhantom(){
        phantomColor = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        int sizeOfBoard = this.getHeight();
        int sizeOfSquare = sizeOfBoard / 8;

        //draw canvas
        g.setColor(Color.orange);
        g.fillRect(0, 0, sizeOfBoard, sizeOfBoard);

        //draw locationsChecks
        g.setColor(Color.BLACK);
        for (int i = 0; i <= sizeOfBoard; i += sizeOfSquare){
            g.drawLine(i, 0, i, sizeOfBoard);
            g.drawLine(0, i, sizeOfBoard, i);
        }

        Chip check;
        int sizeCell = this.getHeight() / numberOfSquares;
        int indentation = (int) (sizeCell * 0.1);
        int range = (int) (sizeCell * 0.8);

        //draw phantom check
        if (phantomColor != null){
            g.setColor(new Color(phantomColor.getRed(), phantomColor.getGreen(),
                    phantomColor.getBlue(), 130));

            g.fillOval(phantomCoordinates.getX() * sizeCell + indentation,
                    phantomCoordinates.getY() * sizeCell + indentation, range, range);
        }

        //draw locationsChecks
        for (int i = 0; i < numberOfSquares; i++){
            for (int j = 0; j < numberOfSquares; j++){
                check = locationsChecks.getChip(new Coordinates(i, j));
                if (check != null){
                    g.setColor(check.getOwner().getColorOfChecks());
                    g.fillOval(i * sizeCell + indentation, j * sizeCell + indentation, range, range);
                }
            }
        }
    }
}

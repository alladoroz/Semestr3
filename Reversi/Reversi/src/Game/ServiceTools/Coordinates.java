package Game.ServiceTools;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 29.02.12
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class Coordinates {

    private int X;
    private int Y;

    public Coordinates(int x, int y){
        setValue(x, y);
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public void setValue(int x, int y){
        X = x;
        Y = y;
    }

    public boolean equals(Coordinates coordinates){
        return (this.X == coordinates.X && this.Y == coordinates.Y);
    }

    @Override
    public Coordinates clone(){
        return new Coordinates(this.X, this.Y);
    }
}

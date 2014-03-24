package Game.ServiceTools;

/**
 * Coordinates
 */
public class Coordinates {

    private int X;
    private int Y;

    /**
     * set coordinates
     * @param x coordinate
     * @param y coordinate
     */
    public Coordinates(int x, int y){
        setValue(x, y);
    }

    /**
     * get x
     * @return x
     */
    public int getX(){
        return X;
    }

    /**
     * get y
     * @return y
     */
    public int getY(){
        return Y;
    }

    /**
     * set coordinates
     * @param x x
     * @param y y
     */
    public void setValue(int x, int y){
        X = x;
        Y = y;
    }

    /**
     * Check for equality
     * @param coordinates coordinates
     * @return boolean equality
     */
    public boolean equals(Coordinates coordinates){
        return (this.X == coordinates.X && this.Y == coordinates.Y);
    }

    /**
     * clone coordinates
     * @return same coordinates
     */
    @Override
    public Coordinates clone() {
        return new Coordinates(this.X, this.Y);
    }
}

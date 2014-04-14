
package Game.Players;

import java.awt.*;

/**
 * player class
 */
public class Player {

    protected Color colorOfChecks;
    private String name;
    private boolean isLocal;

    /**
     * constructor
     * @param color color
     * @param isLocal status
     */
    public Player(Color color, boolean isLocal) {
        this.colorOfChecks = color;
        this.isLocal = isLocal;
    }

    /**
     * constructor
     * @param color color
     * @param name name
     * @param isLocal status
     */
    public Player(Color color, String name, boolean isLocal) {
        this.colorOfChecks = color;
        this.name = name;
        this.isLocal = isLocal;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get color
     * @return color
     */
    public Color getColorOfChecks() {
        return colorOfChecks;
    }

    /**
     * checks if player is bot
     * @return false
     */
    public boolean isBot(){
        return false;
    }

    /**
     * checks if player is local
     * @return status
     */
    public boolean isLocal() {
        return isLocal;
    }
}

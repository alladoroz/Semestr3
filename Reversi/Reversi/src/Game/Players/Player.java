package Game.Players;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Andrey
 * Date: 27.02.12
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    protected Color colorOfChecks;
    private String name;
    private boolean isLocal;

    public Player(Color color, boolean isLocal){
        this.colorOfChecks = color;
        this.isLocal = isLocal;
    }

    public Player(Color color, String name, boolean isLocal){
        this.colorOfChecks = color;
        this.name = name;
        this.isLocal = isLocal;
    }

    public String getName(){
        return name;
    }

    public Color getColorOfChecks(){
        return colorOfChecks;
    }

    public boolean isBot(){
        return false;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean isLocal() {
        return isLocal;
    }
}

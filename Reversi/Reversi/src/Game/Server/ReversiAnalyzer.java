package Game.Server;

import Game.ServiceTools.Coordinates;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.05.12
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */
public class ReversiAnalyzer {
    static final int WAITING = 0;
    static final int BEGINNING_NAME = 1;
    static final int NAME = 2;
    static final int FINAL_NAME = 3;
    static final int BEGINNING_COORDINATES = 3;
    static final int COMMA = 4;
    static final int FINAL_COORDINATES = 5;
    static final int SECOND_COORDINATES = 6;

    static final Coordinates disconnect = new Coordinates(-1, -1);
    static final Coordinates spam = new Coordinates(-2, -2);

    static final int MAGIC_NUMBER = 48;

    private String playerName = "";
    private int x;
    private int y;

    public String getPlayerName(String info, boolean isServer){
        playerName = "";
        char symbol;
        int status = WAITING;
        if (info != null){
            for (int i = 0; i < info.length(); i++){
                symbol = info.charAt(i);
                if (status == WAITING && symbol == '#'){
                    status = BEGINNING_NAME;
                    continue;
                }
                if (status == BEGINNING_NAME && symbol == '#'){
                    String player = "anonymous ";
                    if (isServer){
                        player += "(White)";
                    }
                    else {
                        player += "(Black)";
                    }
                    return player;
                }
                if (status == NAME && symbol == '#'){
                    status = FINAL_NAME;
                    continue;
                }
                if (status == BEGINNING_NAME || status == NAME){
                    playerName += symbol;
                    status = NAME;
                    continue;
                }
                status = WAITING;
            }
        }
        return playerName;
    }

    public Coordinates getCoordinates(String info){
        Coordinates coordinate = null;
        int status = WAITING;
        char symbol;
        if (info != null){
            for (int i = 0; i < info.length(); i++){
                symbol = info.charAt(i);
                if (symbol == '?'){
                    return spam;
                }
                if (symbol == '!'){
                    return disconnect;
                }
                if (symbol == '<' && status == WAITING){
                    status = BEGINNING_COORDINATES;
                    continue;
                }
                if (status == BEGINNING_COORDINATES){
                    status = COMMA;
                    x = (int) symbol - MAGIC_NUMBER;
                    continue;
                }
                if (status == COMMA && symbol == ','){
                    status = SECOND_COORDINATES;
                    continue;
                }
                if (status == SECOND_COORDINATES){
                    y = (int) symbol - MAGIC_NUMBER;
                    status = FINAL_COORDINATES;
                    continue;
                }
                if (symbol == '>' && status == FINAL_COORDINATES){
                    coordinate = new Coordinates(x, y);
                }
                status = WAITING;
                x = y = -1;
            }
        }
        return coordinate;
    }
}

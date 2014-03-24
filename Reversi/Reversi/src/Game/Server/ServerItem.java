package Game.Server;

import Game.GameSession;
import Game.ServiceTools.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 12.05.12
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class ServerItem {

    protected PrintWriter out = null;
    protected BufferedReader in = null;
    protected ReversiAnalyzer analyzer = new ReversiAnalyzer();
    private String playerName;

    protected void startReadingWriting(GameSession gameSession) throws Exception{
        String inputLine;
        String outputLine;
        long time = System.currentTimeMillis();
        long spamTime = System.currentTimeMillis();
        long differenceTime;
        long differenceSpamTime;
        Coordinates tmp = null;
        while (!gameSession.isGameEnd()){
            if (Thread.currentThread().isInterrupted()){
                out.println("!");
                break;
            }
            differenceTime = System.currentTimeMillis() - time;
            differenceSpamTime = System.currentTimeMillis() - spamTime;
            if (differenceTime > 30000){
                throw new Exception();
            }
            if (differenceSpamTime > 15000){
                out.println("?");
                spamTime = System.currentTimeMillis();
                continue;
            }
            inputLine = null;
            if (in.ready()){
                inputLine = in.readLine();
            }
            if (inputLine != null && inputLine.length() > 0){
                tmp = analyzer.getCoordinates(inputLine);
            }
            if (tmp == ReversiAnalyzer.spam){
                time = System.currentTimeMillis();
                tmp = null;
                continue;
            }
            if (tmp == ReversiAnalyzer.disconnect){
                throw new Exception();
            }
            if (tmp != null){
                gameSession.doInternetMove(tmp);
                tmp = null;
                continue;
            }
            tmp = gameSession.getLastMoveCoordinates();
            if (tmp != null){
                outputLine = "<" + tmp.getX() + "," + tmp.getY() + ">";
                out.println(outputLine);
                gameSession.setLastMoveCoordinateNull();
            }
        }
        tmp = gameSession.getLastMoveCoordinates();
        if (tmp != null && !Thread.currentThread().isInterrupted()){
            outputLine = "<" + tmp.getX() + "," + tmp.getY() + ">";
            out.println(outputLine);
            gameSession.setLastMoveCoordinateNull();
        }
    }

    protected void readName(GameSession gameSession, boolean isServer) throws IOException {
        boolean end = false;
        boolean flag = true;
        String inputLine, outputLine, tmp = null;
        while (!end || flag){
            inputLine = null;
            if (in.ready()){
                inputLine = in.readLine();
            }
            if (inputLine != null && inputLine.length() > 0){
                tmp = analyzer.getPlayerName(inputLine, isServer);
            }
            if (tmp != null && tmp.length() > 0){
                playerName = tmp;
                end = true;
                tmp = null;
                continue;
            }
            if (isServer){
                outputLine = gameSession.getServerName();
            }
            else {
                outputLine = gameSession.getClientName();
            }
            if (flag){
                out.println("#" + outputLine + "#");
                flag = false;
            }
        }
    }

    protected String getPlayerName(){
        return playerName;
    }
}

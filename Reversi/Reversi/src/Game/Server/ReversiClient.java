package Game.Server;


import Game.GameSession;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.05.12
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
public class ReversiClient extends ServerItem{

    private GameSession gameSession;
    private JFrame owner;

    public ReversiClient(JFrame owner, GameSession gameSession){
        this.owner = owner;
        this.gameSession = gameSession;
    }

    public void startClient(String address) throws Exception {
        Socket clientSocket;
        try{
            clientSocket = new Socket(address, 8888);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch(UnknownHostException e){
            JOptionPane.showMessageDialog(owner, "Don't know about host.", "Error Message", 0);
            throw new Exception();
        } catch(IOException  e) {
            JOptionPane.showMessageDialog(owner, "Couldn't get I/O for the connection to: " + address,
                    "Error Message", 0);
            throw new Exception();
        }
        if (Thread.currentThread().isInterrupted()){
            out.close();
            in.close();
            clientSocket.close();
            return;
        }

        try{
            readName(gameSession, false);
            gameSession.setServerName(getPlayerName());
            startReadingWriting(gameSession);
        } catch (Exception e){
            JOptionPane.showMessageDialog(owner, "Disconnect.", "Message", 1);
            throw new Exception();
        } finally {
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}

package Game.Server;

import Game.GameSession;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 02.05.12
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class ReversiServer extends ServerItem {

    private GameSession gameSession;
    private JFrame owner;
    private volatile boolean accepting = false;

    public ReversiServer(JFrame owner, GameSession gameSession){
        this.owner = owner;
        this.gameSession = gameSession;
    }

    public void startServer() throws Exception {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8888);
        } catch(IOException e){
            JOptionPane.showMessageDialog(owner, "Could not listen on port: 8888.", "Error Message", 0);
            throw new Exception();
        }

        Socket clientSocket;
        accepting = true;
        try {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(owner, "Accept failed.", "Error Message", 0);
            serverSocket.close();
            throw new Exception();
        }
        accepting = false;
        if (Thread.currentThread().isInterrupted()){
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
            return;
        }

        try {
            readName(gameSession, true);
            gameSession.setClientName(getPlayerName());
            startReadingWriting(gameSession);
        } catch (Exception e){
            JOptionPane.showMessageDialog(owner, "Disconnect.", "Message", 1);
            throw new Exception();
        } finally {
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        }
    }

    public boolean getAcceptingStatus(){
        return accepting;
    }
}

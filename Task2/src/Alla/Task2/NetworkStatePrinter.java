package Alla.Task2;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Printing current state of the network
 */
public class NetworkStatePrinter {

    public NetworkStatePrinter() {
        update = new Updater();
    }

    public NetworkStatePrinter(Random testRandomizer) {
        update = new Updater(testRandomizer);
    }

    /**
     * Printing the current network state every second
     */
    public void print() {
        Timer timer = new Timer();
        timer.schedule(update, 0, 1000);
    }

    private TimerTask update;
}

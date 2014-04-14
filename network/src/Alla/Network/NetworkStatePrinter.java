package Alla.Network;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Printing current state of the network
 */
public class NetworkStatePrinter {
    private Updater update;

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
        update.currentStateString();
    }
}

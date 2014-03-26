package Alla.Network;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Printing the current network state
 */
public class Updater {
    private Random randomizer;

    private int[][] connections;
    private int[] systems;
    private boolean[] infections;

    private final int numberOfComputers = NetworkParameters.numberOfComputers();
    private final double probabilityLinux = NetworkParameters.probabilityLinux();
    private final double probabilityWindows = NetworkParameters.probabilityWindows();

    /**
     * Initiating the data fields on the creating of a new instance of Updater
     */
    public Updater(){
        this(new Random());
    }

    /**
     * Constructor for testing
     * @param testRandomizer object of the class Random
     */
    public Updater(Random testRandomizer) {
        try {
            initiateFields();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        randomizer = testRandomizer;
    }

    /**
     * Refreshing the system state and printing it
     */
    public void run() {
        updateNetworkState();
        printCurrentState();
    }

    /**
     * Current network state description
     * @return string of boolean values corresponding to the current network state
     */
    public String currentStateString() {
        String result = "";
        for (boolean isInfected: infections)
            if (isInfected)
                result += "true";
            else
                result += "false";
        return result;
    }

    /**
     * Refreshing the system state
     */
    private void updateNetworkState() {
        boolean[] updatedInfections = new boolean[numberOfComputers];
        for (int i = 0; i < numberOfComputers; ++i)
            updatedInfections[i] = infections[i];
        for (int i = 0; i < numberOfComputers; ++i) {
            if (infections[i]) {
                for (int j = 0; j < numberOfComputers; ++j) {
                    if (connections[i][j] == 1 && !infections[j]) {
                        double probability = getProbability(j);
                        double rand = randomizer.nextDouble();
                        if (rand <= probability) {
                            updatedInfections[j] = true;
                        }
                    }
                }
            }
        }
        infections = updatedInfections;
    }

    /**
     * Calculating the probability of getting infected for the concrete computer depending on the OS type
     * @param indexOfComputer - index of the computer in the network
     * @return probability of getting infected
     */
    private double getProbability(int indexOfComputer) {
        switch (systems[indexOfComputer]) {
            case 0: return probabilityLinux;
            case 1: return probabilityWindows;
            default: return probabilityLinux;
        }
    }

    /**
     * Prinng the current system time and the current network state
     */
    private void printCurrentState() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        for (int i = 0; i < numberOfComputers; ++i) {
            System.out.print("Computer " + (i + 1) + " is ");
            if (infections[i])
                System.out.print("INFECTED");
            else
                System.out.print("healthy");
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Initializing the data fields with the information from NetworkParameters class
     * @throws FileNotFoundException
     */
    private void initiateFields() throws FileNotFoundException {
        connections = NetworkParameters.connectivityMatrix();
        systems = NetworkParameters.osTypes();
        infections = NetworkParameters.infections();
    }
}

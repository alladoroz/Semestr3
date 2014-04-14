package Alla.Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Input method
 */
public class NetworkParameters {
    private static final int numberOfComputers = 5;
    private static final double probabilityLinux = 0.05;
    private static final double probabilityWindows = 0.08;

    /**
     * Getting the OS types
     * @param inputFile file with the information on the OS types of all the computers
     * @return array of 0s (corresponding to Linux) and 1s (corresponding to Windows)
     * @throws FileNotFoundException
     */
    public static int[] osTypes() throws FileNotFoundException {
        int[] type = new int[numberOfComputers];
        File inputFile = new File("inputOSTypes.txt");
        FileInputStream fis = new FileInputStream(inputFile);
        Scanner scanner = new Scanner(fis);
        for (int i = 0; i < numberOfComputers; ++i)
            type[i] = scanner.nextInt();
        return type;
    }

    /**
     * Getting the initial state of the network
     * @param inputFile file with the information which of computers are initially infected
     * @return array of boolean values meaning whether a computer is infected
     * @throws FileNotFoundException
     */
    public static boolean[] infections() throws FileNotFoundException {
        boolean[] result = new boolean[numberOfComputers];
        File inputFile = new File("inputInfections.txt");
        FileInputStream fis = new FileInputStream(inputFile);
        Scanner scanner = new Scanner(fis);
        for (int i = 0; i < numberOfComputers; ++i)
            result[i] = scanner.nextBoolean();
        return result;
    }

    /**
     * Getting the connectivity matrix
     * @param inputFile file with the connectivity matrix
     * @return connectivity matrix
     * @throws FileNotFoundException
     */
    public static int[][] connectivityMatrix() throws FileNotFoundException {
        int[][] matrix = new int[numberOfComputers][numberOfComputers];
        File inputFile = new File("inputConnections.txt");
        FileInputStream fis = new FileInputStream(inputFile);
        Scanner scanner = new Scanner(fis);
        for (int i = 0; i < numberOfComputers; ++i)
            for (int j = 0; j < numberOfComputers; ++j)
                matrix[i][j] = scanner.nextInt();
        return matrix;
    }

    /**
     * Getting number of computers
     * @return number of computers
     */
    public static int numberOfComputers() {
        return numberOfComputers;
    }

    /**
     * Getting probability of linux infection
     * @return probability for linux
     */
    public static double probabilityLinux() {
        return probabilityLinux;
    }

    /**
     * Getting probability of windows infection
     * @return probability for windows
     */
    public static double probabilityWindows() {
        return probabilityWindows;
    }
}

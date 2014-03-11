package Alla.graph;

import java.util.Scanner;

/**
 * Main class
 */
public class Main {
    /**
     * input method
     */
    private static Graph input () {
        Scanner sc = new Scanner(System.in);
        int numberOfVertices;
        int numberOfRobots;
        int[][] adjacentVertices;
        boolean[] robots;

        System.out.println("Please note that vertices are enumerated starting with 0");
        System.out.println("Enter number of vertices");
        numberOfVertices = sc.nextInt();
        adjacentVertices = new int[numberOfVertices][numberOfVertices];

        System.out.println("For each vertex enter number of adjacent vertices and enumerate them");
        for (int i = 0; i < numberOfVertices; i++) {
            adjacentVertices[i][0] = sc.nextInt();
            for (int j = 1; j <= adjacentVertices[i][0]; j++)
                adjacentVertices[i][j] = sc.nextInt();
        }

        System.out.println("Enter number of robots");
        numberOfRobots = sc.nextInt();
        robots = new boolean[numberOfVertices];
        System.out.println("Enter positions of each robot");
        for (int i = 0; i < numberOfRobots; i++)
            robots[sc.nextInt()] = true;
        System.out.println("Computing...");

        return new Graph(adjacentVertices, robots, numberOfRobots, numberOfVertices);
    }

    /**
     * Main method
     * @param args none
     */
    public static void main(String[] args) {
        Graph graph = input();

        if (graph.test())
            System.out.println("It's possible to destroy all robots");
        else
            System.out.println("It's impossible to destroy all robots");
    }
}

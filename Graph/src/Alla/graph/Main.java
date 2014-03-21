package Alla.graph;

import java.util.ArrayList;
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
        ArrayList<Integer>[] adjacentVertices;
        boolean[] robots;

        System.out.println("Please note that vertices are enumerated starting with 0");
        System.out.println("Enter number of vertices");
        numberOfVertices = sc.nextInt();
        adjacentVertices = new ArrayList[numberOfVertices];

        System.out.println("For each vertex enter number of adjacent vertices and enumerate them");
        for (int i = 0; i < numberOfVertices; i++) {
            int currentNumberOfVertices = sc.nextInt();
            adjacentVertices[i] = new ArrayList<Integer>();
            for (int j = 0; j < currentNumberOfVertices; j++)
                adjacentVertices[i].add(sc.nextInt());
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

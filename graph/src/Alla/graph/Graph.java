package Alla.graph;

import java.util.ArrayList;

/**
 * Graph class
 */
public class Graph {
    private int evenRobots = 0;
    private int unevenRobots = 0;

    private int[] was;
    private ArrayList<Integer>[] adjacentVertices;
    private boolean[] robots;
    private int numberOfRobots;

    /**
     * Constructor
     * @param adjacentVertices matrix of adjacent vertices
     * @param robots robots position
     * @param numberOfRobots number of robots
     * @param numberOfVertices number of vertices
     */
    public Graph(ArrayList<Integer>[] adjacentVertices, boolean[] robots, int numberOfRobots, int numberOfVertices) {
        this.was = new int[numberOfVertices];
        this.adjacentVertices = adjacentVertices;
        this.robots = robots;
        this.numberOfRobots = numberOfRobots;
    }

    /**
     * Solve the task
     * @return answer to the task
     */
    public boolean test() {
        return depthFirstSearch(0, 1) && numberOfRobots != 1 || evenRobots != 1 && unevenRobots != 1;
    }

    /**
     * Depth-first search
     * @param vertex current vertex
     * @param from previous vertex
     */
    private boolean depthFirstSearch(int vertex, int from) {
        was[vertex] = from == 2 ? 1 : 2;

        if (robots[vertex])
            if (was[vertex] == 2)
                evenRobots++;
            else
                unevenRobots++;

        for (int i = 0; i < adjacentVertices[vertex].size(); i++) {
            if (was[adjacentVertices[vertex].get(i)] == 0 && depthFirstSearch(adjacentVertices[vertex].get(i), from == 2 ? 1 : 2))
                return true;
            else if (was[adjacentVertices[vertex].get(i)] == was[vertex])
                return true;
        }

        return false;
    }
}
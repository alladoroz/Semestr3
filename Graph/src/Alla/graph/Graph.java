package Alla.graph;

/**
 * Graph class
 */
public class Graph {
    private boolean cycle = false;
    private int evenRobots = 0;
    private int unevenRobots = 0;

    private int[] was;
    private int[][] adjacentVertices;
    private boolean[] robots;
    private int numberOfRobots;

    /**
     * Constructor
     * @param adjacentVertices matrix of adjacent vertices
     * @param robots robots position
     * @param numberOfRobots number of robots
     * @param numberOfVertices number of vertices
     */
    public Graph(int[][] adjacentVertices, boolean[] robots, int numberOfRobots, int numberOfVertices) {
        this.was = new int[numberOfVertices];
        this.adjacentVertices = adjacentVertices;
        this.robots = robots;
        this.numberOfRobots = numberOfRobots;
    }

    /**
     * debug method
     */
    private void debug () {
        for (int i = 0; i < was.length; i++)
            System.out.println(i + " = " + was[i]);
        System.out.println("Cycle = " + cycle);
        System.out.println("Uneven robots = " + unevenRobots);
        System.out.println("Even robots = " + evenRobots);
    }

    /**
     * Solve the task
     * @return answer to the task
     */
    public boolean test() {
        this.depthFirstSearch(0, 1);
        //debug();
        return cycle && numberOfRobots % 2 == 0 || unevenRobots % 2 == 0 && evenRobots % 2 == 0;
    }

    /**
     * Depth-first search
     * @param vertex current vertex
     * @param from previous vertex
     */
    private void depthFirstSearch(int vertex, int from) {
        was[vertex] = from == 2 ? 1 : 2;

        if (robots[vertex])
            if (was[vertex] == 2)
                evenRobots++;
            else
                unevenRobots++;

        for (int i = 1; i <= adjacentVertices[vertex][0]; i++) {
            if (was[adjacentVertices[vertex][i]] == 0) {
                was[adjacentVertices[vertex][i]] = was[vertex] == 1 ? 2 : 1;
                depthFirstSearch(adjacentVertices[vertex][i], from == 2 ? 1 : 2);
            }
            else if (was[adjacentVertices[vertex][i]] == was[vertex])
                cycle = true;
        }
    }
}
package Alla.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {
    private Graph graph1;
    private Graph graph2;

    @Before
    public void setUp() throws Exception {
        int numberOfRobots1 = 2;
        int numberOfVertices1 = 4;
        int[][] adjacentVertices1 = {
                {2, 1, 2, 0},
                {2, 0, 3, 0},
                {2, 0, 3, 0},
                {2, 1, 2, 0}
        };
        boolean[] robots1 = {true, true, false, false};
        graph1 = new Graph(adjacentVertices1, robots1, numberOfRobots1, numberOfVertices1);

        int numberOfRobots2 = 2;
        int numberOfVertices2 = 6;
        int[][] adjacentVertices2 = {
                {1, 1, 0, 0, 0, 0},
                {3, 0, 2, 3, 0, 0},
                {2, 1, 3, 0, 0, 0},
                {3, 1, 2, 4, 0, 0},
                {2, 3, 5, 0, 0, 0},
                {1, 4, 0, 0, 0, 0}
        };
        boolean[] robots2 = {true, false, false, false, false, true};
        graph2 = new Graph(adjacentVertices2, robots2, numberOfRobots2, numberOfVertices2);
    }

    @Test
    public void testTest1() throws Exception {
        assertFalse(graph1.test());
    }

    @Test
    public void testTest2() throws Exception {
        assertTrue(graph2.test());
    }
}

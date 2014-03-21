package Alla.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {
    private Graph graph1;
    private Graph graph2;

    @Before
    public void setUp() throws Exception {
        int numberOfRobots1 = 2;
        int numberOfVertices1 = 4;
        ArrayList<Integer>[] adjacentVertices1 = new ArrayList[4];

        adjacentVertices1[0] = new ArrayList<Integer>();
        adjacentVertices1[0].add(1);
        adjacentVertices1[0].add(2);

        adjacentVertices1[1] = new ArrayList<Integer>();
        adjacentVertices1[1].add(0);
        adjacentVertices1[1].add(3);

        adjacentVertices1[2] = new ArrayList<Integer>();
        adjacentVertices1[2].add(0);
        adjacentVertices1[2].add(3);

        adjacentVertices1[3] = new ArrayList<Integer>();
        adjacentVertices1[3].add(1);
        adjacentVertices1[3].add(2);

        boolean[] robots1 = {true, true, false, false};
        graph1 = new Graph(adjacentVertices1, robots1, numberOfRobots1, numberOfVertices1);

        int numberOfRobots2 = 2;
        int numberOfVertices2 = 6;
        ArrayList<Integer>[] adjacentVertices2 = new ArrayList[6];

        adjacentVertices2[0] = new ArrayList<Integer>();
        adjacentVertices2[0].add(1);

        adjacentVertices2[1] = new ArrayList<Integer>();
        adjacentVertices2[1].add(0);
        adjacentVertices2[1].add(2);
        adjacentVertices2[1].add(3);

        adjacentVertices2[2] = new ArrayList<Integer>();
        adjacentVertices2[2].add(1);
        adjacentVertices2[2].add(3);

        adjacentVertices2[3] = new ArrayList<Integer>();
        adjacentVertices2[3].add(1);
        adjacentVertices2[3].add(2);
        adjacentVertices2[3].add(4);

        adjacentVertices2[4] = new ArrayList<Integer>();
        adjacentVertices2[4].add(3);
        adjacentVertices2[4].add(5);

        adjacentVertices2[5] = new ArrayList<Integer>();
        adjacentVertices2[5].add(4);

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

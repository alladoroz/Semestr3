package Alla.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {
    private Graph graph1;
    private Graph graph2;
    private Graph square;
    private Graph triangle;

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


        int numberOfRobots3 = 2;
        int numberOfVertices3 = 4;
        ArrayList<Integer>[] adjacentVertices3 = new ArrayList[4];

        adjacentVertices3[0] = new ArrayList<Integer>();
        adjacentVertices3[0].add(1);
        adjacentVertices3[0].add(3);

        adjacentVertices3[1] = new ArrayList<Integer>();
        adjacentVertices3[1].add(0);
        adjacentVertices3[1].add(2);

        adjacentVertices3[2] = new ArrayList<Integer>();
        adjacentVertices3[2].add(1);
        adjacentVertices3[2].add(3);

        adjacentVertices3[3] = new ArrayList<Integer>();
        adjacentVertices3[3].add(2);
        adjacentVertices3[3].add(0);

        boolean[] robots3 = {true, false, true, false};
        square = new Graph(adjacentVertices3, robots3, numberOfRobots3, numberOfVertices3);

        int numberOfRobots4 = 2;
        int numberOfVertices4 = 3;
        ArrayList<Integer>[] adjacentVertices4 = new ArrayList[3];

        adjacentVertices4[0] = new ArrayList<Integer>();
        adjacentVertices4[0].add(1);
        adjacentVertices4[0].add(2);

        adjacentVertices4[1] = new ArrayList<Integer>();
        adjacentVertices4[1].add(0);
        adjacentVertices4[1].add(2);

        adjacentVertices4[2] = new ArrayList<Integer>();
        adjacentVertices4[2].add(1);
        adjacentVertices4[2].add(0);

        boolean[] robots4 = {true, true, true};
        triangle = new Graph(adjacentVertices4, robots4, numberOfRobots4, numberOfVertices4);


    }


    @Test
    public void testTest1() throws Exception {
        assertFalse(graph1.test());
    }

    @Test
    public void testTest2() throws Exception {
        assertTrue(graph2.test());
    }

    @Test
    public void testTest3() throws Exception {
        assertTrue(square.test());
    }

    @Test
    public void testTest4() throws Exception {
        assertTrue(triangle.test());
    }
}

package Alla.Network;

import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test of NetworkParameters
 */
public class NetworkParametersTest {
    /**
     * Test of osTypes method
     */
    @Test
    public void testOsTypes() throws FileNotFoundException {
        int[] expResult = {0, 0, 1, 1, 1};
        int[] result = NetworkParameters.osTypes();
        for (int i = 0; i < NetworkParameters.numberOfComputers(); ++i)
            assertEquals(expResult[i], result[i]);
    }

    /**
     * Test of infections method, of class NetworkParameters.
     */
    @Test
    public void testInfections() throws FileNotFoundException {
        boolean[] expResult = {false, false, true, false, false};
        boolean[] result = NetworkParameters.infections();
        for (int i = 0; i < NetworkParameters.numberOfComputers(); ++i)
            assertEquals(expResult[i], result[i]);
    }

    /**
     * Test of connectivityMatrix method, of class NetworkParameters.
     */
    @Test
    public void testConnectivityMatrix() throws FileNotFoundException {
        int[][] expResult = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0}
        };
        int[][] result = NetworkParameters.connectivityMatrix();
        for (int i = 0; i < NetworkParameters.numberOfComputers(); ++i)
            for (int j = 0; j < NetworkParameters.numberOfComputers(); ++j)
                assertEquals(expResult[i][j], result[i][j]);
    }

    /**
     * Test of numberOfComputers method, of class NetworkParameters.
     */
    @Test
    public void testNumberOfComputers() {
        assertEquals(5, NetworkParameters.numberOfComputers());
    }

    /**
     * Test of probabilityLinux method, of class NetworkParameters.
     */
    @Test
    public void testProbabilityLinux() {
        assertEquals(0.05, NetworkParameters.probabilityLinux(), 0.000000001);
    }

    /**
     * Test of probabilityWindows method, of class NetworkParameters.
     */
    @Test
    public void testProbabilityWindows() {
        assertEquals(0.08, NetworkParameters.probabilityWindows(), 0.000000001);
    }

}

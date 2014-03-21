package Alla.Task2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * NetworkStatePrinter test
 */
public class NetworkStatePrinterTest {

    public NetworkStatePrinterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * print test
     */
    @Test
    public void testPrint() {
        NetworkStatePrinter printer = new NetworkStatePrinter();
        printer.print();
    }

}

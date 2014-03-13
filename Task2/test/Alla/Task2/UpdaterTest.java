package Alla.Task2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * test of updater
 */
public class UpdaterTest {
    public UpdaterTest() {
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
     * Test of run
     */
    @Test
    public void testRun() {
        Updater updater = new Updater(new TestRandom());
        assertEquals("falsefalsetruefalsefalse", updater.currentStateString());
        updater.run();
        assertEquals("falsetruetruetruefalse", updater.currentStateString());
        updater.run();
        assertEquals("truetruetruetruetrue", updater.currentStateString());
    }
}
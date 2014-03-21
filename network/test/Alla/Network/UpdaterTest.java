package Alla.Network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * test of updater
 */
public class UpdaterTest {
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

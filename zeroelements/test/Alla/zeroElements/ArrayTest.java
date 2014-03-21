package Alla.zeroElements;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayTest {
    private Array myTest = new Array();
    private int ans;

    @Before
    public void setUp() throws Exception {
        int[] ints = {1, 15, 0, -3, 0, 4, 9, 11, 17, 10};
        ans = myTest.calculate(ints);
    }

    @Test
    public void testCalculate() throws Exception {
        assertEquals(ans, 2);
    }
}

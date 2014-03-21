import Alla.Task4.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alla on 12.03.14.
 */
public class ArrayTest {
    private Array myTest = new Array(10);
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

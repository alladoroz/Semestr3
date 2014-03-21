package Alla.BinTree;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of Tree class implementing binary tree with an iterator
 */
public class TreeTest {
    private Tree<String> instance = new Tree<String>();

    /**
     * Adding test
     */
    @Test
    public void testAdd() {
        instance.add("add");
        assertTrue(instance.contains("add"));
    }

    /**
     * Removing test
     */
    @Test
    public void testRemove() {
        instance.add("remove");
        instance.remove("remove");
        assertFalse(instance.contains("remove"));

    }

    /**
     * Test of contains method
     */
    @Test
    public void testContains() {
        assertFalse(instance.contains("smth"));
    }

    /**
     * Test of tree iterator, hasNext method
     */
    @Test
    public void testIteratorHasNext() {
        Iterator<String> it = instance.iterator();
        assertFalse(it.hasNext());
    }

    /**
     * Test of tree iterator
     */
    @Test
    public void testIterator() {
        instance.add("abc");
        instance.add("cde");
        instance.add("aaa");
        instance.add("bcd");
        Iterator<String> it = instance.iterator();
        String string = new String();
        while (it.hasNext())
            string += it.next();
        assertEquals("abcaaacdebcd", string);
    }

    /**
     * Test of tree iterator, foreach syntax
     */
    @Test
    public void testIteratorForeach() {
        instance.add("abc");
        instance.add("cde");
        instance.add("aaa");
        instance.add("bcd");
        String string = new String();
        for (String element : instance) {
            element += "$";
            string += element;
        }
        assertEquals("abc$aaa$cde$bcd$", string);
    }
}

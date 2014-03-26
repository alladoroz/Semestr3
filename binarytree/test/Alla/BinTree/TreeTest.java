package Alla.BinTree;


import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TreeTest {
    private Tree<String> instance = new Tree<String>();

    @Test
    public void testAdd() {
        instance.add("add");
        assertTrue(instance.contains("add"));
    }

    @Test
    public void testRemove() {
        instance.add("remove");
        instance.add("ololo");
        instance.remove("remove");
        assertTrue(instance.contains("ololo"));
        assertFalse(instance.contains("remove"));
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

package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    @BeforeEach
    void setup() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);
    }

    /* clear() */
    @Test
    void testClearNormal() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    void testClearEdgeAlreadyEmpty() {
        emptySet.clear();
        assertTrue(emptySet.isEmpty());
    }

    /* length() */
    @Test
    void testLengthNormal() {
        assertEquals(3, set1.length());
    }

    @Test
    void testLengthEmptySet() {
        assertEquals(0, emptySet.length());
    }

    /* equals() */
    @Test
    void testEqualsSameElementsDifferentOrder() {
        IntegerSet temp = new IntegerSet();
        temp.add(3);
        temp.add(1);
        temp.add(2);
        assertTrue(set1.equals(temp));
    }

    @Test
    void testEqualsDifferentSizes() {
        IntegerSet temp = new IntegerSet();
        temp.add(1);
        temp.add(2);
        assertFalse(set1.equals(temp));
    }

    /* contains() */
    @Test
    void testContainsPresent() {
        assertTrue(set1.contains(1));
    }

    @Test
    void testContainsAbsent() {
        assertFalse(set1.contains(10));
    }

    /* largest() */
    @Test
    void testLargestNormal() {
        assertEquals(3, set1.largest());
    }

    @Test
    void testLargestExceptionOnEmpty() {
        assertThrows(RuntimeException.class, () -> emptySet.largest());
    }

    /* smallest() */
    @Test
    void testSmallestNormal() {
        assertEquals(1, set1.smallest());
    }

    @Test
    void testSmallestExceptionOnEmpty() {
        assertThrows(RuntimeException.class, () -> emptySet.smallest());
    }

    /* add() */
    @Test
    void testAddNormal() {
        set1.add(5);
        assertTrue(set1.contains(5));
    }

    @Test
    void testAddDuplicate() {
        set1.add(2);
        assertEquals(3, set1.length());
    }

    /* remove() */
    @Test
    void testRemoveNormal() {
        set1.remove(2);
        assertFalse(set1.contains(2));
    }

    @Test
    void testRemoveValueNotPresent() {
        set1.remove(10);
        assertEquals(3, set1.length());
    }

    /* union() */
    @Test
    void testUnionNormal() {
        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2, 3, 4]", result.toString());
    }

    @Test
    void testUnionWithEmptySet() {
        IntegerSet result = set1.union(emptySet);
        assertEquals("[1, 2, 3]", result.toString());
    }

    /* intersect() */
    @Test
    void testIntersectNormal() {
        IntegerSet result = set1.intersect(set2);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    void testIntersectNoCommonElements() {
        IntegerSet a = new IntegerSet();
        a.add(1);
        IntegerSet b = new IntegerSet();
        b.add(2);
        assertEquals("[]", a.intersect(b).toString());
    }

    /* diff() */
    @Test
    void testDiffNormal() {
        IntegerSet result = set1.diff(set2);
        assertEquals("[1]", result.toString());
    }

    @Test
    void testDiffIdenticalSets() {
        assertEquals("[]", set1.diff(set1).toString());
    }

    /* complement() */
    @Test
    void testComplementNormal() {
        IntegerSet result = set1.complement(set2);
        assertEquals("[4]", result.toString());
    }

    @Test
    void testComplementDisjointSets() {
        IntegerSet a = new IntegerSet();
        a.add(1);
        IntegerSet b = new IntegerSet();
        b.add(2);
        assertEquals("[2]", a.complement(b).toString());
    }

    /* isEmpty() */
    @Test
    void testIsEmptyTrue() {
        assertTrue(emptySet.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        assertFalse(set1.isEmpty());
    }

    /* toString() */
    @Test
    void testToStringNormal() {
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    void testToStringEmptySet() {
        assertEquals("[]", emptySet.toString());
    }
}
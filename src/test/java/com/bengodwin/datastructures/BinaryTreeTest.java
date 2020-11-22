package com.bengodwin.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    static BinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();

        assertTrue(tree.isEmpty());

        tree.add(5);
        assertEquals(1, tree.size());

        assertFalse(tree.isEmpty());

        tree.add(5);
        assertEquals(1, tree.size());

        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(8);
        tree.add(10);
        tree.add(9);

        assertEquals(6, tree.size());

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(9));

        assertFalse(tree.contains(1));
        assertFalse(tree.contains(3));
        assertFalse(tree.contains(6));
        assertFalse(tree.contains(7));

        assertEquals(5, tree.get(5));
        assertEquals(2, tree.get(2));
        assertEquals(8, tree.get(8));
        assertEquals(4, tree.get(4));
        assertEquals(10, tree.get(10));
        assertEquals(9, tree.get(9));

        assertEquals(null, tree.get(1));
        assertEquals(null, tree.get(3));
        assertEquals(null, tree.get(6));
        assertEquals(null, tree.get(7));

        assertFalse(tree.isEmpty());
    }

    @Test
    void clear() {
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertFalse(tree.contains(9));
    }

    @Test
    void remove() {
        tree.add(6);
        tree.add(7);
        tree.add(1);
        tree.add(3);

        assertEquals(10, tree.size());

        tree.remove(8);
        assertEquals(9, tree.size());
        assertTrue(tree.contains(9));

        tree.remove(5);
        assertEquals(8, tree.size());
        assertTrue(tree.contains(7));

        Integer[] array = tree.toArray(Integer.class);
        assertEquals(8, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
        assertEquals(4, array[3]);
        assertEquals(6, array[4]);
        assertEquals(7, array[5]);
        assertEquals(9, array[6]);
        assertEquals(10, array[7]);
    }

    @Test
    void toArray() {
        Integer[] array = tree.toArray(Integer.class);
        assertEquals(6, array.length);

        assertEquals(2, array[0]);
        assertEquals(4, array[1]);
        assertEquals(5, array[2]);
        assertEquals(8, array[3]);
        assertEquals(9, array[4]);
        assertEquals(10, array[5]);
    }
}
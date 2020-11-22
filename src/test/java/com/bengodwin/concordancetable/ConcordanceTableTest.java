package com.bengodwin.concordancetable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ConcordanceTableTest {

    private ConcordanceTable table;

    @BeforeEach
    void setUp() {
        table = new ConcordanceTable();
    }

    @Test
    void add() {
        table.add("  Apple ");
        table.add(" m1");
        table.add("1m ");
        table.add("1-M");
        table.add(" M--1");
        table.add("1-2");

        assertEquals(5, table.size());

        ConcordanceDataPair pair = table.get(" Apple");
        assertNotNull(pair);
        assertEquals(1, pair.getOccurrences());

        ConcordanceDataPair[] words = table.toArray();
        List<String> strings = Arrays.stream(words).map(x -> x.getWord()).collect(Collectors.toList());

        assertTrue(strings.contains("APPLE"));
        assertTrue(strings.contains("M1"));
        assertTrue(strings.contains("1M"));
        assertTrue(strings.contains("1-M"));
        assertTrue(strings.contains("M--1"));
    }

    @Test
    void addLine() {
        String testLine = "John's friend drove 80 miles per hour (wow!) to exit 11. #insane!!";

        table.addLine(testLine);

        assertEquals(10, table.size());

        ConcordanceDataPair[] words = table.toArray();
        List<String> strings = Arrays.stream(words).map(x -> x.getWord()).collect(Collectors.toList());

        assertTrue(strings.contains("JOHN'S"));
        assertTrue(strings.contains("FRIEND"));
        assertTrue(strings.contains("DROVE"));
        assertTrue(strings.contains("MILES"));
        assertTrue(strings.contains("PER"));
        assertTrue(strings.contains("HOUR"));
        assertTrue(strings.contains("WOW"));
        assertTrue(strings.contains("TO"));
        assertTrue(strings.contains("EXIT"));
        assertTrue(strings.contains("INSANE"));
    }

    @Test
    void toArray() {
        table.addLine("C A B");
        assertEquals(3, table.size());
        assertEquals(3, table.getTotalWords());

        ConcordanceDataPair[] pairs = table.toArray();

        ConcordanceDataPair pair = table.get("a");
        assertNotNull(pair);
        assertEquals(1, pair.getOccurrences());

        assertEquals(3, pairs.length);
        assertEquals("A", pairs[0].getWord());
        assertEquals("B", pairs[1].getWord());
        assertEquals("C", pairs[2].getWord());
        assertEquals(1, pairs[0].getOccurrences());
        assertEquals(1, pairs[1].getOccurrences());
        assertEquals(1, pairs[2].getOccurrences());

        table.add("C");
        table.add("A");
        assertEquals(3, table.size());
        assertEquals(5, table.getTotalWords());

        pair = table.get("a");
        assertNotNull(pair);
        assertEquals(2, pair.getOccurrences());

        pairs = table.toArray();
        assertEquals(3, pairs.length);
        assertEquals("A", pairs[0].getWord());
        assertEquals("B", pairs[1].getWord());
        assertEquals("C", pairs[2].getWord());
        assertEquals(2, pairs[0].getOccurrences());
        assertEquals(1, pairs[1].getOccurrences());
        assertEquals(2, pairs[2].getOccurrences());
    }

}
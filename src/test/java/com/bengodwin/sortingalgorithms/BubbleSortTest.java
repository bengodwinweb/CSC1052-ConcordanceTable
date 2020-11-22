package com.bengodwin.sortingalgorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BubbleSortTest {

    static BubbleSort<Integer> sorter;

    @BeforeEach
    void setUp() {
        sorter = new BubbleSort<>();
    }

    @Test
    void sort() {
        SortingAlgorithmTest tester = new SortingAlgorithmTest(sorter);
        tester.testSort(5_000, 1_000_000);
    }

}
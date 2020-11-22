package com.bengodwin.sortingalgorithms;

import org.junit.jupiter.api.Test;

class QuickSortTest {

    @Test
    void sort() {
        QuickSort<Integer> sorter = new QuickSort<>();

        SortingAlgorithmTest test = new SortingAlgorithmTest(sorter);
        test.testSort(100_000, 1_000_000);
    }
}
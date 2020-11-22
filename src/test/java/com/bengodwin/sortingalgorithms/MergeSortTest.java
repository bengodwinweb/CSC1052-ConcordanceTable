package com.bengodwin.sortingalgorithms;

import org.junit.jupiter.api.Test;

class MergeSortTest {

    @Test
    void sort() {
        MergeSort<Integer> sorter = new MergeSort<>();

        SortingAlgorithmTest test = new SortingAlgorithmTest(sorter);
        test.testSort(100_000, 1_000_000);
    }
}
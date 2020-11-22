package com.bengodwin.sortingalgorithms;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingAlgorithmTest {

    ISortingAlgorithm<Integer> sorter;

    public SortingAlgorithmTest(ISortingAlgorithm<Integer> sorter) {
        this.sorter = sorter;
    }

    void testSort(int collectionSize, int maxValue) {
        var data = SortingTestUtil.makeData(collectionSize, maxValue);

//        System.out.println("Unsorted:");
//        SortingTestUtil.printArray(data);

        LocalDateTime start = LocalDateTime.now();
        data = sorter.sort(data, Integer::compareTo);
        LocalDateTime end = LocalDateTime.now();

//        System.out.println("Sorted:");
//        SortingTestUtil.printArray(data);

        Duration d = Duration.between(start, end);
        System.out.printf("Sort Time: %d ms\n", d.toMillis());


        for (int i = 0; i < data.length - 1; i++) {
            assertTrue(data[i] <= data[i + 1]);
        }

    }
}

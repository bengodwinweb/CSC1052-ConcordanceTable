/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
BubbleSort.java

Info: Bubble Sort implementation that can be applied to an array where the data type implements Comparable
*/

package com.bengodwin.sortingalgorithms;

import java.util.Comparator;

public class BubbleSort<T> implements ISortingAlgorithm<T> {

    /**
     * Sorts the data using an implementation of Bubble Sort. Modifies the array directly.
     */
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        return doSort(array, comparator);
    }

    private T[] doSort(T[] array, Comparator comparator) {
        /*
        Recursive - moves through the array comparing adjacent items. If the adjacent items are out of order
        (left is larger than right), flips the two elements.
        If any elements were flipped, calls itself again recursively.
        If no flips were made, returns the sorted array.
         */

        boolean preSorted = true;

        for (int i = 0; i < array.length - 1; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                T temp = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temp;

                preSorted = false;
            }
        }

        if (preSorted) {
            return array;
        }

        return doSort(array, comparator);
    }
}

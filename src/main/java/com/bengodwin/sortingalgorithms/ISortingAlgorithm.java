/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
ISortingAlgorithm.java

Info: Interface for a sorting algorithm
*/

package com.bengodwin.sortingalgorithms;

import java.util.Comparator;

public interface ISortingAlgorithm<T> {

    /**
     * Returns a sorted array from the data using the comparator.
     * May do the sort in place or may return a copy depending on the implementation
     * @param array data to sort (this colleciton may be modified directly)
     * @param comparator comparison used in sorting
     * @return sorted array
     */
    T[] sort(T[] array, Comparator<T> comparator);
}

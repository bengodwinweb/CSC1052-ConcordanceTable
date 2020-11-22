/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
MergeSort.java

Info: Merge Sort implementation that can be applied to an array where the data type implements Comparable
*/

package com.bengodwin.sortingalgorithms;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<T> implements ISortingAlgorithm<T> {

    /**
     * Returns a sorted copy of the array, does not modify the array directly
     */
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        T[] a = doSort(array, comparator);
        return a;
    }

    private T[] doSort(T[] array, Comparator<T> comparator) {
        /*
        Recursively split the array down the middle on both sides until you have arrays of length 1.
        Merge each of these back together one at a time. Each array that is being merged will be sorted -
        so the comparator always just need to pick the first element from either the left or right side,
        whichever is smallest.
         */

        if (array.length > 1) {
            T[] left = doSort(Arrays.copyOfRange(array, 0, array.length / 2), comparator); // sort the left
            T[] right = doSort(Arrays.copyOfRange(array, array.length / 2, array.length), comparator); // sort the right
            return merge(left, right, comparator); // merge them back together
        } else {
            return array; // base case, no sorting to be done
        }
    }

    private T[] merge(T[] left, T[] right, Comparator<T> comparator) {
        T[] merged = Arrays.copyOf(left, left.length + right.length);

        int i = 0; // index of the next element in the left array
        int j = 0; // index of the next element in the right array
        int k = 0; // index of the next element in the merged array

        // go one at a time adding the smallest from the left or right until either the left or the right has all been added
        while (i < left.length && j < right.length) {
            int comparison = comparator.compare(left[i], right[j]);
            if (comparison <= 0) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        // whichever side still has elements to add, add them all in order
        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }

        return merged;
    }
}

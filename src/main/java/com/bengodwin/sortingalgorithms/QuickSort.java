/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
MergeSort.java

Info: Quick Sort implementation that can be applied to an array where the data type implements Comparable
*/

package com.bengodwin.sortingalgorithms;

import java.util.Comparator;

public class QuickSort<T> implements ISortingAlgorithm<T> {

    /**
     * Returns the array with all elements sorted, modifies the array directly
     */
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        return doSort(array, 0, array.length - 2, array.length - 1, comparator);
    }

    private T[] doSort(T[] array, int left, int right, int pivot, Comparator<T> comparator) {
        /*
        Recursive. The pivot will be the last element in the section of the array we are currently sorting.
        We start at the left looking for elements larger than the pivot, and the right looking for elements smaller than the pivot.
        Anytime that we are able to find one of each at the left and right pointer, we swap them in the array.

        Once the pointers meet, we swap the pivot with the element at left if the element at left is larger.

        We then recurse down sorting from the beginning of the array to left, and from left to the end.
         */

        if (left > right) { // array length is < 2, nothing to swap
            return array;
        } else if (left == right) { // array length is 2. See if we should swap the elements then return
            if (comparator.compare(array[left], array[pivot]) > 0) {
                swap(array, left, pivot);
            }
            return array;
        }

        // we need to save a reference to the left bound of our section of the array for the later recursive call.
        // pivot serves as the right bound and is not modified
        int initialLeft = left;

        // move the left pointer and right pointer towards each other, stopping when you find an element that should be flipped.
        // flip as many as exist with a counterpart
        while (left < right) {
            while (comparator.compare(array[left], array[pivot]) <= 0 && left < right) {
                left++;
            }

            while (comparator.compare(array[right], array[pivot]) > 0 && right > left) {
                right--;
            }

            if (left < right) {
                swap(array, left, right);
            }
        }

        // no more flips to be made between left and right, swap left and pivot if left is larger
        if (comparator.compare(array[left], array[pivot]) > 0) {
            swap(array, left, pivot);
        }

        doSort(array, initialLeft, left - 1, left, comparator); // sort the left "half"
        doSort(array, left + 1, pivot - 1, pivot, comparator); // sort the right "half"
        return array;
    }

    private void swap(T[] array, int left, int right) {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}

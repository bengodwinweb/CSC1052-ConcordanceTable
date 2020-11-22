/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
ICollection.java

Info: Interface for a generic collection
*/

package com.bengodwin.datastructures;

public interface ICollection<T> {

    /**
     * Add an element to the collection
     */
    void add(T element);

    /**
     * Remove all elements from the collection
     */
    void clear();

    /**
     * Indicates whether a matching element exists in the collection
     * @param element item to search for
     * @return true if matching item was found, false otherwise
     */
    boolean contains(T element);

    /**
     * Indicates whether the collection has a size of 0
     */
    boolean isEmpty();

    /**
     * Removes the first matching element from the collection if found
     * @param element item to search for
     */
    void remove(T element);

    /**
     * Returns the matching element in the collection if found
     * @param element item to search for
     * @return element if found, null otherwise
     */
    T get(T element);

    /**
     * Returns the size of the collection as an int
     */
    int size();

    /**
     * Returns the collection as an array
     * @param clazz Class of the array to be generated. Needed to make collections while working with generic types.
     */
    T[] toArray(Class<T> clazz);
}

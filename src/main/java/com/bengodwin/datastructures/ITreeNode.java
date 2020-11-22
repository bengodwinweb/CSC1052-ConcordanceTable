/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
ITreeNode.java

Info: Interface for a generic node to be used by a Tree data structure
*/

package com.bengodwin.datastructures;

public interface ITreeNode<T> {

    /**
     * Returns the data held by the node
     */
    T getData();

    /**
     * Returns the left child node
     */
    ITreeNode<T> getLeft();

    /**
     * Returns true if the node holds a left child reference
     */
    boolean hasLeft();

    /**
     * Returns the right child node
     */
    ITreeNode<T> getRight();

    /**
     * Returns true if the node holds a right child reference
     * @return
     */
    boolean hasRight();

    /**
     * Replaces node's data
     */
    void setData(T data);

    /**
     * Sets the node's left child reference
     */
    void setLeft(ITreeNode<T> leftChild);

    /**
     * Sets the node's right child reference
     */
    void setRight(ITreeNode<T> rightChild);
}

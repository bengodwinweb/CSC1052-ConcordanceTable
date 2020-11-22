/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
TreeNode.java

Info: Generic node for a Tree data structure
*/

package com.bengodwin.datastructures;

public class TreeNode<T> implements ITreeNode<T> {

    private T m_data;
    private ITreeNode<T> m_left;
    private ITreeNode<T> m_right;

    public TreeNode(T data) {
        this.m_data = data;
        this.m_left = null;
        this.m_right = null;
    }

    @Override
    public T getData() {
        return m_data;
    }

    @Override
    public ITreeNode<T> getLeft() {
        return m_left;
    }

    @Override
    public boolean hasLeft() {
        return m_left != null;
    }

    @Override
    public ITreeNode<T> getRight() {
        return m_right;
    }

    @Override
    public boolean hasRight() {
        return m_right != null;
    }

    @Override
    public void setData(T data) {
        this.m_data = data;
    }

    @Override
    public void setLeft(ITreeNode<T> leftChild) {
        this.m_left = leftChild;
    }

    @Override
    public void setRight(ITreeNode<T> rightChild) {
        this.m_right = rightChild;
    }
}

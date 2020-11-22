/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
BinaryTree.java

Info: Implementation of a generic Binary Tree
*/

package com.bengodwin.datastructures;

import java.lang.reflect.Array;
import java.util.*;

public class BinaryTree<T extends Comparable<T>> implements ICollection<T> {

    private Optional<ITreeNode<T>> m_root;
    private int m_size;

    public BinaryTree() {
        this.m_root = Optional.empty();
    }

    @Override
    public int size() {
        return this.m_size;
    }

    @Override
    public boolean isEmpty() {
        return this.m_root.isEmpty();
    }

    @Override
    public T get(T element) {
        if (element == null || isEmpty()) {
            return null;
        }

        Optional<ITreeNode<T>> found = search(element, m_root.get());
        return found.isPresent() ? found.get().getData() : null;
    }

    @Override
    public boolean contains(T element) {
        if (element == null || isEmpty()) {
            return false;
        }

        return search(element, m_root.get()).isPresent();
    }

    @Override
    public void clear() {
        this.m_root = Optional.empty();
        m_size = 0;
    }

    @Override
    public void add(T element) {
        if (element == null) {
            return;
        }

        /*
        If we have a root node, insert this element into the tree
        Otherwise, create a root node with this element

        m_size++ should be called when the element is actually added to the collection - calling doAdd does not guarantee
        the element will be added based on the doAdd() implementation
         */

        if (!isEmpty()) {
            doAdd(element, m_root.get());
        } else {
            m_root = Optional.of(new TreeNode<>(element));
            m_size++; // increase collection size
        }
    }

    /**
     * Recursive method to add an element to the tree. Compares the new element to the node and either goes
     * left or right based on the comparison. Adds the element as a child when it finds a node with no child on the
     * appropriate side.
     *
     * @param element element to add
     * @param node    current node to compare against
     */
    protected void doAdd(T element, ITreeNode<T> node) {
        int comparison = element.compareTo(node.getData());

        if (comparison == 0) {
            // new element is equal to the current node
            addMatch(element, node);
        } else if (comparison < 0) {
            // go left
            if (node.hasLeft()) {
                doAdd(element, node.getLeft());
            } else {
                // no child exists, add element as left child
                node.setLeft(new TreeNode<>(element));
                m_size++; // increase the collection size
            }
        } else {
            // go right
            if (node.hasRight()) {
                doAdd(element, node.getRight());
            } else {
                // no child exists, add element as right child
                node.setRight(new TreeNode<>(element));
                m_size++; // increase the collection size
            }
        }
    }

    /**
     * Handles the case in which an element is being added to the tree but matches an existing element.
     * This method does nothing in the base BinaryTree class, exists to be overridden by subclasses.
     *
     * @param element new data to add
     * @param node    node with matching data already in the tree
     */
    protected void addMatch(T element, ITreeNode<T> node) {
        // do nothing, method is here so it can be overridden if necessary
    }

    /**
     * Recursive method. Performs a binary search through the tree for a node with matching data.
     *
     * @param element element to find
     * @param node    node to search from
     * @return Optional node, contains the node if match was found
     */
    protected Optional<ITreeNode<T>> search(T element, ITreeNode<T> node) {
        int comparison = element.compareTo(node.getData());

        /*
        If the current node's data is equal to the element we're searching for, return this node.
        Otherwise, recursively search down the left or right branch as appropriate.
        If no branch exists, then the element is not in the collection, return Optional.empty()
         */

        if (comparison == 0) {
            return Optional.of(node);
        } else if (comparison < 0) {
            if (node.hasLeft()) {
                return search(element, node.getLeft());
            }
        } else {
            if (node.hasRight()) {
                return search(element, node.getRight());
            }
        }

        return Optional.empty();
    }


    @Override
    public void remove(T element) {
        if (element == null || isEmpty()) {
            return;
        }

        var returnedRoot = delete(element, m_root.get());
        m_root = returnedRoot == null ? Optional.empty() : Optional.of(returnedRoot);
    }

    /**
     * Recursive method. Used to find and delete a node from the BinaryTree.
     *
     * @param element element to search for, deletes the first node where the data matches this element
     * @param node    current node in the search
     * @return node to set the parent's child to (not the deleted node)
     */
    protected ITreeNode<T> delete(T element, ITreeNode<T> node) {
        /**
         * A little trickier than the others because we can't lose the reference to any children nodes if we're deleting an element
         * that is not a leaf.
         *
         * This method looks for a node with matching data. If one is found:
         * If it is a leaf, return null.
         * If it has one child missing, return the one child.
         * If it has two children, we find the leftmost child of the right child, and replace the data in this node with that leaf node's data, then remove that leaf.
         *
         * This method returns what the calling parent should set its child to, not the leaf or deleted node (unless one up from that point).
         * This way we are able to cascade correctly back up the tree with something like node.setRight(delete(leafNodeData, node.getRight))
         */

        if (node == null) {
            return null;
        }

        int comparison = element.compareTo(node.getData());

        if (comparison == 0) {
            if (!node.hasLeft()) {
                node = node.getRight(); // if both children are missing, this returns null, which is expected
            } else if (!node.hasRight()) {
                node = node.getLeft();
            } else {
                var farthestLeaf = getMinChild(node.getRight());
                node.setData(farthestLeaf.getData());
                node.setRight(delete(farthestLeaf.getData(), node.getRight()));
                m_size--;
            }
        } else if (comparison < 0) {
            node.setLeft(delete(element, node.getLeft()));
        } else {
            node.setRight(delete(element, node.getRight()));
        }

        return node;
    }

    /**
     * Recursive. Returns the leaf starting at this node and always going left.
     *
     * @param node node to search from
     * @return smallest leaf
     */
    protected ITreeNode<T> getMinChild(ITreeNode<T> node) {
        if (node.hasLeft()) {
            return getMinChild(node.getLeft());
        }
        return node;
    }

    @Override
    public T[] toArray(Class<T> clazz) {
        // Use an ArrayConstructor node consumer to add all elements in the tree to an array in order
        T[] arr = (T[]) Array.newInstance(clazz, m_size);

        if (!isEmpty()) {
            ArrayConstructor constructor = new ArrayConstructor(arr);
            traverse(constructor);
        }

        return arr;
    }

    /**
     * Functional interface to be passed to traverse() that will add all elements to an array
     */
    private class ArrayConstructor implements NodeConsumer<T> {

        private final T[] array;
        private int i;

        public ArrayConstructor(T[] arr) {
            array = arr;
            i = 0;
        }

        @Override
        public void consumeNode(ITreeNode<T> node) {
            array[i++] = node.getData();
        }
    }


    /**
     * Functional interface that can be passed as a parameter to traverse() to call a method with every element in the collection
     */
    @FunctionalInterface
    public interface NodeConsumer<T> {
        void consumeNode(ITreeNode<T> node);
    }

    /**
     * Traverses the tree, passing each node into the NodeConsumer.consumeNode() method.
     * Moves through the tree from the smallest to the largest node
     *
     * @param method method used by the calling class to process each node in the tree
     */
    public void traverse(NodeConsumer<T> method) {
        if (!isEmpty()) {
            doTraverse(m_root.get(), method);
        }
    }

    /**
     * Recursive method. Traverses through the tree in order from the current node down
     * @param node
     * @param method
     */
    private void doTraverse(ITreeNode<T> node, NodeConsumer<T> method) {
        /*
        Visit every left node, then visit the node passed in to this method, then visit every right node.
        Recursively visits every node in the tree from node down, in order
         */

        if (node.hasLeft()) {
            doTraverse(node.getLeft(), method);
        }

        method.consumeNode(node);

        if (node.hasRight()) {
            doTraverse(node.getRight(), method);
        }
    }

}

/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
ConcordanceTable.java

Info: this class extends BinaryTree. This class is a wrapper around an underlying collection (currently a binary tree)
of ConcordanceDataPair (holds a word and the number of times that word was added to this collection).

Words can be passed one at a time or by the line to this collection, and the class provides the ability to see the number
of unique words or total number of words. It stores the words alphabetically - getting the underlying
collection with a .toArray() call returns a list of ConcordanceDataPair sorted in alphabetical order by word.
*/

package com.bengodwin.concordancetable;

import com.bengodwin.datastructures.BinaryTree;
import com.bengodwin.datastructures.ITreeNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcordanceTable extends BinaryTree<ConcordanceDataPair> implements IConcordanceTable {

    private static final String s_expression = ".*[\\p{Alpha}].*"; // one or more letters anywhere in the string
    private static final Pattern s_pattern = Pattern.compile(s_expression);

    @Override
    public void add(String word) {
        word = word.trim().toUpperCase(); // get rid of leading and trailing whitespace, then uppercase
        Matcher m = s_pattern.matcher(word); // check that the word matches the pattern - must contain an alphabetic character
        if (m.matches()) {
            add(new ConcordanceDataPair(word));
        }
    }

    @Override
    public void addLine(String line) {
        /*
        Split the line into an array of words. Split on whitespace, brackets, and punctuation other than - and '
        Add each resulting word from the new line to the collection.
        The add method will get rid of strings that are empty or do not contain any alphabetic characters.
         */
        String[] words = line.split("[&#@$`/.,?!;:|\\*<>\\^\\[\\](){}\\=+_\\s]");
        for (var word : words) {
            add(word);
        }
    }

    @Override
    protected void addMatch(ConcordanceDataPair element, ITreeNode<ConcordanceDataPair> node) {
        /*
        base BinaryTree does nothing when new elements are added that match elements already in the collection.
        We override that here to increment the number of occurrences when a match is found
         */
        node.getData().incrementOccurrences();
    }

    @Override
    public ConcordanceDataPair get(String word) {
        return get(new ConcordanceDataPair(word.trim().toUpperCase()));
    }

    @Override
    public ConcordanceDataPair[] toArray() {
        return super.toArray(ConcordanceDataPair.class);
    }

    @Override
    public int getUniqueWords() {
        return size();
    }

    @Override
    public int getTotalWords() {
        // we use the traverse method - create a counter that will add up the occurrences in each node, return that count
        var counter = new WordCounter();
        traverse(counter);
        return counter.getCount();
    }

    /**
     * NodeConsumer used to count the total number of words held in the collection.
     * This gets passed into traverse(), consumeNode will get called on each node in the collection.
     */
    private class WordCounter implements NodeConsumer<ConcordanceDataPair> {
        private int count;

        public WordCounter() {
            count = 0;
        }

        public int getCount() {
            return count;
        }

        @Override
        public void consumeNode(ITreeNode<ConcordanceDataPair> node) {
            count += node.getData().getOccurrences();
        }
    }
}

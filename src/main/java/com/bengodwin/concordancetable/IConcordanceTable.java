/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
IConcordanceTable.java

Info: public interface for the ConcordanceTable class
*/

package com.bengodwin.concordancetable;

public interface IConcordanceTable {

    /**
     * Adds the word to the collection, ignoring capitalization and leading/trailing whitespace.
     * Words without at least one alphabetic character will be ignored.
     * @param word word to add to the collection - "  Apple " becomes "APPLE", " m1" becomes "M1", and "1-2" is ignored
     */
    void add(String word);

    /**
     * Adds the words in a line of text to the collection. This method will remove punctuation characters other than - and '.
     * Each word from the resulting line will be added to the collection with the restraints present on the add(String word) method.
     * @param line line of text to add to the collection. "John's friend drove 80 miles per hour (wow!) to exit 11. #insane!!"
     *             beocmes "JOHN'S" "FRIEND" "DROVE" "MILES" "PER" "HOUR" "WOW" "TO" "EXIT" "INSANE"
     */
    void addLine(String line);

    /**
     * Returns the ConcordanceDataPair held in the table matching the word, or null if not found.
     * Capitalization and leading/trailing whitespace are ignored.
     * @param word "  Apple " returns ConcordanceDataPair matching "APPLE" if any exist in the collection
     */
    ConcordanceDataPair get(String word);

    /**
     * Returns an array of ConcordanceDataPair in the table, sorted by word in alphabetical order
     */
    ConcordanceDataPair[] toArray();

    /**
     * Returns the number of unique words added to the ConcordanceTable
     */
    int getUniqueWords();

    /**
     * Returns the total number of words added to the ConcordanceTable, including duplicates
     */
    int getTotalWords();
}

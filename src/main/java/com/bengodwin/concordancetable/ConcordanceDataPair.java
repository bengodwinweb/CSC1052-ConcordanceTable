/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
ConcordanceDataPair.java

Info: this class is used as the element in a ConcordanceTable collection. Holds the "word" as a string, and the number
of occurrences for that word.
*/

package com.bengodwin.concordancetable;

public class ConcordanceDataPair implements Comparable<ConcordanceDataPair> {

    private final String m_word;
    private int m_occurrences;

    public ConcordanceDataPair(String word) {
        this(word, 1);
    }

    public ConcordanceDataPair(String word, int occurrences) {
        this.m_word = word;
        this.m_occurrences = occurrences;
    }

    public String getWord() {
        return m_word;
    }

    public int getOccurrences() {
        return m_occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.m_occurrences = occurrences;
    }

    public void incrementOccurrences() {
        m_occurrences++;
    }

    @Override
    public int compareTo(ConcordanceDataPair o) {
        // Compares only the words - not number of occurrences. For inserting/finding the word in the ConcordanceTable collection
        if (o == null) {
            return 1;
        }

        return getWord().compareTo(o.getWord());
    }

    @Override
    public boolean equals(Object o) {
        // Compares only the words - not number of occurrences. For inserting/finding the word in the ConcordanceTable collection
        if (this == o) return true;

        if (o == null || !(o instanceof ConcordanceDataPair)) {
            return false;
        }

        ConcordanceDataPair other = (ConcordanceDataPair) o;
        return getWord().equals(other.getWord());
    }

    @Override
    public String toString() {
        return String.format("Word: %s, Occurances: %d", getWord(), getOccurrences());
    }
}

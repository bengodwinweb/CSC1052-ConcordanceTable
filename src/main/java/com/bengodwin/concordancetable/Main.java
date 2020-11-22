/*
Benjamin Godwin 2020
CSC1052 - Concordance Table Project
Main.java

Info: Main entry point to the application. Reads in text file(s), counts the number of unique and total words,
and sorts the words alphabetically
*/

package com.bengodwin.concordancetable;

import com.bengodwin.sortingalgorithms.BubbleSort;
import com.bengodwin.sortingalgorithms.ISortingAlgorithm;
import com.bengodwin.sortingalgorithms.MergeSort;
import com.bengodwin.sortingalgorithms.QuickSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

public class Main {

    private static ISortingAlgorithm<ConcordanceDataPair> s_sorter;

    static {
        /*
       Can switch out the sorting algorithm here to see how times differ.
       Fair warning - BubbleSort seems to throw a stack overflow somewhere between 5-10,000 elements.
       The other two algorithms are fine with much larger collections.
         */

        // s_sorter = new BubbleSort<>();
//         s_sorter = new MergeSort<>();
        s_sorter = new QuickSort<>();
    }

    /*
        SUMMARY:
        Project contains implementations of a BinaryTree and three sorting algorithms - Bubble Sort, Merge Sort, and Quick Sort
        The ConcordanceTable class extends BinaryTree - holding a collection of ConcordanceDataPairs (pair is a String "word" and a number of occurrences)

        The main method takes in an array of strings representing absolute paths to .txt files.
        For each file in args, we read in the file, passing all of the text into the ConcordanceTable. The table creates a collection of unique words
        in alphabetical order from the text that is passed in, with a number of occurrences for each word.

        After reading a file we print the number of unique words, number of total words, and a list of unique words in alphabetical order.
        We then use the sorter chosen in the static initialization block to make a list of the words by number of occurrences and print that as well.
     */

    /**
     * Takes in an array of Strings representing the full path to a file on disk and creates a ConcordanceTable from the text in each file
     * @param args each must be the absolute path to a .txt file
     */
    public static void main(String[] args) {
        for (var path : args) {
            analyzeText(path);
        }
    }

    /**
     * Creates a ConcordanceTable from the file and prints the results to the console
     * @param filePath absolute path of a .txt file
     */
    private static void analyzeText(String filePath) {
        // Check for valid file type
        Optional<String> extension = getExtensionFromPath(filePath);
        if (extension.isEmpty() || !extension.get().equals(".txt")) {
            System.out.printf("Skipping file: %s, invalid file type\n", filePath);
            return;
        }

        IConcordanceTable table = new ConcordanceTable();

        try (BufferedReader r = new BufferedReader(new FileReader(filePath))) {
            LocalDateTime start = LocalDateTime.now(); // get the time we started the read so we can print how long the read took later on

            System.out.println("File: " + filePath);

            // Read each line from the file and add it to the table - table handles separating the line into words
            String line;
            while ((line = r.readLine()) != null) {
                table.addLine(line);
                System.out.println("\t" + line.trim());
            }

            ConcordanceDataPair[] alphabetical = table.toArray(); // get an array of words/occurrences sorted in alphabetical order
            LocalDateTime endRead = LocalDateTime.now(); // get the time we finished reading the file/creating the sorted array
            Duration readAlphabetically = Duration.between(start, endRead); // get the duration of the read/alphabetical sort to print later

            // Print the total words, unique words, and list of pairs in alphabetical order
            System.out.println("\nTotal words found: " + table.getTotalWords());
            System.out.println("Unique words found: " + table.getUniqueWords());
            System.out.println("\nAll words in alphabetical order:");
            for (var pair : alphabetical) {
                System.out.printf("\t%s\n", pair.toString());
            }

            start = LocalDateTime.now(); // get start time of sort
            // Sort the words by number of occurrences, highest number of occurrences comes first in the list
            ConcordanceDataPair[] occurrenceOrder = s_sorter.sort(alphabetical, (e1, e2) -> Integer.compare(e2.getOccurrences(), e1.getOccurrences()));
            LocalDateTime endSort = LocalDateTime.now(); // get end time of sort
            Duration sortByOccurrence = Duration.between(start, endSort); // get the sort duration to print later

            System.out.println("\nAll words in order of occurrence:");
            for (var pair : occurrenceOrder) {
                System.out.println("\t" + pair.toString());
            }

            printDuration("\nTime to read and sort file alphabetically", readAlphabetically); // print the duration of the read/alphabetical sort
            printDuration("Time to sort words by occurrence", sortByOccurrence); // print the duration of the sort by occurrence
        } catch (IOException e) {
            System.out.println("Unable to read file at path " + filePath);
            System.out.println(e.getMessage());
        }

        System.out.println();
    }

    /**
     * Prints the message and duration to the console in the format "{message}: {duration} ms"
     */
    private static void printDuration(String message, Duration d) {
        System.out.printf("%s: %d ms\n", message, d.toMillis());
    }

    /**
     * Returns an Optional containing the file extension from an absolute filepath, if found
     * @param path absolute path to the file
     * @return extension, ex. ".txt" or ".java"
     */
    private static Optional<String> getExtensionFromPath(String path) {
        if (path.contains(".")) {
            String ext = path.substring(path.lastIndexOf('.'));
            if (ext.length() > 2) {
                return Optional.of(ext);
            }
        }

        return Optional.empty();
    }
}

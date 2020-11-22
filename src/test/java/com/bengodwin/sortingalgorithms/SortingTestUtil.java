package com.bengodwin.sortingalgorithms;

import java.util.Arrays;
import java.util.Random;

public class SortingTestUtil {

    public static void printArray(Object[] array) {
        String[] strings = Arrays.stream(array).map(Object::toString).toArray(String[]::new);
        System.out.println(String.join(", ", strings));
    }

    public static Integer[] makeData(int collectionSize, int max) {
        Random rand = new Random();
        Integer[] data = new Integer[collectionSize];

        for (int i = 0; i < collectionSize; i++) {
            data[i] = rand.nextInt(max);
        }

        return data;
    }
}

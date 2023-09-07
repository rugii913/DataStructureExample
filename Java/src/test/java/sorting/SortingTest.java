package sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {

    static final int NUM_SCALE = 10_000;
    static final int SIZE = 1_000;
    int array[] = new int[SIZE];
    int comparisonArray[] = new int[SIZE];

    @BeforeEach
    void prepare() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (NUM_SCALE * Math.random());
            comparisonArray[i] = array[i];
        }

        comparisonArray = Arrays.stream(comparisonArray).sorted().toArray();
    }

    @Test
    void quickSortTest() {
        Sorting sorting = new Sorting(array);
        sorting.quickSort();

        Assertions.assertEquals(comparisonArray[1], array[1]);
        Assertions.assertEquals(comparisonArray[10], array[10]);
        Assertions.assertEquals(comparisonArray[100], array[100]);
        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(comparisonArray[i], array[i]);
        }
    }

}
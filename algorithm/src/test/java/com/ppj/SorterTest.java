package com.ppj;

import com.ppj.algorithm.sort.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SorterTest {

    private int[] arr;
    private int size = 20;
    private int[] copyArr;

    @Before
    public void beforeSorter() {
        arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        System.out.print("source array is: ");
        SorterUtil.printArray(arr);
        copyArr = Arrays.copyOf(arr, arr.length);
    }

    @After
    public void afterSort() {
        System.out.print("result array is: ");
        SorterUtil.printArray(arr);
        Arrays.sort(copyArr);
        if (Arrays.equals(arr, copyArr)) {
            System.out.println("the result is right!");
        } else {
            System.out.println("the result is error!");
            System.out.print("right array is: ");
            SorterUtil.printArray(copyArr);
        }
    }

    /**
     * test BubbleSorter
     */
    @Test
    public void test01() {
        Sorter sorter = new BubbleSorter();
        sorter.sort(arr);
    }

    /**
     * test InsertionSorter
     */
    @Test
    public void test02() {
        Sorter sorter = new InsertionSorter();
        sorter.sort(arr);
    }

    /**
     * test SelectionSorter
     */
    @Test
    public void test03() {
        Sorter sorter = new SelectionSorter();
        sorter.sort(arr);
    }

    /**
     * test MergeSorter
     */
    @Test
    public void test04() {
        Sorter sorter = new MergeSorter();
        sorter.sort(arr);
    }

}

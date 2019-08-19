package com.ppj.algorithm.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class SorterUtil {

    public static void swap(@NotNull int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
}

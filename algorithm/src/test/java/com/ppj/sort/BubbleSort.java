package com.ppj.sort;

import java.util.Arrays;

/**
 * @author cj
 * @since 22/9/22 11:40 上午
 */
public class BubbleSort {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 3, 4, 2, 2, 3, 1, 4, 6, 8, 4, 0, 6, 8, 9};
    bubbleSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      boolean flag = true;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          flag = false;
        }
      }
      if (flag) {
        System.out.println("跳出了");
        break;
      }
    }
  }

}

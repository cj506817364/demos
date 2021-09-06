package com.ppj.algorithm.leetcode;

import java.util.Random;

/**
 * @author pipi
 * @since 2021/8/30 19:55
 */
public class No528_按权重随机选择 {

  /** sum[i] 表示 w[]的前i个数的和 */
  private int[] sum;

  private Random random;

  public No528_按权重随机选择(int[] w) {
    sum = new int[w.length + 1];
    for (int i = 0; i < w.length; i++) {
      sum[i + 1] = sum[i] + w[i];
    }
    this.random = new Random();
  }

  public static void main(String[] args) {

    final No528_按权重随机选择 no528_按权重随机选择 = new No528_按权重随机选择(new int[] {1});
    System.out.println(no528_按权重随机选择.pickIndex());
  }

  public static int recursionBinarySearch(int[] arr, int key, int low, int high) {

    int middle = low + (high - low) / 2;
    if (low >= high) {
      // 没找到
      return middle;
    }
    if (arr[middle] > key) {
      return recursionBinarySearch(arr, key, low, high - 1);
    } else if (arr[middle] < key) {
      return recursionBinarySearch(arr, key, low + 1, high);
    }
    return middle;
  }

  public int pickIndex() {
    final int length = sum.length;
    int index = random.nextInt(sum[length - 1]);
    return recursionBinarySearch(sum, index, 0, sum.length);
  }
}

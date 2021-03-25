package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author pipi
 * @since 2021/3/23 18:04
 */
public class 旋转数组 {

  public static void main(String[] args) {
    final int[] nums = {7, 1, 5, 3, 6, 4};
    new 旋转数组().rotate(nums, 2);
    System.out.println(Arrays.toString(nums));
  }

  public void rotate(int[] nums, int k) {
    final int len = nums.length;
    while (k >= len) {
      k -= len;
    }
    if (k == 0) {
      return;
    }
    final int startPoint = len - k;
    int[] temp = new int[k];

    System.arraycopy(nums, startPoint, temp, 0, k);

    for (int i = 0; i < startPoint; i++) {
      nums[len - i - 1] = nums[len - i - 1 - k];
    }

    System.arraycopy(temp, 0, nums, 0, k);
  }
}

package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author pipi
 * @since 2021/7/20 10:21
 */
public class No1877_数组中最大数对和的最小值 {

  public static void main(String[] args) {
    //
    final int i = new No1877_数组中最大数对和的最小值().minPairSum(new int[] {3, 5, 4, 2, 4, 6});
    System.out.println(i);
  }

  public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int max = 0;
    for (int i = 0; i <= nums.length / 2; i++) {
      int j = nums.length - 1 - i;
      max = Math.max(max, nums[i] + nums[j]);
    }
    return max;
  }
}

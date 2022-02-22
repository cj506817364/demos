package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2022/1/4 11:25
 */
public class No152_乘积最大子数组 {

  public static void main(String[] args) {
    System.out.println(new No152_乘积最大子数组().maxProduct(new int[]{-2, 0, -1}));
  }

  public int maxProduct(int[] nums) {
    int max = nums[0];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i; j < nums.length; j++) {
        int res = product(nums, i, j);
        max = Math.max(res, max);
      }
    }
    return max;
  }

  private int product(int[] nums, int i, int j) {
    int res = 1;
    for (int k = i; k <= j; k++) {
      res *= nums[k];
    }
    return res;
  }
}

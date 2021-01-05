package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * jump game
 *
 * @author pipi
 * @date 2021/1/5 11:59
 */
public class Q1696 {

  public static void main(String[] args) {
    final int num = new Q1696().maxResult(new int[]{1, -1, -2, 4, -7, 3}, 2);
    System.out.println(num);
  }

  public int maxResult(int[] nums, int k) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MIN_VALUE);
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      for (int j = Math.max(0, i - k); j < i; j++) {
        dp[i] = Math.max(dp[i], dp[j]);
      }
      dp[i] += nums[i];
    }
    return dp[nums.length - 1];
  }


}

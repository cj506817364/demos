package com.ppj.algorithm.leetcode;

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

  /**
   * dp[i]表示 走到i的时候的最大值
   * 给你一个下标从 0 开始的整数数组 nums和一个整数 k。
   *
   * 一开始你在下标0处。每一步，你最多可以往前跳k步，但你不能跳出数组的边界。也就是说，你可以从下标i跳到[i + 1， min(n - 1, i + k)]包含 两个端点的任意位置。
   *
   * 你的目标是到达数组最后一个位置（下标为 n - 1），你的 得分为经过的所有数字之和。
   *
   * 请你返回你能得到的 最大得分。
   *
   * @param nums
   * @param k
   * @return
   */
  public int maxResult(int[] nums, int k) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int tempMax = dp[i] = Integer.MIN_VALUE;
      for (int j = Math.max(i - k, 0); j < i; j++) {
        if (tempMax < dp[j]) {
          tempMax = dp[j];
        }
        dp[i] = tempMax + nums[i];
      }
    }
    return dp[nums.length - 1];
  }


}

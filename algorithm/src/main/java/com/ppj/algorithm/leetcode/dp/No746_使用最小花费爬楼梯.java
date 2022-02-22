package com.ppj.algorithm.leetcode.dp;

import java.util.Arrays;

/**
 * @author cj
 * @since 2021/12/30 15:41
 */
public class No746_使用最小花费爬楼梯 {

  public static void main(String[] args) {
    System.out.println(
        new No746_使用最小花费爬楼梯().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
  }

  /**
   * dp[i] 表示走到第i步的最小花费
   */
  public int minCostClimbingStairs(int[] cost) {
    int len = cost.length;
    int[] dp = new int[len + 1];
    dp[0] = 0;
    dp[1] = 0;
    for (int i = 2; i <= len; i++) {
      dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    }
    System.out.println(Arrays.toString(dp));
    return dp[len];
  }
}

package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/30 18:37
 */
public class No45_跳跃游戏II {

  public static void main(String[] args) {
    new No45_跳跃游戏II().jump(new int[]{2, 3, 1, 1, 4});
  }

  /**
   * dp[i] 表示跳到第i个位置需要的最少次数
   */
  public int jump(int[] nums) {
    int len = nums.length;
    int[] dp = new int[len + 1];
    return 0;
  }
}

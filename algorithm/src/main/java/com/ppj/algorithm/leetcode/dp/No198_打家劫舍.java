package com.ppj.algorithm.leetcode.dp;

import java.util.Arrays;

/**
 * @author cj
 * @since 2021/12/30 16:01
 */
public class No198_打家劫舍 {

  public static void main(String[] args) {
    System.out.println(new No198_打家劫舍().rob(new int[]{1, 2, 3, 1}));
  }

  /**
   * dp[i] 表示偷窃到第i个房间的最高金额
   */
  public int rob(int[] nums) {
    int len = nums.length;
    int[] dp = new int[len + 1];
    dp[0] = 0;
    dp[1] = nums[0];
    for (int i = 2; i <= len; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
    }
    System.out.println(Arrays.toString(dp));
    return dp[len];
  }

}

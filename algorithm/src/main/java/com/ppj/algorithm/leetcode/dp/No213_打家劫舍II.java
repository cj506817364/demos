package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/30 16:12
 */
public class No213_打家劫舍II {

  public static void main(String[] args) {
    System.out.println(new No213_打家劫舍II().rob(new int[]{0, 0}));
  }


  /**
   * dp[i] 表示偷窃到第i个房屋最大金额
   */
  public int rob(int[] nums) {
    int len = nums.length;
    int[] dp = new int[len + 1];
    dp[0] = 0;
    dp[1] = nums[0];
    if (len >= 2){
      dp[2] = Math.max(nums[0], nums[1]);
    }
    for (int i = 3; i <= len; i++) {
      dp[i] = Math.max(robRange(nums, 0, len - 2), robRange(nums, 1, len - 1));
    }
    return dp[len];
  }

  public int robRange(int[] nums, int start, int end) {
    int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
    for (int i = start + 2; i <= end; i++) {
      int temp = second;
      second = Math.max(first + nums[i], second);
      first = temp;
    }
    return second;
  }


}

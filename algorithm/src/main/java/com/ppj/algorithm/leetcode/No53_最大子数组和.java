package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2021/12/31 18:54
 */
public class No53_最大子数组和 {

  public static void main(String[] args) {
    System.out.println(new No53_最大子数组和().maxSubArray(new int[]{-2, 1}));
  }

  /**
   * DP[i] 表示达到nums[i] 能算出的最大值
   * dp[i] = max(dp[i-1]+nums[i], nums[i])
   */
  public int maxSubArray(int[] nums) {
    int len = nums.length;
    int[] dp = initDP(len);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      dp[i + 1] = Math.max(dp[i] + nums[i], nums[i]);
      max = Math.max(max, dp[i + 1]);
    }
    return max;
  }

  /**
   * 初始化dp数组
   */
  private int[] initDP(int len) {
    int[] dp = new int[len + 1];
    dp[0] = 0;
    return dp;
  }

//  public int maxSubArray(int[] nums) {
//    int max = Arrays.stream(nums).min().getAsInt();
//    for (int i = 0; i < nums.length; i++) {
//      for (int j = i; j < nums.length; j++) {
//        int res = sum(nums, i, j);
//        max = Math.max(res, max);
//      }
//    }
//    return max;
//  }
//
//  private int sum(int[] nums, int start, int end) {
//    int res = 0;
//    for (int i = start; i <= end; i++) {
//      res += nums[i];
//    }
//    return res;
//  }

}

package com.ppj.algorithm.leetcode.dp;

import java.util.Arrays;

/**
 * @author cj
 * @since 2021/12/30 17:31
 */
public class No740_删除并获得点数 {

  public static void main(String[] args) {
    System.out.println(new No740_删除并获得点数().deleteAndEarn(
        new int[]{8, 3, 4, 7, 6, 6, 9, 2, 5, 8, 2, 4}));
  }

  /**
   */
  int[] cnts = new int[10009];
  public int deleteAndEarn(int[] nums) {
    int max = 0;
    for (int x : nums) {
      cnts[x]++;
      max = Math.max(max, x);
    }
    // dp[i][0] 代表「不选」数值 i；dp[i][1] 代表「选择」数值 i
    int[][] dp = new int[max + 1][2];
    for (int i = 1; i <= max; i++) {
      dp[i][1] = dp[i - 1][0] + i * cnts[i];
      dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
    }
    return Math.max(dp[max][0], dp[max][1]);
  }

//  /**
//   * dp[i] 表示删除第i个点数能获得的最大值
//   */
//  public int deleteAndEarn(int[] nums) {
//    int len = nums.length;
//    if (len == 0) {
//      return 0;
//    }
//    if (len == 1) {
//      return nums[0];
//    }
//    int[] dp = new int[len + 1];
//    dp[0] = 0;
//    for (int i = 1; i <= len; i++) {
//      final int current = nums[i - 1];
//      final int[] removeIndexArr = del(nums, i - 1);
//      final int[] tempArr = Arrays.stream(removeIndexArr)
//          .filter(a -> a != current - 1 && a != current + 1)
//          .toArray();
//      dp[i] = current + deleteAndEarn(tempArr);
//    }
//    return Arrays.stream(dp).max().getAsInt();
//  }
//
//  private int[] del(int[] nums, int index) {
//    final int[] ints = new int[nums.length - 1];
//    int k = 0;
//    for (int i = 1; i < nums.length; i++) {
//      if (i == index) {
//        continue;
//      }
//      ints[k++] = nums[i];
//    }
//    return ints;
//  }

}

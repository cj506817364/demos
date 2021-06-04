package com.ppj.algorithm.leetcode;

import cn.hutool.core.lang.Assert;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * <p>输入: nums = [0,1,0] 输出: 2 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 * @author pipi
 * @since 2021/6/3 10:21
 */
public class No525_连续数组 {

  public static void main(String[] args) {
    final int maxLength = new No525_连续数组().findMaxLength(new int[] {0, 1, 0, 1});
    System.out.println(maxLength);
    Assert.isTrue(maxLength == 4);
  }

  // 动态规划
  public int findMaxLength(int[] nums) {
    final int len = nums.length;
    int max = len / 2;
    // 设dp[n]为有n个0和1的时候 最多有dp[n]种解法
    boolean dp[] = new boolean[max + 1];
    dp[0] = false;
    for (int n = 1; n <= max; n++) {
      dp[n] = false;
      for (int i = 0; i < len; i++) {
        if (len - i < n * 2) {
          break;
        }
        if (dp[n]) {
          break;
        }
        // 区间[i,j]
        for (int j = i + 1; j < len; j++) {
          if (j - i + 1 < n * 2) {
            continue;
          }
          if (j - i + 1 > n * 2) {
            break;
          }
          if (dp[n]) {
            break;
          }
          if (check(nums, i, j, n)) {
            dp[n] = true;
          }
        }
      }
    }
    for (int i = dp.length - 1; i >= 0; i--) {
      if (dp[i]) {
        return i * 2;
      }
    }
    return 0;
  }

  // check i,j区间内是否为连续n个 0,1
  private boolean check(int[] nums, int i, int j, int n) {
    int zero = 0;
    int one = 0;
    for (int k = i; k <= j; k++) {
      if (nums[k] == 0) {
        zero++;
      } else {
        one++;
      }
    }
    return zero == one;
  }
}

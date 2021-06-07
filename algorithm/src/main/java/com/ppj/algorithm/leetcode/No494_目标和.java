package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/6/7 10:43
 */
public class No494_目标和 {

  public static int count = 0;

  public static void main(String[] args) {
    final int targetSumWays = new No494_目标和().findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3);
    System.out.println(targetSumWays);
  }

  // 回溯算法 时间复杂度O(2^n)
  public int findTargetSumWays(int[] nums, int target) {
    cal(nums, target, 0, 0);
    return count;
  }

  public void cal(int[] nums, int target, int index, int res) {
    if (index == nums.length) {
      if (res == target) {
        count++;
      }
    } else {
      cal(nums, target, index + 1, res + nums[index]);
      cal(nums, target, index + 1, res - nums[index]);
    }
  }
}

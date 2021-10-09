package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/9/9 12:31
 */
public class No42_接雨水 {

  public static void main(String[] args) {
    //
    final int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    final int trap = trap(height);
    System.out.println(trap);
  }

  public static int trap(int[] height) {
    int res = 0;
    int left = 0;
    int right = 0;
    int width = 0;
    for (int i : height) {
      if (left == 0 && i != 0) {
        left = i;
        continue;
      }
      if (i != 0) {
        right = i;
        res += Math.min(left, right) * width;
        left = 0;
        right = 0;
        width = 0;
        continue;
      }
      width++;
    }
    return res;
  }
}

package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author pipi
 * @since 2021/7/19 18:50
 */
public class No1838_最高频元素的频数 {

  public static void main(String[] args) {
    //
    final int i = new No1838_最高频元素的频数().maxFrequency(new int[] {1, 4, 8, 13}, 5);
    System.out.println(i);
  }

  public int maxFrequency(int[] nums, int k) {
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      int target = nums[i];
      int[] diffArr = new int[nums.length];
      for (int j = 0; j < nums.length; j++) {
        if (i == j) {
          diffArr[j] = 0;
          continue;
        }
        final int num = nums[j];
        if (target >= num) {
          int diff = target - num;
          diffArr[j] = diff;
        } else {
          diffArr[j] = -1;
        }
      }
      Arrays.sort(diffArr);
      int total = 0;
      int bits = 0;
      for (final int currentDiff : diffArr) {
        if (currentDiff == -1) {
          continue;
        }
        total += currentDiff;
        if (total > k) {
          break;
        }
        bits++;
      }
      max = Math.max(max, bits);
    }
    return max;
  }
}

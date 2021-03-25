package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author pipi
 * @since 2021/3/23 18:34
 */
public class 只出现一次的数字 {

  public static void main(String[] args) {
    System.out.println(new 只出现一次的数字().singleNumber1(new int[]{2, 2, 1, 1, 3}));
  }

  public int singleNumber(int[] nums) {
    Arrays.sort(nums);
    int len = nums.length;
    if (len == 1) {
      return nums[0];
    }
    if (nums[0] != nums[1]) {
      return nums[0];
    }
    for (int i = 1; i < len - 1; i++) {
      if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
        return nums[i];
      }
    }
    return nums[len - 1];
  }

  public int singleNumber1(int[] nums) {
    int reduce = 0;
    for (int num : nums) {
      reduce = reduce ^ num;
    }
    return reduce;
  }
}

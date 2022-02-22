package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2022/2/14 15:26
 */
public class No540_有序数组中的单一元素 {

  public static void main(String[] args) {
    System.out
        .println(new No540_有序数组中的单一元素().singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5}));
  }

  public int singleNonDuplicate(int[] nums) {
    if (nums.length < 2) {
      return nums[0];
    }

    for (int i = 0; i < nums.length; i += 2) {
      if (i+1 >= nums.length) {
        return nums[i];
      }
      if (nums[i] == nums[i + 1]) {
        continue;
      }
      return nums[i];
    }
    return 0;
  }
}

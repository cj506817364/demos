package com.ppj.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author pipi
 * @since 2021/3/23 18:22
 */
public class 存在重复元素 {

  public static void main(String[] args) {
    System.out.println(new 存在重复元素().containsDuplicate(new int[]{1, 2, 3, 1}));
  }

  public boolean containsDuplicate(int[] nums) {
    final HashSet<Object> objects = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      objects.add(nums[i]);
      if (objects.size() != i + 1) {
        return false;
      }
    }
    return true;
  }

  public boolean containsDuplicate2(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i-1]){
        return true;
      }
    }
    return false;
  }
}

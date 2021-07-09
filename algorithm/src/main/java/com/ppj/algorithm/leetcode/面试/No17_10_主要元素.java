package com.ppj.algorithm.leetcode.面试;

/**
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。
 *
 * <p>若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 * @author pipi
 * @since 2021/7/9 11:22
 */
public class No17_10_主要元素 {

  public static void main(String[] args) {
    //
    final int i = new No17_10_主要元素().majorityElement(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 1});
    System.out.println(i);
  }

  public int majorityElement(int[] nums) {
    int res = -1, count = 0;
    for (int num : nums) {
      if (count == 0) {
        res = num;
        count = 1;
        continue;
      }
      if (res == num) count++;
      else count--;
    }
    count = 0;
    for (int num : nums) {
      if (num == res) {
        count++;
      }
    }
    return count * 2 > nums.length ? res : -1;
  }
}

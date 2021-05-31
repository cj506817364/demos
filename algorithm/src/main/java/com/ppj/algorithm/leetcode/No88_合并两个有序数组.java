package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @since 2021/5/31 16:46
 * @author pipi
 */
public class No88_合并两个有序数组 {

  // 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
  // 输出：[1,2,2,3,5,6]
  public static void main(String[] args) {
    int[] num1 = new int[] {1, 2, 3, 0, 0, 0};
    int[] num2 = new int[] {2, 5, 6};
    new No88_合并两个有序数组().merge(num1, 3, num2, 3);
    System.out.println(Arrays.toString(num1));
  }

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    for (int i = 0; i < nums2.length; i++) {
      nums1[m + i] = nums2[i];
    }
    Arrays.sort(nums1);
  }
}

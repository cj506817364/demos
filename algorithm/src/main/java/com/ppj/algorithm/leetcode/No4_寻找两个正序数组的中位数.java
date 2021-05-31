package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * @author pipi
 * @since 2021/5/14 14:59
 */
public class No4_寻找两个正序数组的中位数 {

  public static void main(String[] args) {
    //
    System.out.println(
        new No4_寻找两个正序数组的中位数().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    final int len1 = nums1.length;
    final int len2 = nums2.length;

    final int len = len1 + len2;
    final boolean oushu = len % 2 == 0;
    List<Integer> nums = new ArrayList<Integer>();
    for (int num : nums1) {
      nums.add(num);
    }

    for (int num : nums2) {
      nums.add(num);
    }
    nums = nums.stream().sorted().collect(Collectors.toList());

    if (oushu){
      int mid1 = len / 2;
      int mid2 = mid1 + 1;
      return ((double)nums.get(mid1 - 1) + (double)nums.get(mid2 - 1)) / 2;
    }
    return nums.get(len / 2);
  }
}

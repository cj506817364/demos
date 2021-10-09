package com.ppj.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/9/8 16:27
 */
public class No215_数组中的第K个最大元素 {

  public static void main(String[] args) {
    int res = new No215_数组中的第K个最大元素().findKthLargest(new int[] {4, 3, 2, 3, 1, 2, 5, 5, 6}, 4);
    System.out.println(res);
  }

  public int findKthLargest(int[] nums, int k) {
    return Arrays.stream(nums)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList())
        .get(k - 1);
  }
}

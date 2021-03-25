package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pipi
 * @since 2021/3/23 18:48
 */
public class 两个数组的交集 {

  public static void main(String[] args) {
    final int[] intersect = new 两个数组的交集().intersect(new int[]{9, 4, 2}, new int[]{4, 2, 1});
    System.out.println(Arrays.toString(intersect));
  }

  public int[] intersect(int[] nums1, int[] nums2) {
    List<Integer> res = new ArrayList<>();
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int index = 0;
    for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
      final int n1 = nums1[i];
      final int n2 = nums2[j];
      if (n1 == n2) {
        res.add(nums1[i++]);
        j++;
        continue;
      }
      if (n1 < n2) {
        i++;
        continue;
      }
      j++;
    }
    return Arrays.stream(res.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
  }

}

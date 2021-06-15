package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/6/15 11:12
 */
public class No852_山脉数组的峰顶索引 {

  public static void main(String[] args) {
    //
    System.out.println(new No852_山脉数组的峰顶索引().peakIndexInMountainArray(new int[] {0, 1, 4, 3, 2}));
  }

  // 二分查找 [left,right]
  private int peakIndexInMountainArray(int[] arr) {
    int left = 0, right = arr.length - 2, ans = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] > arr[mid + 1]) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  // 普通遍历 时间复杂度 O(n)
  //  public int peakIndexInMountainArray(int[] arr) {
  //    if (arr.length == 1 || arr[0] > arr[1]) {
  //      return 0;
  //    }
  //    for (int i = 1; i < arr.length - 1; i++) {
  //      if (arr[i] > arr[i + 1] && arr[i] > arr[i - 1]) {
  //        return i;
  //      }
  //    }
  //    return arr.length - 1;
  //  }
}

package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/9/6 13:58
 */
public class No704_二分查找 {

  public static void main(String[] args) {
    System.out.println(new No704_二分查找().search(new int[] {-1, 0, 3, 5, 9, 12}, 2));
  }

  public int search(int[] nums, int target) {
    int low = 0;
    int height = nums.length - 1;
    while (low <= height) {
      final int middle = low + (height - low) / 2;
      if (nums[middle] == target) {
        return middle;
      } else if (nums[middle] > target) {
        height = middle - 1;
      } else {
        low = middle + 1;
      }
    }
    return -1;
  }
  //  public int search(int[] nums, int target) {
  //    int middle = nums.length / 2;
  //    int low = 0;
  //    int height = nums.length;
  //    return binarySearch(nums, target, middle, low, height);
  //  }
  //
  //  private int binarySearch(int[] nums, int target, int middle, int low, int height) {
  //    if (nums[middle] == target) {
  //      return middle;
  //    }
  //    if (low == middle || height == middle){
  //      return -1;
  //    }
  //    if (nums[middle] > target) {
  //      return binarySearch(nums, target, low + (middle - low) / 2, low, middle);
  //    } else {
  //      return binarySearch(nums, target, middle + (height - middle) / 2, middle, height);
  //    }
  //  }
}

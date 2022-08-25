package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/30 18:15
 */
public class No55_跳跃游戏 {

  public static void main(String[] args) {
    System.out.println(new No55_跳跃游戏().canJump(new int[]{2, 0, 0}));
  }

  public boolean canJump(int[] nums) {
    int n = nums.length;
    int rightmost = 0;
    for (int i = 0; i < n; ++i) {
      if (i <= rightmost) {
        rightmost = Math.max(rightmost, i + nums[i]);
        if (rightmost >= n - 1) {
          return true;
        }
      }
    }
    return false;
  }

//  // 不要跳中0即可¬
//  public boolean canJump(int[] nums) {
//    if (nums.length == 1 && nums[0] == 0){
//      return true;
//    }
//    for (int i = 0; i < nums.length; i++) {
//      if (nums[i] != 0) {
//        continue;
//      }
//      if (i == nums.length -1){
//        return true;
//      }
//      boolean over = true;
//      for (int j = i - 1; j >= 0; j--) {
//        final int size = i - j;
//        if (nums[j] > size) {
//          over = false;
//          break;
//        }
//      }
//      if (over) {
//        return false;
//      }
//    }
//    return true;
//  }

}

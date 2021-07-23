package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/7/23 11:51
 */
public class No1893_检查是否区域内所有整数都被覆盖 {

  public static void main(String[] args) {
    //
    int[][] ranges = new int[][] {{1, 2}, {3, 4}, {5, 6}};
    int left = 2;
    int right = 5;
    final boolean covered = new No1893_检查是否区域内所有整数都被覆盖().isCovered(ranges, left, right);
    System.out.println(covered);
  }

  // 差分数组
  public boolean isCovered(int[][] ranges, int left, int right) {
    int[] diff = new int[52];
    for (int[] range : ranges) {
      diff[range[0]]++;
      diff[range[1] + 1]--;
    }
    int[] sum = new int[52];
    for (int i = 1; i < diff.length; i++) {
      sum[i] = sum[i - 1] + diff[i];
    }
    for (int i = left; i <= right; i++) {
      if (sum[i] <= 0) return false;
    }
    return true;
  }

//  public boolean isCovered(int[][] ranges, int left, int right) {
//    int flag = left;
//    while (flag <= right) {
//      boolean contains = false;
//      for (int[] range : ranges) {
//        if (flag >= range[0] && flag <= range[1]) {
//          contains = true;
//          break;
//        }
//      }
//      flag++;
//      if (!contains) {
//        return false;
//      }
//    }
//    return true;
//  }
//  }

}
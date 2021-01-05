package com.ppj.algorithm.leetcode;


/**
 * @author pipi
 * @date 2021/1/5 16:10
 */
public class Q1004 {

  public static void main(String[] args) {
    System.out.print(new Q1004()
        .longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
  }

  // 滑动窗口解题
  public int longestOnes(int[] A, int K) {
    int count = 0;// 窗口内0的个数
    int left = 0;
    int right = 0;
    int maxRes = 0;
    while (right < A.length) {
      if (A[right++] == 0) {
        count++;
      }

      while (count > K) {
        if (A[left++] == 0) {
          count--;
        }
      }
      maxRes = Math.max(maxRes, right - left);
    }
    return maxRes;
  }
}

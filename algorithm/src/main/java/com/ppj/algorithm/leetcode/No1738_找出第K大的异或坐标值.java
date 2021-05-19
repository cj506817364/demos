package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为m x n 由非负整数组成。
 *
 * <p>矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0
 * 开始计数）执行异或运算得到。
 *
 * <p>请你找出matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 * <p>
 *
 * <p>示例 1：
 *
 * <p>输入：matrix = [[5,2],[1,6]], k = 1 输出：7 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。 示例 2：
 *
 * <p>输入：matrix = [[5,2],[1,6]], k = 2 输出：5 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 *
 * @author pipi
 * @since 2021/5/19 10:21
 */
public class No1738_找出第K大的异或坐标值 {

  public static void main(String[] args) {
    int[][] matrix = new int[][] {{5, 2}, {1, 6}};
    int k = 1;
    System.out.println(new No1738_找出第K大的异或坐标值().kthLargestValue(matrix, k));
  }

  public int kthLargestValue(int[][] matrix, int k) {
    int m = matrix.length, n = matrix[0].length;
    int[][] xor = new int[m + 1][n + 1];
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i < xor.length; i++) {
      for (int j = 1; j < xor[i].length; j++) {
        xor[i][j] = xor[i][j - 1] ^ xor[i - 1][j] ^ xor[i - 1][j - 1] ^ matrix[i - 1][j - 1];
        list.add(xor[i][j]);
      }
    }
    list.sort(Comparator.comparing(o -> -o));
    return list.get(k - 1);
  }

  //  public int kthLargestValue(int[][] matrix, int k) {
  //    int m = matrix.length, n = matrix[0].length;
  //    for (int i = 0; i < m; i++) {
  //      for (int j = 1; j < n; j++) {
  //        matrix[i][j] ^= matrix[i][j - 1];
  //      }
  //    }
  //
  //    for (int i = 1; i < m; i++) {
  //      for (int j = 0; j < n; j++) {
  //        matrix[i][j] ^= matrix[i - 1][j];
  //      }
  //    }
  //    List<Integer> list = new ArrayList<>();
  //    for (int[] ints : matrix) {
  //      for (int anInt : ints) {
  //        list.add(anInt);
  //      }
  //    }
  //    list.sort(Comparator.comparing(o -> -o));
  //    return list.get(k - 1);
  //  }
}

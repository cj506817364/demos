package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cj
 * @since 2022/1/4 11:50
 */
public class No54_螺旋矩阵 {


  public static void main(String[] args) {
    System.out.println(new No54_螺旋矩阵().spiralOrder(new int[][]{
        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
    }));
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    int height = matrix.length;
    int width = matrix[0].length;
    for (int i = 1; i <= height * width; i++) {
      if (i <= width) {
        res.add(matrix[0][i]);
        continue;
      }
      // i > width
      if (i < width + height) {

      }
    }
    return res;
  }
}

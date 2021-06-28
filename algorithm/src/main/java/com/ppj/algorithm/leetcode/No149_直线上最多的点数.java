package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/6/24 14:56
 */
public class No149_直线上最多的点数 {

  public static void main(String[] args) {
    //
    int[][] points = new int[][] {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
    System.out.println(new No149_直线上最多的点数().maxPoints(points));
  }

  public int maxPoints(int[][] points) {
    int max = 1;
    final int len = points.length;
    for (int i = 0; i < len; i++) {
      int[] p1 = points[i];
      for (int j = i + 1; j < len; j++) {
        int[] p2 = points[j];
        int tempMax = 2;
        // p1 -> p2 斜率 与p1 -> p3 相等
        // (y2-y1)/(x2-x1) = (y3-y1)/(x3-x1)
        // 变形: (y2-y1)*(x3-x1)=(y3-y1)*(x2-x1)
        for (int k = j + 1; k < len; k++) {
          int[] p3 = points[k];
          if ((p2[1] - p1[1]) * (p3[0] - p1[0]) == (p3[1] - p1[1]) * (p2[0] - p1[0])) {
            tempMax++;
          }
        }
        max = Math.max(max, tempMax);
      }
    }
    return max;
  }
}

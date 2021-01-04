package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author pipi
 * @date 2020/12/30 18:47
 */
public class Q1706 {

  public static void main(String[] args) {
    final int[] res = new Q1706().findBall(
        new int[][]{{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1},
            {-1, -1, -1, -1, -1}});
    System.out.println(Arrays.toString(res));
  }

  public int[] findBall(int[][] grid) {
    int[] res = new int[grid[0].length];
    init(res);
    for (int i = 0; i < grid.length; i++) {
      final int[] row = grid[i];
      for (int j = 0; j < row.length; j++) {
        int index = findBallIndexByIndex(res, j);
        if (index == -1) {
          continue;
        }
        if (j + 1 < row.length && row[j] == 1 && row[j + 1] == -1) {
          res[index] = -1;
        } else if (j - 1 >= 0 && row[j] == -1 && row[j - 1] == 1) {
          res[index] = -1;
        } else {
          res[index] = res[index] + row[j];
        }
      }

    }
    return res;
  }

  private int findBallIndexByIndex(int[] res, int j) {
    for (int i = 0; i < res.length; i++) {
      if (res[i] == j) {
        return i;
      }
    }
    return -1;
  }

  private void init(int[] res) {
    for (int i = 0; i < res.length; i++) {
      res[i] = i;
    }
  }
}

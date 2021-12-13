package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2021/12/13 16:56
 */
public class No807_保持城市天际线 {

  public static void main(String[] args) {

    final int res = new No807_保持城市天际线().maxIncreaseKeepingSkyline(new int[][]{
        {3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}
    });
    System.out.println(res);
  }

  public int maxIncreaseKeepingSkyline(int[][] grid) {
    int w = grid.length, h = grid[0].length;
    int[] wMax = new int[w], hMax = new int[h];
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        final int thisVal = grid[i][j];
        wMax[i] = Math.max(thisVal, wMax[i]);
        hMax[j] = Math.max(thisVal, hMax[j]);
      }
    }
    // wMax hMax 都已经准备好了, 计算差值
    int ans = 0;
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        final int thisVal = grid[i][j];
        ans += Math.min(wMax[i], hMax[j]) - thisVal;
      }
    }
    return ans;
  }
}

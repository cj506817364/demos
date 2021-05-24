package com.ppj.algorithm.leetcode;

/**
 * 动态规划: 每一步 都和 上一步结果有关的场景
 *
 * @author pipi
 * @since 2021/5/24 10:20
 */
public class No664_奇怪的打印机 {

  public static void main(String[] args) {
    System.out.println(new No664_奇怪的打印机().strangePrinter("aaabbb"));
  }

  public int strangePrinter(String s) {
    final int len = s.length();
    // dp[i][j] 表示区间[i,j]最少的打印次数
    int[][] dp = new int[len][len];
    for (int i = len - 1; i >= 0; i--) {
      dp[i][i] = 1;
      for (int j = i + 1; j < len; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          // s[i] == s[j] dp[i][j] = dp[i][j - 1]
          dp[i][j] = dp[i][j - 1];
        } else {
          int minn = Integer.MAX_VALUE;
          for (int k = i; k < j; k++) {
            minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
          }
          dp[i][j] = minn;
        }
      }
    }
    return dp[0][len - 1];
  }
}

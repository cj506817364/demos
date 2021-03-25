package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/3/23 17:24
 */
public class 买卖股票的最佳时机 {

  public static void main(String[] args) {
    System.out.println(new 买卖股票的最佳时机().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
  }

  public int maxProfit(int[] prices) {
    return maxProfit动态规划(prices);
  }

  /**
   * dp[i]表示第i天手里没有股票能获得的最大利润
   *
   * @param prices
   * @return
   */
  public int maxProfit动态规划(int[] prices) {
    int total = 0;
    for (int i = 1; i < prices.length; i++) {
      final int sub = prices[i] - prices[i - 1];
      if (sub > 0) {
        total += sub;
      }
    }
    return total;
  }
}

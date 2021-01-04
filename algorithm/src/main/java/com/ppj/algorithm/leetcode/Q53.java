package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @date 2020/12/30 11:31
 */
public class Q53 {

  int n;
  int[] cards;
  int ans = 0;

  public static void main(String[] args) {
    System.out.println(new Q53().cardPermutation(11));
  }

  public int cardPermutation(int n) {
    this.n = n;
    this.cards = new int[n * 2];
    dfs(1);
    return ans;
  }

  public void dfs(int num) {
    if (num == n + 1) {
      ++ans;
      return;
    }

    for (int i = 0; i + num + 1 < n * 2; ++i) {
      // 枚举两张数字为 num 的牌的放入空位，它们的间隔为 num
      if (cards[i] == 0 && cards[i + num + 1] == 0) {
        cards[i] = cards[i + num + 1] = num;
        dfs(num + 1);
        cards[i] = cards[i + num + 1] = 0;
      }
    }
  }
}

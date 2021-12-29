package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/17 18:10
 */
public class N1137_第N个泰波那契数 {

  public static void main(String[] args) {
    System.out.println(new N1137_第N个泰波那契数().tribonacci(25));
  }

  public int tribonacci(int n) {
    if (n <= 1) {
      return n;
    }
    if (n == 2) {
      return 1;
    }
    int n_3 = 0, n_2 = 0, n_1 = 1, n_0 = 1;
    for (int i = 3; i <= n; i++) {
      n_3 = n_2;
      n_2 = n_1;
      n_1 = n_0;
      n_0 = n_1 + n_2 + n_3;
    }
    return n_0;
  }

}

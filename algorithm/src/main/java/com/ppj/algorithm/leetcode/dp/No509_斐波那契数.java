package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/17 17:38
 */
public class No509_斐波那契数 {

  public static void main(String[] args) {
    System.out.println(new No509_斐波那契数().fib(4));
  }

  public int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }
}

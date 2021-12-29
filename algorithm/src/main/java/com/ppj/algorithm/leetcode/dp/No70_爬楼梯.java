package com.ppj.algorithm.leetcode.dp;

/**
 * @author cj
 * @since 2021/12/29 11:50
 */
public class No70_爬楼梯 {

  public static void main(String[] args) {
    System.out.println(new No70_爬楼梯().climbStairs(10));
  }

  public int climbStairs(int n) {
    int s1 = 0, s2 = 0, s3 = 1;
    for (int index = 1; index <= n; ++index) {
      s1 = s2;
      s2 = s3;
      s3 = s1 + s2;
    }
    return s3;
  }

}

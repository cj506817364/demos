package com.ppj.algorithm.leetcode;

/**
 *
 * @since 2021/5/31 12:20
 * @author pipi
 */
public class No231_2的幂 {

  public static void main(String[] args) {
    new No231_2的幂().isPowerOfTwo(4);
  }

//  public boolean isPowerOfTwo(int n) {
//    return n>0 && (n & (n-1)) == 0;
//  }

  public boolean isPowerOfTwo(int n) {
    final String s = Integer.toBinaryString(n);
    final int i = s.indexOf('1');
    return n > 0 &&  i != -1 &&  i == s.lastIndexOf('1');
  }
}

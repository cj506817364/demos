package com.ppj.algorithm.leetcode;

/**
 *
 * @since 2021/5/31 12:24
 * @author pipi
 */
public class No342_4的幂 {

  public static void main(String[] args) {
    System.out.println(new No342_4的幂().isPowerOfFour(4));
  }

  public boolean isPowerOfFour(int n) {
    if(n == 1){
      return true;
    }
    final String bin = Integer.toBinaryString(n);
    final int beginIndex = bin.indexOf('1');
    return n >= 4 && beginIndex != -1 && beginIndex == bin.lastIndexOf('1') && bin.substring(beginIndex + 1).length() % 2 == 0;
  }
}

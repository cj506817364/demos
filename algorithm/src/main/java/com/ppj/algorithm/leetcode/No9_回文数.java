package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 25/8/22 9:59 上午
 */
public class No9_回文数 {

  public static void main(String[] args) {
    final boolean res = new No9_回文数()
        .isPalindrome(121);
    System.out.println(res);
  }

  public boolean isPalindrome(int x) {
    final char[] chars = String.valueOf(x).toCharArray();
    int j = chars.length - 1;
    for (int i = 0; i < j; i++, j--) {
      if (chars[i] != chars[j]) {
        return false;
      }
    }
    return true;
  }

}

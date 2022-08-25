//package com.ppj.algorithm.leetcode;
//
///**
// * @author cj
// * @since 2022/2/14 15:33
// */
//public class No5_最长回文子串 {
//
//  public static void main(String[] args) {
//    System.out.println(new No5_最长回文子串().longestPalindrome("asasa"));
//  }
//
//  /**
//   * p(i,j) = p(i+1,j-1)&(Si==Sj)
//   * p(i,i) = true
//   * p(i,i+1) = (Si == Si+1)
//   * @param s 字符串
//   * @return
//   */
//  public String longestPalindrome(String s) {
//    final int len = s.length();
//    if (len == 1) {
//      return s;
//    }
//    int maxLen = 1;
//    int begin = 0;
//    // dp[i][j]表示 s[i~j] 是否为回文子串
//    boolean[][] dp = new boolean[len][len];
//    for (int i = 0; i < len; i++) {
//      dp[i][i] = true;
//    }
//
//    final char[] chars = s.toCharArray();
//    for (int L = 2; L <= len; L++) {
//      for (int i = 0; i < len; i++) {
//
//      }
//    }
//
//  }
//
//}

package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/9/7 14:54
 */
public class No1221_分割平衡字符串 {

  public static void main(String[] args) {
    System.out.println(new No1221_分割平衡字符串().balancedStringSplit("RLRRLLRLRL"));
  }

  public int balancedStringSplit(String s) {
    int num = 0;
    int count = 0;
    for (char c : s.toCharArray()) {
      switch (c) {
        case 'R':
          num++;
          break;
        case 'L':
          num--;
          break;
        default:
      }
      if (num == 0) {
        count++;
      }
    }
    return count;
  }
}

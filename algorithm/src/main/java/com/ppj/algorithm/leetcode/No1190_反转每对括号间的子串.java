package com.ppj.algorithm.leetcode;

/**
 * @since 2021/5/26 10:24
 * @author pipi
 */
public class No1190_反转每对括号间的子串 {

  public static void main(String[] args) {
    System.out.println(new No1190_反转每对括号间的子串().reverseParentheses("ta()usw((((a))))"));
  }

  public String reverseParentheses(String s) {
    if (s == null) {
      return s;
    }
    while (s.indexOf('(') != -1) {
      s = rev(s);
    }
    return s;
  }

  private String rev(String s) {
    final int start = s.lastIndexOf('(');
    final int end = s.indexOf(')',start);
    final String s1 = s.substring(0, start);
    String s2 = s.substring(start + 1, end);
    s2 = s2.isEmpty() ? s2 : new StringBuffer(s2).reverse().toString();
    final String s3 = s.substring(end + 1);
    return s1 + s2 + s3;
  }
}

package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/10/9 10:20
 */
public class No434_字符串中的单词数 {

  public static void main(String[] args) {
    System.out.println(new No434_字符串中的单词数().countSegments("Hello, my name is John"));
  }

  //  public int countSegments(String s) {
  //    final String[] split = s.split("\\s");
  //    return (int) Arrays.stream(split).filter(c -> !Objects.equals(c, "")).count();
  //  }

  public int countSegments(String s) {
    final char empty = " ".toCharArray()[0];
    int total = 0;
    boolean f = true;
    for (char c : s.toCharArray()) {
      if (c == empty) {
        f = true;
      } else if (f) {
        total++;
        f = false;
      }
    }
    return total;
  }
}

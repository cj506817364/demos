package com.ppj.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cj
 * @since 2022/3/2 19:39
 */
public class TestStringNoReplay {

  public static void main(String[] args) {
    System.out.println(cal("abcabcbbcdefgh"));
  }
  // 可以使用滑动窗口优化
  private static int cal(String str) {
    int max = 0;
    Set<Character> set = new HashSet<>();
    // 长度
    final int len = str.length();
    for (int i = 0; i < len; i++) {
      int current = 0;
      set.clear();
      for (int j = i; j < len; j++) {
        if (set.contains(str.charAt(j))) {
          break;
        }
        set.add(str.charAt(j));
        current++;
      }
      max = Math.max(max, current);
    }
    return max;
  }

}

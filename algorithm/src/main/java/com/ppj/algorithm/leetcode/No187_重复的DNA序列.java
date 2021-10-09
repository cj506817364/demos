package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/10/8 13:39
 */
public class No187_重复的DNA序列 {

  public static void main(String[] args) {
//    final String s = "AAAAAAAAAAA";
    final String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
    final List<String> res =
        new No187_重复的DNA序列().findRepeatedDnaSequences(s);
    System.out.println(res);
  }

  static final int L = 10;
  Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
    put('A', 0);
    put('C', 1);
    put('G', 2);
    put('T', 3);
  }};

  // 位运算, 节省空间
  public List<String> findRepeatedDnaSequences(String s) {
    List<String> ans = new ArrayList<>();
    int n = s.length();
    if (n <= L) {
      return ans;
    }
    int x = 0;
    for (int i = 0; i < L - 1; ++i) {
      x = (x << 2) | bin.get(s.charAt(i));
    }

    Map<Integer, Integer> cnt = new HashMap<>();
    for (int i = 0; i <= n - L; ++i) {
      x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
      cnt.put(x, cnt.getOrDefault(x, 0) + 1);
      if (cnt.get(x) == 2) {
        ans.add(s.substring(i, i + L));
      }
    }
    return ans;
  }

//  枚举法
//  public List<String> findRepeatedDnaSequences(String s) {
//    int len = 10;
//    Map<String, Integer> cntMap = new HashMap<>();
//    for (int i = 0; i <= s.length() - len; i++) {
//      String sub = s.substring(i, len + i);
//      cntMap.put(sub, cntMap.getOrDefault(sub, 0) + 1);
//    }
//    return cntMap.entrySet().stream()
//        .filter(k -> k.getValue() > 1)
//        .map(Entry::getKey)
//        .collect(Collectors.toList());
//  }
}

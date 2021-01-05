package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pipi
 * @date 2021/1/5 16:10
 */
public class Q830 {

  public static void main(String[] args) {
    List<List<Integer>> res = new Q830()
        .largeGroupPositions("aaa");
    System.out.print(res);
  }

  public List<List<Integer>> largeGroupPositions(String s) {
    List<List<Integer>> ret = new ArrayList<>();
    int n = s.length();
    int num = 1;
    for (int i = 0; i < n; i++) {
      if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
        if (num >= 3) {
          ret.add(Arrays.asList(i - num + 1, i));
        }
        num = 1;
      } else {
        num++;
      }
    }
    return ret;
  }
}

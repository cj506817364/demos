package com.ppj.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pipi
 * @since 2021/5/14 11:48
 */
public class No3_无重复字符的最长子串 {

  public static void main(String[] args) {
    System.out.println(new No3_无重复字符的最长子串().lengthOfLongestSubstring("abcabcbb"));
  }


  public int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    final char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      int len = 1;
      Set<Character> cSet = new HashSet<>();
      cSet.add(chars[i]);
      for (int j = i + 1; j < chars.length; j++) {
        if (cSet.contains(chars[j])) {
          break;
        }
        len++;
        cSet.add(chars[j]);
      }
      maxLen = Math.max(len, maxLen);
    }
    return maxLen;
  }

//  public int lengthOfLongestSubstring(String s) {
//    int maxLen = 0;
//    final char[] chars = s.toCharArray();
//    for (int i = 0; i < chars.length; i++) {
//      if (maxLen >= chars.length - i) {
//        break;
//      }
//      Set<Character> subChar = new HashSet<>();
//      int len = 0;
//      for (int j = i; j < chars.length; j++) {
//        if (subChar.contains(chars[j])) {
//          break;
//        }
//        len++;
//        subChar.add(chars[j]);
//      }
//      if (len > maxLen) {
//        maxLen = len;
//      }
//    }
//    return maxLen;
//  }
}

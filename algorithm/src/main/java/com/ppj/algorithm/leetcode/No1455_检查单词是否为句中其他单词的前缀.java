package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 25/8/22 9:56 上午
 */
public class No1455_检查单词是否为句中其他单词的前缀 {

  public static void main(String[] args) {
    final int prefixOfWord = new No1455_检查单词是否为句中其他单词的前缀()
        .isPrefixOfWord(
            "i love eating burger",
            "burger"
        );
    System.out.println(prefixOfWord);
  }

  public int isPrefixOfWord(String sentence, String searchWord) {
    final String[] s = sentence.split(" ");
    for (int i = 0; i < s.length; i++) {
      if (s[i].startsWith(searchWord)) {
        return i + 1;
      }
    }
    return -1;
  }
}

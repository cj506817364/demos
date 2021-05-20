package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给一非空的单词列表，返回前k个出现次数最多的单词。
 *
 * <p>返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * <p>示例 1：
 *
 * <p>输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 输出: ["i", "love"] 解析: "i" 和 "love"
 * 为出现次数最多的两个单词，均为2次。 注意，按字母顺序 "i" 在 "love" 之前。
 *
 * @author pipi
 * @since 2021/5/20 10:29
 */
public class No692_前K个高频单词 {
  public static void main(String[] args) {
    System.out.println(
        new No692_前K个高频单词()
            .topKFrequent(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2));
  }

  public List<String> topKFrequent(String[] words, int k) {
    List<String> sList = new ArrayList<>();
    final String sep = "_";
    Arrays.stream(words)
        .collect(Collectors.groupingBy(a -> a))
        .forEach((key, v) -> sList.add(v.size() + sep + key));
    sList.sort(
        (o1, o2) -> {
          final String[] split1 = o1.split(sep);
          final String[] split2 = o2.split(sep);
          final int count1 = Integer.parseInt(split1[0]);
          final int count2 = Integer.parseInt(split2[0]);
          if (count1 != count2) {
            return count2 - count1;
          }
          return split1[1].compareTo(split2[1]);
        });
    return sList.subList(0, k).stream().map(str -> str.split(sep)[1]).collect(Collectors.toList());
  }
}

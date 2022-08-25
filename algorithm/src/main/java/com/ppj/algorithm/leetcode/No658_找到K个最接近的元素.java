package com.ppj.algorithm.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * <p>
 * 示例 1：
 *
 * @author cj
 * @since 25/8/22 9:31 上午
 */
public class No658_找到K个最接近的元素 {

  public static void main(String[] args) {
    final List<Integer> res = new No658_找到K个最接近的元素()
        .findClosestElements(
            new int[]{1, 2, 3, 4, 5},
            4,
            3
        );
    System.out.println(res);
  }

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    if (arr.length <= k) {
      return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
    final List<Integer> collect = Arrays.stream(arr).boxed().sorted((a, b) -> {
      final int absA = Math.abs(a - x);
      final int absB = Math.abs(b - x);
      final int compare = Integer.compare(absA, absB);
      if (compare == 0) {
        return Integer.compare(a, b);
      }
      return compare;
    }).collect(Collectors.toList());
    // 查找absList中K个最小的数字
    return collect.subList(0, k).stream().sorted().collect(Collectors.toList());
  }
}

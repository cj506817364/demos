package com.ppj.algorithm.leetcode.lcp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/7/1 11:33
 */
public class No07_传递信息 {

  /**
   * 5 [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]] 3
   *
   * @param args
   */
  public static void main(String[] args) {
    final int i = new No07_传递信息()
            .numWays(5, new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}}, 3);
    System.out.println(i);
  }

  public int numWays(int n, int[][] relation, int k) {
    final Map<Integer, List<int[]>> collect =
        Arrays.stream(relation).collect(Collectors.groupingBy(arr -> arr[0]));
    Queue<Integer> queue = new LinkedList<>();
    // 初始化队列
    queue.add(0);
    while (k-- > 0) {
      int size = queue.size();
      while (size-- > 0) {
        final Integer peek = queue.poll();
        final List<int[]> ints = collect.get(peek);
        if (ints == null) {
          continue;
        }
        for (int[] anInt : ints) {
          queue.add(anInt[1]);
        }
      }
    }

    int res = 0;
    while (!queue.isEmpty()) {
      if (queue.poll() == n - 1) {
        res++;
      }
    }
    return res;
  }

}

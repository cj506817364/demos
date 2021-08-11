package com.ppj.algorithm.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 *
 * <p>给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 *
 * @author pipi
 * @since 2021/8/9 11:24
 */
public class No313_超级丑数 {

  public static void main(String[] args) {
    final int i = new No313_超级丑数().nthSuperUglyNumber(12, new int[] {2, 7, 13, 19});
    System.out.println(i);
  }

  /**
   * 对于任意一个丑数, 月primes[n]相乘, 结果仍为丑数
   * 1 是最小的丑数
   * @param n
   * @param primes
   * @return
   */
  public int nthSuperUglyNumber(int n, int[] primes) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    queue.offer(1);
    final HashSet<Integer> tempSet = new HashSet<>();
    tempSet.add(1);
    while (n-- > 0) {
      final Integer poll = queue.poll();
      for (int prime : primes) {
        if (!tempSet.contains(poll)){
          tempSet.add(prime * poll);
        }
      }
    }
    return queue.poll();
  }


}

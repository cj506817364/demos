package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果x == y，那么两块石头都会被完全粉碎；
 * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pipi
 * @date 2020/12/30 12:21
 */
public class Q1046 {

  public static void main(String[] args) {
    final int i = new Q1046().lastStoneWeight(new int[]{1, 1, 2, 2, 3, 3});
    System.out.println(i);
  }

  public int lastStoneWeight(int[] stones) {
    while (stones.length > 1) {
      Arrays.sort(stones);
      int dis = stones[stones.length - 1] - stones[stones.length - 2];
      if (dis == 0) {
        stones = Arrays.copyOfRange(stones, 0, stones.length - 2);
      } else {
        stones = Arrays.copyOfRange(stones, 0, stones.length - 1);
        stones[stones.length - 1] = dis;
      }
    }
    return stones.length == 0 ? 0 : stones[0];
  }
}

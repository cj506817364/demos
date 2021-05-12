package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
 *
 * <p>你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
 *
 * <p>将第 i 个筹码向左或者右移动 2 个单位，代价为 0。 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
 *
 * <p>返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
 *
 * @author pipi
 * @since 2021/5/12 18:24
 */
public class No1217_玩筹码 {

  public static void main(String[] args) {
    System.out.println(new No1217_玩筹码().minCostToMoveChips(new int[] {2, 3, 3}));
  }

  // f(i)表示将 所有 筹码移动到i处所需要的代价
  public int minCostToMoveChips(int[] position) {
    int[] posArr = Arrays.stream(position).distinct().toArray();
    int[] f = new int[posArr.length];
    for (int i = 0; i < f.length; i++) {
      f[i] = cal(position, posArr[i]);
    }
    return Arrays.stream(f).min().getAsInt();
  }

  private int cal(int[] position, int i) {
    int chip = 0;
    for (int pos : position) {
      if ((pos - i) % 2 != 0) {
        chip += 1;
      }
    }
    return chip;
  }
}

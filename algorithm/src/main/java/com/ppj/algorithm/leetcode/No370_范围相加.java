package com.ppj.algorithm.leetcode;

import cn.hutool.core.util.ArrayUtil;

/**
 * length = 5,
 * updates = [
 * [1,  3,  2],
 * [2,  4,  3],
 * [0,  2, -2]
 * ]
 *
 * @author cj
 * @since 2022/1/29 11:52
 */
public class No370_范围相加 {

  public static void main(String[] args) {
    int len = 5;
    int[][] array = new int[][]{
        {1, 3, 2},
        {2, 4, 3},
        {0, 2, -2},
    };

    final int[] ints = new No370_范围相加().rangeAddition(len, array);
    System.out.println(ArrayUtil.toString(ints));
  }

  public int[] rangeAddition(int len, int[][] array) {
    int[] res = new int[len];
    for (int[] ints : array) {
      for (int i = ints[0]; i <= ints[1]; i++) {
        res[i] += ints[2];
      }
    }
    return res;
  }
}

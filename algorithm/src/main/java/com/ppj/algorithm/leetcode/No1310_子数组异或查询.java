package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。
 *
 * <p>对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。
 *
 * <p>并返回一个包含给定查询 queries 所有结果的数组。
 *
 * <p>示例 1：
 *
 * <p>输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]] 输出：[2,7,14,8] 解释： 数组中元素的二进制表示形式是： 1 =
 * 0001 3 = 0011 4 = 0100 8 = 1000 查询的 XOR 值为： [0,1] = 1 xor 3 = 2 [1,2] = 3 xor 4 = 7 [0,3] = 1 xor
 * 3 xor 4 xor 8 = 14 [3,3] = 8
 *
 * @author pipi
 * @since 2021/5/12 16:24
 */
public class No1310_子数组异或查询 {

  public static void main(String[] args) {
    //
    System.out.println(
        Arrays.toString(
            new No1310_子数组异或查询()
                .xorQueries(new int[] {1, 3, 4, 8}, new int[][] {{0, 1}, {1, 2}, {0, 3}, {3, 3}})));
  }

  public int[] xorQueries(int[] arr, int[][] queries) {
    int[] resArr = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      final int[] query = queries[i];
      int beginIndex = query[0];
      int res = arr[beginIndex];
      for (int j = beginIndex + 1; j <= query[1]; j++) {
        res ^= arr[j];
      }
      resArr[i] = res;
    }
    return resArr;
  }
}

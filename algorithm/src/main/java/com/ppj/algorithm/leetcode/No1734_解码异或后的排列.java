package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 *
 * <p>它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm =
 * [1,3,2] ，那么 encoded = [2,1] 。
 *
 * <p>给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 *
 * <p>示例 1：
 *
 * <p>输入：encoded = [3,1] 输出：[1,2,3] 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1] 示例
 * 2：
 *
 * <p>输入：encoded = [6,5,4,6] 输出：[2,4,1,5,3]
 *
 * @author pipi
 * @since 2021/5/11 17:42
 */
public class No1734_解码异或后的排列 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new No1734_解码异或后的排列().decode(new int[] {3, 1})));
  }



  public int[] decode(int[] encoded) {
    int n = encoded.length + 1;
    int cal0_n = cal0_n(n);
    int calE1_En = calE1_En(encoded,n);
    int[] perm = new int[n];
    perm[0] = cal0_n^calE1_En;
    for (int i = 0; i < n - 1; i++) {
      perm[i + 1] = perm[i] ^ encoded[i];
    }
    return perm;
  }

  private int calE1_En(int[] encoded, int n) {
    int calE1_En = 0;
    for (int i = 1; i < n - 1; i += 2) {
      calE1_En ^= encoded[i];
    }
    return calE1_En;
  }

  private int cal0_n(int n) {
    int cal0_n = 0;
    for (int i = 1; i < n; i++) {
      cal0_n^=i;
    }
    return cal0_n;
  }
}

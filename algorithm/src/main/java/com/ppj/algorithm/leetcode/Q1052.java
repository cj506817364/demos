package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * [1,0,1,2,1,1,7,5]
 * [0,1,0,1,0,1,0,1]
 * 3
 *
 * @author pipi
 * @date 2020/12/30 18:00
 */
public class Q1052 {

  public static void main(String[] args) {
    final int res = new Q1052()
        .maxSatisfied(new int[]{10, 1, 7}, new int[]{0, 0, 0}, 2);
    System.out.println(res);
  }

  public int maxSatisfied(int[] customers, int[] grumpy, int X) {
    int max = 0;
    int total = 0;
    int sum = 0;
    if (X == customers.length) {
      return Arrays.stream(customers).sum();
    }

    for (int i = 0; i < customers.length; i++) {
      if (grumpy[i] == 0) {
        total += customers[i];
        customers[i] = 0;
      }
    }

    for (int i = 0; i < customers.length; i++) {
      sum += customers[i];
      if (i >= X - 1) {
        if (sum >= max) {
          max = sum;
        }
        sum -= customers[i - X + 1];
      }
    }

    return max + total;
  }

}

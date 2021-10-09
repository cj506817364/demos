package com.ppj.algorithm.leetcode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @since 2021/5/28 14:38
 * @author pipi
 */
public class No477_汉明距离总和 {

  public static void main(String[] args) {
    System.out.println(System.currentTimeMillis() / 1000);
    System.out.println(1574835097);
    int val = new No477_汉明距离总和().totalHammingDistance(new int[]{4,14,2});
    System.out.println(val);
  }

  public int totalHammingDistance(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        total += countBinary1(nums[i] ^ nums[j]);
      }
    }
    return total;
  }

  private int countBinary1(int n) {
    int count = 0;
    int flag = 1;
    while (flag != 0){
      if((n & flag) != 0){
        count++;
      }
      flag = flag << 1;
    }
    return count;
  }
}

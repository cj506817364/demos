package com.ppj.algorithm.leetcode;

import cn.hutool.core.lang.Assert;

/**
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 *
 * <p>子数组大小 至少为 2 ，且 子数组元素总和为 k 的倍数。 如果存在，返回 true ；否则，返回 false 。
 *
 * <p>如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 *
 * @author pipi
 * @since 2021/6/2 11:27
 */
public class No523_连续的子数组和 {

  public static void main(String[] args) {
    final boolean b = new No523_连续的子数组和().checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6);
    Assert.isTrue(b);
  }

  public boolean checkSubarraySum(int[] nums, int k) {
    for (int i = 0; i < nums.length; i++) {
      int temp = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        temp += nums[j];
        if (temp % k == 0) {
          return true;
        }
      }
    }
    return false;
  }
//  // 超出时间限制
//  public boolean checkSubarraySum(int[] nums, int k) {
//    for (int i = 0; i < nums.length; i++) {
//      int temp = nums[i];
//      for (int j = i + 1; j < nums.length; j++) {
//        temp += nums[j];
//        if (temp % k == 0) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
}

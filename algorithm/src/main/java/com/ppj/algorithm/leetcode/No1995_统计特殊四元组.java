package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2021/12/29 11:28
 */
public class No1995_统计特殊四元组 {

  public static void main(String[] args) {
    System.out.println(
        new No1995_统计特殊四元组().countQuadruplets(new int[]{28, 8, 49, 85, 37, 90, 20, 8}));
  }

//   枚举无敌
  public int countQuadruplets(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          for (int h = k + 1; h < nums.length; h++) {
            if (nums[i] + nums[j] + nums[k] == nums[h]) {
              total++;
            }
          }
        }
      }
    }
    return total;
  }


}

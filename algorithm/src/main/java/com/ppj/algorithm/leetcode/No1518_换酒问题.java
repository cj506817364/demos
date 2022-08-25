package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2021/12/17 09:59
 */
public class No1518_换酒问题 {

  public static void main(String[] args) {
    System.out.println(new No1518_换酒问题().numWaterBottles(2, 3));
  }

  public int numWaterBottles(int numBottles, int numExchange) {
    int drunkCount = numBottles, trunkCount = numBottles;
    while (trunkCount >= numExchange) {
      int tempTrunkCount = trunkCount / numExchange;
      trunkCount -= tempTrunkCount * numExchange;
      drunkCount += tempTrunkCount;
      trunkCount += tempTrunkCount;
    }
    return drunkCount;
  }
}

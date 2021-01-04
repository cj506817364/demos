package com.ppj.algorithm.geekbang;

/**
 * @author pipi
 * @date 2020/12/24 10:52
 */
public class FindMaxMulti {

  public static void main(String[] args) {
    System.out.println(new FindMaxMulti().almostIncreasingSequenceTZM(new int[]{1,3,2,1}));
  }

  // 1,4,2,3
  public boolean almostIncreasingSequenceTZM(int[] input_one) {
    if (input_one == null || input_one.length <= 2) {
      return true;
    }
    int errorCount = 0;
    for (int index = 1; index < input_one.length; index++) {
      if (input_one[index] >= input_one[index - 1]) {
        continue;
      }
      if (++errorCount >= 2) {
        return false;
      }
      // 如果比前面两个都小，那么就只有修改当前的值
      if ((index - 2) >= 0 && input_one[index] < input_one[index - 2]) {
        input_one[index] = input_one[index - 1];
        continue;
      }
      // 如果比之前的大，则可以修改当前的值
      input_one[index - 1] = input_one[index];
    }
    return true;
  }

}

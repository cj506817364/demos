package com.ppj.algorithm.leetcode;

/**
 * @author cj
 * @since 2022/2/28 10:34
 */
public class No1601_最多可达成的换楼请求数目 {

  public static void main(String[] args) {
    System.out.println(new No1601_最多可达成的换楼请求数目().maximumRequests(3, new int[][]{
//        {0, 0},
//        {1, 1},
//        {0, 0},
        {2, 0},
//        {2, 2},
//        {1, 1},
        {2, 1},
        {0, 1},
        {0, 1}
    }));
  }

  public int maximumRequests(int n, int[][] requests) {
    final int[] ints = new int[n];
    for (int[] request : requests) {
      ints[request[0]]--;
      ints[request[1]]++;
    }
    int res = requests.length;
    for (int anInt : ints) {
      if (anInt < 0) {
        res += anInt;
      }
    }
    return res;
  }
}

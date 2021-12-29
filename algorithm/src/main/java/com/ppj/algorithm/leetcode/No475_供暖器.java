package com.ppj.algorithm.leetcode;

import lombok.val;

/**
 * @author cj
 * @since 2021/12/20 09:48
 */
public class No475_供暖器 {

  public static void main(String[] args) {
    val res = 1+2+3+4.0;
    System.out.println(res);
    final int radius = new No475_供暖器().findRadius(new int[]{1, 2, 3}, new int[]{2});
    System.out.println(radius);
  }

  public int findRadius(int[] houses, int[] heaters) {

    int left = 0, right = (int) 1e9;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (check(houses, heaters, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return right;
  }

  public boolean check(int[] houses, int[] heaters, int dis) {
    int n = houses.length, m = heaters.length;
    for (int i = 0, j = 0; i < n; i++) {
      while (j < m && houses[i] > heaters[j] + dis) {
        j++;
      }
      if (j < m && heaters[j] - dis <= houses[i] && houses[i] <= heaters[j] + dis) {
        continue;
      }
      return false;
    }
    return true;
  }

}

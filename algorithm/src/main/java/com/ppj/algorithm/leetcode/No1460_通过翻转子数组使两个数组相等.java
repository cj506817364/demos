package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你两个长度相同的整数数组target和arr。每一步中，你可以选择arr的任意 非空子数组并将它翻转。你可以执行此过程任意次。
 * <p>
 * 如果你能让 arr变得与 target相同，返回 True；否则，返回 False 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cj
 * @since 24/8/22 12:00 下午
 */
public class No1460_通过翻转子数组使两个数组相等 {

  public static void main(String[] args) {
    final boolean res = new No1460_通过翻转子数组使两个数组相等()
        .canBeEqual(
            new int[]{1, 2, 3, 4},
            new int[]{2, 4, 1, 3}
        );
    System.out.println(res);
  }

  public boolean canBeEqual(int[] target, int[] arr) {
    if (target.length != arr.length) {
      return false;
    }
    Arrays.sort(target);
    Arrays.sort(arr);
    for (int i = 0; i < target.length; i++) {
      if (target[i] != arr[i]) {
        return false;
      }
    }
    return true;
  }
}

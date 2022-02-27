package com.ppj.algorithm.leetcode;

/**
 * 给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
 * <p>
 * 比方说，如果 nums = [1, 2, 3, 4] ：
 * [2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
 * [1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
 * 请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
 * <p>
 * nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 30
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-number-of-good-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cj
 * @since 2022/2/22 10:17
 */
public class No1994_好子集的数目 {

  int mod = (int) 1e9 + 7;
  // 1 <= nums[i] <= 30
  int[] p = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
  int[] cnts = new int[31];

  public static void main(String[] args) {
    System.out.println(new No1994_好子集的数目().numberOfGoodSubsets(new int[]{1, 2, 3, 4}));
  }

  public int numberOfGoodSubsets(int[] nums) {
    int n = nums.length;
    for (int i : nums) cnts[i]++;
    int mask = 1 << 10;
    long[] f = new long[mask];


    return 0;
  }


}

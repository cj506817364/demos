package com.ppj.algorithm;

/**
 * 题目1：
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例 1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 示例 3：
 * 输入：nums = [2,2,0,1,0]
 * 输出：true
 * 解释：从下标跳到下标3，再从下标3跳到最后。
 * <p>
 * 示例 4：
 * 输入：nums = [2,0,1,3,4]
 * 输出：true
 * 解释：从下标0跳到下标2，从下标3跳到下标3，下标3跳到最后。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 *
 * @author cj
 * @since 2022/3/3 19:06
 */
public class TestCode {

  public static void main(String[] args) {
    System.out.println(cal(new int[]{2, 3, 1, 1, 4}));
    System.out.println(cal(new int[]{3, 2, 1, 0, 4}));
    System.out.println(cal(new int[]{2, 2, 0, 1, 0}));
    System.out.println(cal(new int[]{2, 0, 1, 3, 4}));
  }

  private static boolean cal(int[] nums) {
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i > max) {
        return false;
      }
      // 再 位置i可以跳的最远距离
      max = Math.max(max, i + nums[i]);
      if (max > nums.length - 1) {
        return true;
      }
    }
    return true;
  }

}

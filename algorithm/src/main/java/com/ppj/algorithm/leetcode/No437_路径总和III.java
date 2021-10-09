package com.ppj.algorithm.leetcode;

import lombok.Builder;
import lombok.Data;

/**
 * @since 2021/9/28 12:14
 * @author pipi
 */
public class No437_路径总和III {

  public static void main(String[] args) {
    final TreeNode builder =
        builder(
            10,
            builder(5, builder(3, builder(3), builder(-2)), builder(2, null, builder(1))),
            builder(-3, null, builder(11)));
    final int i = pathSum(builder, 8);
    System.out.println(i);
  }

  public static int pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return 0;
    }
    int ret = rootSum(root, targetSum);
    ret += pathSum(root.left, targetSum);
    ret += pathSum(root.right, targetSum);
    return ret;
  }

  private static int rootSum(TreeNode root, int targetSum) {
    if (root == null) {
      return 0;
    }

    int ret = 0;

    int val = root.val;
    if (val == targetSum) {
      ret++;
    }

    ret += rootSum(root.left, targetSum - val);
    ret += rootSum(root.right, targetSum - val);
    return ret;
  }

  public static TreeNode builder(int val, TreeNode left, TreeNode right) {
    return TreeNode.builder().val(val).left(left).right(right).build();
  }

  public static TreeNode builder(int val) {
    return TreeNode.builder().val(val).build();
  }

  @Data
  @Builder
  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}

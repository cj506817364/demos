package com.ppj.algorithm.leetcode.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author pipi
 * @since 2021/5/17 11:22
 */
public class TreeNodeBuild {
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

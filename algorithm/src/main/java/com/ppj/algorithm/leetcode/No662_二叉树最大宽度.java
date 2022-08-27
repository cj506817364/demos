package com.ppj.algorithm.leetcode;

import com.ppj.algorithm.leetcode.common.TreeNodeBuild;

/**
 * @author cj
 * @since 27/8/22 9:58 上午
 */
public class No662_二叉树最大宽度 extends TreeNodeBuild {

  public static void main(String[] args) {
    final TreeNode builder = builder(1, builder(3, builder(5), builder(3)),
        builder(2, null, builder(9)));
    System.out.println(new No662_二叉树最大宽度().widthOfBinaryTree(builder));
  }

  public int widthOfBinaryTree(TreeNode root) {

    int level = countLevel(root);
    int width = 1;
    while (--level > 0) {
      width = width * 2;
    }
    return width;
  }

  private int countLevel(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(countLevel(root.getLeft()), countLevel(root.getRight())) + 1;
  }
}
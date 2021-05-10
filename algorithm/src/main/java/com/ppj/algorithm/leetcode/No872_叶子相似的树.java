package com.ppj.algorithm.leetcode;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/5/10 10:35
 */
public class No872_叶子相似的树 {

  public static void main(String[] args) {

    System.out.println(new No872_叶子相似的树().leafSimilar(buildNode1(), buildNode2()));
  }

  private static TreeNode buildNode2() {
    return builder(
        3,
        builder(5, builder(6), builder(7)),
        builder(1, builder(4), builder(2, builder(9), builder(8))));
  }

  private static TreeNode buildNode1() {
    return builder(
        3,
        builder(5, builder(6), builder(2, builder(7), builder(4))),
        builder(1, builder(9), builder(8)));
  }

  public static TreeNode builder(int val, TreeNode left, TreeNode right) {
    return TreeNode.builder().val(val).left(left).right(right).build();
  }

  public static TreeNode builder(int val) {
    return TreeNode.builder().val(val).build();
  }

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> root1ValList = new ArrayList<>();
    def(root1, root1ValList);
    System.out.println(root1ValList);
    List<Integer> root2ValList = new ArrayList<>();
    def(root2, root2ValList);
    System.out.println(root2ValList);
    return root1ValList.equals(root2ValList);
  }

  public void def(TreeNode node, List<Integer> valList) {
    if (node.left == null && node.right == null) {
      valList.add(node.val);
    } else {
      if (node.left != null) {
        def(node.left, valList);
      }
      if (node.right != null) {
        def(node.right, valList);
      }
    }
  }

  @Builder
  static class TreeNode {
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

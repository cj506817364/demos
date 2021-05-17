package com.ppj.algorithm.leetcode;

import cn.hutool.core.lang.Assert;
import com.ppj.algorithm.leetcode.common.TreeNodeBuild;
import lombok.extern.slf4j.Slf4j;

/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * <p>如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 *
 * <p>我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 *
 * <p>只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 * @author pipi
 * @since 2021/5/17 10:57
 */
@Slf4j
public class No993_二叉树的堂兄弟节点 extends TreeNodeBuild {

  int x;
  TreeNode xParent;
  int xDeep;

  int y;
  TreeNode yParent;
  int yDeep;

  public static void main(String[] args) {
    final boolean cousins = new No993_二叉树的堂兄弟节点().isCousins(buildNode1(), 5, 4);
    final boolean cousins1 = new No993_二叉树的堂兄弟节点().isCousins(buildNode2(), 4, 3);
    Assert.isTrue(cousins);
    Assert.isTrue(!cousins1);
  }

  private static TreeNode buildNode2() {
    return builder(1, builder(2, builder(4), null), builder(3));
  }

  private static TreeNode buildNode1() {
    return builder(1, builder(2, null, builder(4)), builder(3, null, builder(5)));
  }

  public boolean isCousins(TreeNode root, int x, int y) {
    // 深度相同, 父节点不同
    this.x = x;
    this.y = y;
    dfsX(root, 0, x, null);
    dfsY(root, 0, y, null);
    return xDeep == yDeep && xParent.getVal() != yParent.getVal();
  }

  private void dfsX(TreeNode root, int deep, int val, TreeNode parent) {
    if (root == null) {
      return;
    }
    if (root.getVal() == val) {
      this.xParent = parent;
      this.xDeep = deep;
      return;
    }
    dfsX(root.getLeft(), deep + 1, val, root);
    dfsX(root.getRight(), deep + 1, val, root);
  }

  private void dfsY(TreeNode root, int deep, int val, TreeNode parent) {
    if (root == null) {
      return;
    }
    if (root.getVal() == val) {
      this.yParent = parent;
      this.yDeep = deep;
      return;
    }
    dfsY(root.getLeft(), deep + 1, val, root);
    dfsY(root.getRight(), deep + 1, val, root);
  }
}

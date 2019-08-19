package com.ppj.algorithm.tree.binary;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeTraverse {

    /**
     * 前序遍历 递归
     *
     * @param root   根节点
     * @param result 结果
     */
    public static void preorder(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        result.add(root.val);

        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * 前序遍历 非递归
     *
     * @param root   根节点
     * @param result 结果
     */
    public static void preorderIter(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);

        }

    }

    /**
     * 中序遍历 递归
     *
     * @param root   根节点
     * @param result 结果
     */
    public static void inorder(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 中序遍历 递归
     *
     * @param root   根节点
     * @param result 结果
     */
    public static void inorderIter(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current.right);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);
            current = current.right;

        }
    }

    /**
     * 后序遍历 递归
     *
     * @param root   根节点
     * @param result 结果
     */
    public static void afterorder(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        afterorder(root.left, result);
        afterorder(root.right, result);
        result.add(root.val);
    }


}

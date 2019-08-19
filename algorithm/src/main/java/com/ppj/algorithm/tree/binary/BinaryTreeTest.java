package com.ppj.algorithm.tree.binary;

import java.util.LinkedList;
import java.util.List;
/**
 * 二叉树的性质：
 * 性质1：二叉树第i层上的结点数目最多为 2^{i-1} (i≥1)
 * 性质2：深度为k的二叉树至多有2^{k}-1个结点(k≥1)
 * 性质3：包含n个结点的二叉树的高度至少为log2 (n+1)
 * 性质4：若叶子结点的个数为n0，度为2的结点数为n2，则n0=n2+1
 * 完全二叉树:
 * 性质5：对于节点i,左子树的编号:2i,右子数的编号:2i-1
 *
 * 二叉树的遍历:
 * 1.前序遍历: 访问根结点的操作发生在遍历其左右子树之前。
 * 2.中序遍历: 访问根结点的操作发生在遍历其左右子树之中（间）。
 * 3.后序遍历: 访问根结点的操作发生在遍历其左右子树之后。
 */
public class BinaryTreeTest {


    public static void main(String[] args) {

//        String line1 = "[1,null,2,3]";
        String line1 = "[4,2,7,1,3,5,5,3,2]";

        TreeNode root = BinaryTreeUtil.stringToTreeNode(line1);
        BinaryTreeUtil.printNode(root);

        List<Integer> ret = BinaryTreeUtil.preorderTraversal(root);

        System.out.println(ret);
        LinkedList<Integer> result = new LinkedList<>();
        BinaryTreeTraverse.preorder(root, result);
        System.out.println("preorder: " + result);

        result = new LinkedList<>();
        BinaryTreeTraverse.preorderIter(root, result);
        System.out.println("preorderIter: " + result);

        result = new LinkedList<>();
        BinaryTreeTraverse.inorder(root, result);
        System.out.println("inorder: " + result);

        result = new LinkedList<>();
        BinaryTreeTraverse.afterorder(root, result);
        System.out.println("afterorder: " + result);

    }

}

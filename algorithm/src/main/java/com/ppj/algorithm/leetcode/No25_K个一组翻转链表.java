package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/9/6 18:59
 */
public class No25_K个一组翻转链表 {

  public static void main(String[] args) {

    final ListNode listNode =
        new No25_K个一组翻转链表()
            .reverseKGroup(
                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
                2);

    System.out.println(listNode);
  }

  public ListNode reverseKGroup(ListNode head, int k) {
    // 创建虚拟节点 用来实现节点替换的时候 不用单独去判断头结点
    ListNode virtualNode = new ListNode(0, head);

    // 计算操作次数
    int length = 0;
    ListNode cur = head;
    while (cur != null){
      length++;
      cur = cur.next;
    }
    int count = length / k;

    for (int i = 0; i < count; i++) {
      // 准备当前要操作的节点
      ListNode startNode = virtualNode;
      int step = i * k;
      while (step > 0){
        startNode = startNode.next;
        step--;
      }
      int tmp = k;
      while (tmp - 1 > 0) {
        int subTmp = tmp;
        // 第一次: v -> A -> B -> C => v -> B -> A -> C
        // 第二次: v -> B -> A -> C => v -> B -> C -> A
        ListNode currentNode = startNode.next;
        ListNode currentPreNode = startNode;
        while (subTmp - 1 > 0) {
          ListNode A = currentNode;
          ListNode B = currentNode.next;
          ListNode C = currentNode.next.next;
          A.next = C;
          B.next = A;
          currentPreNode.next = B;
          currentPreNode = B;
          subTmp--;
        }
        tmp--;
      }
    }

    return virtualNode.next;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      List<Integer> res = new ArrayList<>();
      ListNode head = this;
      while (head != null) {
        res.add(head.val);
        head = head.next;
      }
      return res.toString();
    }
  }
}

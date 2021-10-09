package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/9/8 18:29
 */
public class No206_反转链表 {
  public static void main(String[] args) {
    final ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3)));
    System.out.println(reverseList(listNode));
  }

  //  A  ->    B    ->   C
  // pre -> current ->  next
  public static ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode current = head;
    while (current != null) {
      ListNode next = current.next;
      current.next = pre;
      pre = current;
      current = next;
    }
    return pre;
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

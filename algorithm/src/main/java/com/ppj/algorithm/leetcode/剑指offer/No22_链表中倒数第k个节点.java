package com.ppj.algorithm.leetcode.剑指offer;

import lombok.ToString;

/**
 * @author pipi
 * @since 2021/9/2 20:43
 */
public class No22_链表中倒数第k个节点 {

  public static void main(String[] args) {
    final ListNode listNode =
        new ListNode(
            1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
    System.out.println(new No22_链表中倒数第k个节点().getKthFromEnd(listNode, 3));
  }

  //  /**
  //   * stack
  //   */
  //  public ListNode getKthFromEnd(ListNode head, int k) {
  //    Stack<ListNode> stack = new Stack<>();
  //    ListNode current = head;
  //    while (current != null) {
  //      stack.push(current);
  //      current = current.next;
  //    }
  //    ListNode res = null;
  //    while (k-- > 0) {
  //      res = stack.pop();
  //    }
  //    return res;
  //  }

  /** 双指针 */
  public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode current = head, nextK = head;
    while (k-- > 0) {
      nextK = nextK.next;
    }
    while (nextK != null) {
      current = current.next;
      nextK = nextK.next;
    }
    return current;
  }

  @ToString
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}

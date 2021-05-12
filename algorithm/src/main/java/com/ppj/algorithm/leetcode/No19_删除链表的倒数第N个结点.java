package com.ppj.algorithm.leetcode;

/**
 * 输入：head = [1,2,3,4,5], n = 2 输出：[1,2,3,5]
 *
 * @author pipi
 * @since 2021/5/12 18:36
 */
public class No19_删除链表的倒数第N个结点 {

  public static void main(String[] args) {
    final ListNode root = new ListNode(1);
    System.out.println(new No19_删除链表的倒数第N个结点().removeNthFromEnd(root, 1));
    //    final ListNode root =
    //        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    //    System.out.println(new No19_删除链表的倒数第N个结点().removeNthFromEnd(root, 2));
  }

  // 快慢指针
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    // 边界条件处理
    // 慢指针
    ListNode low = dummyHead;
    // 快指针
    ListNode fast = dummyHead;
    do {
      fast = fast.next;
    } while (n-- > 0);

    while (fast != null) {
      low = low.next;
      fast = fast.next;
    }
    low.next = low.next.next;
    return dummyHead.next;
  }

  static class ListNode {
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
      return val + (next == null ? "" : "," + next);
    }
  }
}

package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pipi
 * @since 2021/9/9 11:31
 */
public class No2_两数相加 {

  public static void main(String[] args) {
    //
    final ListNode listNode =
        addTwoNumbers(
            new ListNode(2, new ListNode(4, new ListNode(3))),
            new ListNode(5, new ListNode(6, new ListNode(4))));
    System.out.println(listNode);
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    String res = (splice(l1) + splice(l2)) + "";
    final char[] chars = new StringBuffer(res).reverse().toString().toCharArray();
    ListNode node = new ListNode(Integer.parseInt(String.valueOf(chars[0])));
    ListNode tmp = node;
    for (int i = 1; i < chars.length; i++) {
      tmp.next = new ListNode(Integer.parseInt(String.valueOf(chars[i])));
      tmp = tmp.next;
    }
    return node;
  }

  private static Long splice(ListNode l1) {
    StringBuilder tmp = new StringBuilder();
    while (l1 != null) {
      tmp.append(l1.val);
      l1 = l1.next;
    }
    return Long.valueOf(tmp.reverse().toString());
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

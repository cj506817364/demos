package com.ppj.algorithm.leetcode;

import cn.hutool.core.util.ArrayUtil;
import lombok.ToString;

/**
 * hash表 时间复杂ß度 => O(n) 空间复杂度 => O(n);
 *
 * <p>双层for循环 时间复杂度 => O(n²) 空间复杂度 => O(1)
 *
 * <p>双指针 时间复杂度 => O(n) 空间复杂度 => O(1)
 *
 * @author pipi
 * @since 2021/6/4 10:30
 */
public class No160_相交链表 {

  public static void main(String[] args) {
    final ListNode builder = builder(8, 4, 5);
    ListNode headA = builder(4, 1);
    headA.next.next = builder;
    ListNode headB = builder(5, 0, 1);
    headB.next.next.next = builder;
    final ListNode intersectionNode = new No160_相交链表().getIntersectionNode(headA, headB);
    System.out.println(intersectionNode);
  }

  public static ListNode builder(Integer... val) {
    if (val == null) {
      return null;
    }
    final ListNode listNode = new ListNode(val[0]);
    if (val.length >= 2) {
      listNode.next = builder(ArrayUtil.sub(val, 1, val.length));
    }
    return listNode;
  }

  // 双指针
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode pA = headA, pB = headB;
    while (pA != pB) {
      pA = pA == null ? headB : pA.next;
      pB = pB == null ? headA : pB.next;
    }
    return pA;
  }

  //  // 双层for循环
  //  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
  //    while (headA != null){
  //      ListNode tempB = headB;
  //      while (tempB != null){
  //        if (headA.equals(tempB)){
  //          return tempB;
  //        }
  //        tempB = tempB.next;
  //      }
  //      headA = headA.next;
  //    }
  //    return null;
  //  }

  //  // hash表 时间复杂度
  //  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
  //    Set<ListNode> setA = buildHashSet(headA);
  //    while (headB != null) {
  //      if (setA.contains(headB)) {
  //        return headB;
  //      }
  //      headB = headB.next;
  //    }
  //    return null;
  //  }
  //
  //  private Set<ListNode> buildHashSet(ListNode headA) {
  //    Set<ListNode> nodeSet = new HashSet<>();
  //    while (headA != null) {
  //      nodeSet.add(headA);
  //      headA = headA.next;
  //    }
  //    return nodeSet;
  //  }

  @ToString
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

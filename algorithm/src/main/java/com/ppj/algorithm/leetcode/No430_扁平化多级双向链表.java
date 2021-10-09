package com.ppj.algorithm.leetcode;

import lombok.Data;

/**
 * @since 2021/9/24 10:44
 * @author pipi
 */
public class No430_扁平化多级双向链表 {

  public static void main(String[] args) {
    final No430_扁平化多级双向链表 _430 = new No430_扁平化多级双向链表();
//    final Node child8 = new Node(8, new Node(9, new Node(10)), new Node(11, new Node(12)));
//    final Node child7 = new Node(7, child8);
//    final Node head =
//        new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))), child7)));
//    final Node flatten = _430.flatten(head);
//    System.out.println(flatten);
//    System.out.println("[1,2,3,7,8,11,12,9,10,4,5,6]");

    final Node node = new Node(1, null, new Node(2, null, new Node(3)));
    final Node flatten = _430.flatten(node);
    System.out.println(flatten);
  }

  public Node flatten(Node head) {
    dfs(head);
    return head;
  }

  private Node dfs(Node node) {
    Node cur = node;
    Node last = null;
    while (cur != null) {
      final Node child = cur.child;
      if (child == null) {
        last = cur;
        cur = cur.next;
        continue;
      }

      // 将子节点接到链表后面 注意三个属性 prev next child 都要修改
      // prev -> cur -> next
      //             -> child -> childLast
      // prev -> cur -> child -> childLast -> next
      Node next = cur.next;
      Node childLast = dfs(child);
      cur.next = child;
      child.prev = cur;
      childLast.next = next;
      if (next != null) {
        next.prev = childLast;
      }

      // 有子节点
      // 置空子
      cur.child = null;

      last = cur;
      cur = next;


    }
    return last;
  }

  private Node dfs1(Node head) {
    Node cur = head;
    Node last = null;
    while (cur != null) {
      Node next = cur.next;
      if (cur.child != null) {
        Node childLast = dfs1(cur.child);
        // node 链接child
        cur.next = cur.child;
        cur.child.prev = cur;

        // 如果 next 不为空, 就讲 last 与 next 相连
        if (next != null) {
          childLast.next = next;
          next.prev = childLast;
        }

        // 将 child 置为空
        cur.child = null;
        last = childLast;
      } else {
        last = cur;
      }
      cur = next;
    }
    return last;
  }

  public Node pick(Node node) {
    if (node == null) {
      return null;
    }
    final Node child = node.child;
    if (child != null) {
      node.child = null;
      return child;
    }
    if (node.next != null) {
      return node.next;
    }
    return null;
  }

  @Data
  static class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val, Node next, Node child) {
      this.val = val;
      this.next = next;
      this.child = child;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }

    public Node(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "Node{" + "val=" + val + ", next=" + next + ", child=" + child + '}';
    }
  }
}

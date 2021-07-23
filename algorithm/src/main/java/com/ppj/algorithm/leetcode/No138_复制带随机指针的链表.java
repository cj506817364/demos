package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pipi
 * @since 2021/7/22 18:11
 */
public class No138_复制带随机指针的链表 {



  public static void main(String[] args) {
    Node node0 = new Node(7);
    Node node1 = new Node(13);
    Node node2 = new Node(11);
    Node node3 = new Node(10);
    Node node4 = new Node(1);
    node0.next = node1;
    node0.random = null;
    node1.next = node2;
    node1.random = node0;
    node2.next = node3;
    node2.random = node4;
    node3.next = node4;
    node3.random = node2;
    node4.next = null;
    node4.random = node0;

    final Node node = new No138_复制带随机指针的链表().copyRandomList(node0);
    System.out.println(node);
  }

  Map<Node, Integer> nodeMap = new HashMap<>();
  List<Node> newNodeList = new ArrayList<>();

  public Node copyRandomList(Node head) {
    int index = 0;
    Node headPoint = head;
    while (headPoint != null) {
      nodeMap.put(headPoint,index);
      final Node newNode = new Node(headPoint.val);
      newNodeList.add(newNode);
      headPoint = headPoint.next;
      index++;
    }

    for (Node node : nodeMap.keySet()) {
      final Integer idx = getIdx(node);
      final Node newNode = newNodeList.get(idx);
      newNode.next = getIdx(node.next) == null ? null : newNodeList.get(getIdx(node.next));
      newNode.random =getIdx(node.random) == null ? null : newNodeList.get(getIdx(node.random));
    }
    return newNodeList.isEmpty() ? null : newNodeList.get(0);
  }

  private Integer getIdx(Node node) {
    return nodeMap.get(node);
  }

  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
}

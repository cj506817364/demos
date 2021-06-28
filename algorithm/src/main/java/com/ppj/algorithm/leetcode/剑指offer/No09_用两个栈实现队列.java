package com.ppj.algorithm.leetcode.剑指offer;

import java.util.Stack;

/**
 * @author pipi
 * @since 2021/6/28 15:33
 */
public class No09_用两个栈实现队列 {

  public static void main(String[] args) {
    //
    final CQueue cQueue = new CQueue();
    cQueue.appendTail(3);
    System.out.println(cQueue.deleteHead());
    System.out.println(cQueue.deleteHead());
  }

  static class CQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    public void appendTail(int value) {
      stack1.push(value);
    }

    public int deleteHead() {
      if (stack1.empty()) {
        return -1;
      }

      while (!stack1.empty()) {
        stack2.push(stack1.pop());
      }

      final Integer pop = stack2.pop();

      while (!stack2.empty()) {
        stack1.push(stack2.pop());
      }

      return pop;
    }
  }
}

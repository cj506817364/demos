package com.ppj.algorithm.recursive;

import com.ppj.algorithm.List.Node;

/**
 * 递归算法:
 * 问题定义是递归的
 * - 阶乘,Fibonacci数列,链表,树
 * 问题求解方法是递归的
 * - 分治法
 * T(n) = T(n-1) + O(1)
 * T(n-1) = T(n-2) + O(n-1)
 * T(n) = O(n) + O(n-1) + O(n-2) + .+ O(1) + T(0) = O(n(n+1)/2)
 * 时间复杂度:O(N^2)
 */
public class RecursiveAlg {

    /**
     * 阶乘
     */
    public long m(int n) {

        if (n == 0) {
            return 1;
        }
        return n * m(n - 1);
    }

    /**
     * 斐波那契额数列
     */
    public long F(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }
        return F(n - 1) + F(n - 2);
    }

    /**
     * 求前n个数的和
     */
    public long sumArray(int[] arr, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            return arr[startIndex];
        }
        return arr[startIndex] + sumArray(arr, startIndex + 1, endIndex);
    }

    /**
     * 求链表长度
     */
    public int count(Node list) {

        if (list == null) {
            return 0;
        }

        return 1 + count(list.next);
    }

    /**
     * 打印单链表的所有节点值
     */
    public void printList(Node list) {
        if (list == null) {
            return;
        }
        System.out.println(list.data);
        printList(list.next);
    }

    /**
     * 反向打印单链表的所有节点值
     */
    public void printListReverse(Node list) {
        if (list == null) {
            return;
        }
        printList(list.next);
        System.out.println(list.data);
    }

    /**
     * 删除列表中值为x的所有节点
     */
    public void deleteListData(Node<Integer> list, Object x) {

        if (list == null) {
            return;
        }

        if (list.data.equals(x)) {
            list = list.next;
        }

        if(list.next == null){
            return;
        }

        if(list.next.data.equals(x)){
            list.next = list.next.next;
            deleteListData(list.next,x);
        }else {
            deleteListData(list.next.next,x);
        }

    }

    /**
     * 删除列表中值为x的所有节点
     */
    public void _deleteListData(Node list, Object x) {

        if (list == null) {
            return;
        }

        if (list.data.equals(x)) {
            list = list.next;
            _deleteListData(list,x);
        }else {
            _deleteListData(list.next,x);
        }

    }

    /**
     * 输出列表中最大/小节点值
     */
    public void printListData(Node<Integer> list, Integer min, Integer max) {
        if (list == null) {
            System.out.println("min: " + min + " max: " + max);
            return;
        }
        Integer data = list.data;
        if (min == null || min.compareTo(data) < 0) {
            min = data;
        }

        if (max == null || max.compareTo(data) > 0) {
            max = data;
        }

        printListData(list.next, min, max);
    }

    /**
     * 逆转链表
     */
    public void reverseList(Node list,Node next) {

        if(list == null || list.next == null){
            return;
        }



        reverseList(list.next, next);
    }



}

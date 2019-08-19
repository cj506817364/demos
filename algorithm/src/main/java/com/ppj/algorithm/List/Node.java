package com.ppj.algorithm.List;

public class Node<E> {

    public E data; //数据域
    public Node<E> next; //指针域，指向下一个Node

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node(E data) {
        this(data, null);
    }

    public String toString() {
        return data.toString();
    }


}

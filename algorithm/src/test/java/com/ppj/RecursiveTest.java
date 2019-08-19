package com.ppj;

import com.ppj.algorithm.List.Node;
import com.ppj.algorithm.recursive.RecursiveAlg;
import org.junit.Test;

public class RecursiveTest {

    @Test/** Fibonacci */
    public void test01(){
        RecursiveAlg alg = new RecursiveAlg();
        System.out.println(alg.F(10));
    }

    @Test/** printListData */
    public void test02(){
        RecursiveAlg alg = new RecursiveAlg();
        Node<Integer> node = new Node<>(10);
        node.next = new Node<>(5);
        node.next.next = new Node<>(4);
        node.next.next.next = new Node<>(5);

        alg.deleteListData(node,5);

        alg.printList(node);
    }

    /**
     * printListData
     * list.prev.next=list.next
     * list.next.prev=list.prev
     */
    @Test
    public void test03(){
        RecursiveAlg alg = new RecursiveAlg();
        Node<Integer> node = new Node<>(10);
        node.next = new Node<>(5);
        node.next.next = new Node<>(4);
        node.next.next.next = new Node<>(5);
        alg.printListData(node,null,null);
    }

}

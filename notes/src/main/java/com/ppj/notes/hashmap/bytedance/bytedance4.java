package com.ppj.notes.hashmap.bytedance;

import java.util.*;

/**
 * @author cj
 * @date 2019-08-11 19:02
 */
public class bytedance4 {

    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        String s1 = sc.nextLine();
//        String s2 = sc.nextLine();
//        String[] s = s2.split(" ");
//        int[] ints = new int[s.length];
//        for (int i = 0; i < s.length; i++) {
//            ints[i] = Integer.parseInt(s[i]);
//        }
        int n = 3;
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(1,2));
        nodeList.add(new Node(2,1));
        nodeList.add(new Node(3,2));
        nodeList.add(new Node(2,3));
        List<AB> aLlAB = getALlAB(3);
        aLlAB.forEach((ab) -> {
            List<AB> starAList = new ArrayList<>();
            nodeList.forEach((node) -> {
                if(node.v == ab.a){
//                    starAList.add()
                }

            });
        });

    }

    static class Node {
        int v;
        int next;

        public Node(int v, int next) {
            this.v = v;
            this.next = next;
        }

    }

    static class AB {
        int a;
        int b;

        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static List<AB> getALlAB(int n) {
        if (n <= 2) {
            return null;
        }
        List<AB> abList = new ArrayList<>();
        int start = 1;
        do {
            for (int i = 2; i <= n; i++) {
                abList.add(new AB(start, i));
            }
        } while (++start >= n);
        return abList;
    }
}

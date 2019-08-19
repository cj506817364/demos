package com.ppj;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        while(head != null && head.next != null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return head;
//        ListNode current = head;
//        while (current != null && current.next != null) {
//            if (current.next.val == current.val) {
//                current.next = current.next.next;
//            } else {
//                current = current.next;
//            }
//        }
//        return head;
    }

}

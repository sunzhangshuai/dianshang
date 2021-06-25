package com.data.algorithm.practice.primary;

import com.data.structure.list.impl.SingleLinkedListImpl;
import com.data.structure.list.model.ListNode;

/**
 * AddTwoNumbers:
 *
 * @author sunchen
 * @date 2020/7/7 11:24 下午
 */
public class AddTwoNumbers2 {
    static boolean carry = false;

    /**
     * 3-9-6  7-6-6
     *
     * @param list1
     * @param list2
     * @return
     */
    public static SingleLinkedListImpl add(SingleLinkedListImpl list1, SingleLinkedListImpl list2) {
        SingleLinkedListImpl list3 = new SingleLinkedListImpl();
        list3.head = new ListNode(1);
        ListNode pre = list3.head;
        ListNode head1 = list1.getHead();
        ListNode head2 = list2.getHead();
        while (head1 != null && head2 != null) {
            int head3 = head1.val + head2.val;
            if (carry) {
                head3++;
            }
            if (head3 >= 10) {
                carry = true;
                head3 = head3 - 10;
            } else {
                carry = false;
            }
            ListNode node = new ListNode(head3);
            list3.length++;
            pre.next = node;
            pre = node;
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head1 != null) {
            pre = getListNode(list3, pre, head1);
        }
        if (head2 != null) {
            pre = getListNode(list3, pre, head2);
        }
        if (carry) {
            pre.next = new ListNode(1);
            list3.length++;
        }
        list3.head = list3.head.next;
        return list3;
    }

    private static ListNode getListNode(SingleLinkedListImpl list3, ListNode pre, ListNode head) {
        while (head != null) {
            String s  = "";
            s.substring(1,1);
            int data = head.val;
            if (carry) {
                data++;
            }
            if (data >= 10) {
                data = data - 10;
                carry = true;
            } else {
                carry = false;
            }
            ListNode node = new ListNode(data);
            list3.length++;
            pre.next = node;
            pre = node;
            head = head.next;
        }
        return pre;
    }

    public static void main(String[] args) {
        String aa = "111";
        char[] chars = aa.toCharArray();
        System.out.println(chars[0]);
    }
}

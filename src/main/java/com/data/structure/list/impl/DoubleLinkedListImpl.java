package com.data.structure.list.impl;

import com.data.structure.list.interfaces.LinkedList;
import com.data.structure.list.model.ListNode;

/**
 * 双向链表基本操作
 * @author wenba
 */
public class DoubleLinkedListImpl implements LinkedList {

    public ListNode head;

    public ListNode tail;

    public int length;

    @Override
    public boolean clearList() {
        ListNode node = head;
        ListNode next;
        for (int i = 1; i < length; i++) {
            next = node.next;
            node.next = null;
            node.prev = null;
            node.data = null;
            node = next;
        }
        tail = head = null;
        length = 0;
        return true;
    }

    @Override
    public void insFirst(ListNode e) {
        if (listEmpty()) {
            head = tail = e;
        } else {
            e.next = head;
            head.prev = e;
            head = e;
        }
        length++;
    }

    @Override
    public void delFirst() {
        if (listEmpty()) {
            throw new RuntimeException("空表不能删除");
        }
        if (length == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        length--;
    }

    @Override
    public void append(ListNode s) {
        if (listEmpty()) {
           head = s;
        } else {
            tail.next = s;
            s.prev = tail;
        }
        int sLength = 1;
        while (s.next != null) {
            s = s.next;
            sLength++;
        }
        tail = s;
        length += sLength;
    }

    @Override
    public ListNode remove() {
        if (listEmpty()) {
           throw new RuntimeException("空表不能删除");
        }
        ListNode node = tail;
        if (length == 1) {
            tail = head = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }
        return node;
    }

    @Override
    public void insBefore(ListNode p, ListNode s) {
        if (p == head){
            insFirst(s);
            return;
        }
        p.prev.next = s;
        s.prev = p.prev;
        s.next = p;
        p.prev = s;
    }

    @Override
    public void insAfter(ListNode p, ListNode s) {
        if (p == tail) {
            s.next = null;
            append(s);
            return;
        }
        s.next = p.next;
        p.next.prev = s;
        p.next = s;
        s.prev = p;
    }

    @Override
    public void setCurElem(ListNode p, Object e) {
        p.data = e;
    }

    @Override
    public Object getCurElem(ListNode p) {
        return p.data;
    }

    @Override
    public boolean listEmpty() {
        return length == 0;
    }

    @Override
    public int listLength() {
        return length;
    }

    @Override
    public ListNode getHead() {
        return head;
    }

    @Override
    public ListNode getLast() {
        return tail;
    }

    @Override
    public ListNode priorPos(ListNode p) {
        return p.prev;
    }

    @Override
    public ListNode nextPos(ListNode p) {
        return p.next;
    }

    @Override
    public ListNode locatePos(int i) {
        if (i > length && i < 1) {
            throw new RuntimeException("i不合法");
        }
        ListNode node;
        if (i > length>>1) {
            //从后往前找
            node = tail;
            for (int j = length; j > i; j--) {
                node = node.prev;
            }
        } else {
            //从前往后找
            node = head;
            for (int j = 1; j < i; j++) {
                node = node.next;
            }
        }
        return node;
    }

    @Override
    public ListNode locateElem(Object e) {
        return null;
    }

    @Override
    public void listTraverse() {
        ListNode node = head;
        for (int i = 1; i < length; i++) {
            visit(node);
            node = node.next;
        }
    }

    public void visit (ListNode node) {
        System.out.println(node);
    }
}

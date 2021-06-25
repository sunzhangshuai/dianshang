package com.data.structure.list.impl;

import com.data.structure.list.interfaces.LinkedList;
import com.data.structure.list.model.ListNode;

/**
 * 单链表基本操作
 *
 * @author laosun
 */
public class SingleLinkedListImpl implements LinkedList {

    public ListNode head;

    public ListNode tail;

    public int length;

    public SingleLinkedListImpl() {
    }

    @Override
    public boolean clearList() {
        ListNode node = head;
        for (int i = 0; i < length; i++) {
            ListNode next = node.next;
            node.data = null;
            node.next = null;
            node = next;
        }
        head = null;
        tail = null;
        length = 0;
        return true;
    }

    @Override
    public void insFirst(ListNode e) {
        if (listEmpty()) {
            head = tail = e;
        } else {
            e.next = head;
            head = e;
        }
        length++;
    }

    @Override
    public void delFirst() {
        if (listEmpty()) {
            throw new RuntimeException("空表，不能删除");
        }
        if (length == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }
        length--;
    }

    @Override
    public void append(ListNode s) {
        if (listEmpty()) {
            head = s;
        } else {
            tail.next = s;
        }
        int sLength = 1;
        while (s != null) {
            s = s.next;
            sLength++;
        }
        tail = s;
        length += sLength;
    }

    @Override
    public ListNode remove() {
        if (listEmpty()) {
            throw new RuntimeException("空表，不能删除");
        }
        ListNode node = head;
        if (length == 1) {
            head = tail = null;
        } else {
            node = tail;
            ListNode pre = priorPos(tail);
            pre.next = null;
            tail = pre;
        }
        length--;
        return node;
    }

    @Override
    public void insBefore(ListNode p, ListNode s) {
        ListNode pre = priorPos(p);
        if (pre == null) {
            insFirst(s);
            return;
        }
        s.next = p;
        pre.next = s;
        length++;
    }

    /**
     * 删除当前结点
     *
     * @param p 当前节点
     */
    public void delCur(ListNode p) {
        ListNode node = priorPos(p);
        if (node == null) {
            delFirst();
        } else {
            node.next = node.next.next;
            length--;
        }
    }

    @Override
    public void insAfter(ListNode p, ListNode s) {
        s.next = p.next;
        p.next = s;
        length++;
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
        ListNode node = head;
        if (head == p) {
            return null;
        }
        for (int i = 1; i < length; i++) {
            if (p == node.next) {
                break;
            }
            node = node.next;
        }
        return node;
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
        ListNode node = head;
        for (int j = 1; j < i; j++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public ListNode locateElem(Object e) {
        ListNode node = head;
        for (int i = 1; i <= length; i++) {
            if (compare(node.data, e)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void listTraverse() {
        ListNode node = head;
        for (int i = 1; i <= length; i++) {
            visit(node);
            node = node.next;
        }
    }

    public void visit(ListNode node) {
        System.out.println(node.val);
    }

    public boolean compare(Object data, Object e) {
        return data.equals(e);
    }
}

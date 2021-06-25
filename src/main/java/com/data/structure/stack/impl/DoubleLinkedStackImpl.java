package com.data.structure.stack.impl;

import com.data.structure.list.model.ListNode;
import com.data.structure.stack.interfaces.DoubleStack;

/**
 * 双向链栈
 *
 * @author 老孙
 */
public class DoubleLinkedStackImpl implements DoubleStack {
    /**
     * 分别指向两个栈的栈顶
     */
    public ListNode[] tops;

    /**
     * 同一个栈底
     */
    public ListNode tail;

    public DoubleLinkedStackImpl() {
        tail = new ListNode(0);
        tops = new ListNode[]{tail, tail};
    }

    @Override
    public void clearStack(int i) {
        while (tail != tops[i]) {
            pop(i);
        }
    }

    @Override
    public boolean stackEmpty(int i) {
        return tail == tops[i];
    }

    @Override
    public int stackLength(int i) {
        int length = 0;
        if (i == 0) {
            while (tops[i] != tail) {
                length++;
                tops[i] = tops[i].next;
            }
        } else {
            while (tops[i] != tail) {
                length++;
                tops[i] = tops[i].prev;
            }
        }
        return length;
    }

    @Override
    public Object getTop(int i) {
        if (stackEmpty(i)) {
            throw new RuntimeException("栈空");
        }
        return tops[i].data;
    }


    @Override
    public void push(Object e, int i) {
        //要从左边入栈
        ListNode node = new ListNode(e);
        if (i == 0) {
            node.next = tops[0];
            tops[0].prev = node;
            tops[0] = node;
        } else {
            tops[1].next = node;
            node.prev = tops[1];
            tops[1] = node;
        }
    }


    @Override
    public Object pop(int i) {
        if (tops[i] == tail) {
            throw new RuntimeException("栈已经空了，不能出栈");
        }
        //从左边出栈
        Object oldValue = tops[i].data;
        if (i == 0) {
            tops[i] = tops[i].next;
            tops[i].prev = null;
        } else {
            tops[i] = tops[i].prev;
            tops[i].next = null;
        }
        return oldValue;
    }


    @Override
    public void stackTraverse(int i) {
        ListNode node = tops[i];
        if (i == 0) {
            while (tail != node) {
                visit(node.data);
                node = node.next;
            }
        } else {
            while (tail != node) {
                visit(node.data);
                node = node.prev;
            }
        }
    }

    public void visit(Object e) {
        System.out.println(e);
    }
}

package com.data.structure.list.model;

/**
 * 链表结点
 * @author sunchen
 */
public class ListNode {

    public int val;
    /**
     * 数据域
     */
    public Object data;

    /**
     * 前驱
     */
    public ListNode next;

    /**
     * 后继
     */
    public ListNode prev;

    public ListNode(){}

    /**
     * 构造器
     * @param data
     */
    public ListNode(Object data) {
        this.data = data;
    }
    public ListNode(int data) {
        this.val = data;
    }
}

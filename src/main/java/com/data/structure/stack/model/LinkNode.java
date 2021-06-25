package com.data.structure.stack.model;

import com.data.structure.list.model.ListNode;

/**
 * 链表节点
 * @author sunchen
 */
public class LinkNode extends ListNode {

    /**
     * 前驱
     */
    public LinkNode next;

    /**
     * 后继
     */
    public LinkNode prev;

    /**
     * 构造器
     *
     * @param data
     */
    public LinkNode(Object data) {
        super(data);
    }
}

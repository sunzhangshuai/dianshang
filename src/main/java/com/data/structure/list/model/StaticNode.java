package com.data.structure.list.model;

/**
 * @author sunchen
 */
public class StaticNode extends ListNode {
    /**
     * 后继下标
     */
    public int cur;

    /**
     * 构造器
     *
     * @param data
     */
    public StaticNode(Object data) {
        super(data);
    }

    public StaticNode(Object data, int cur) {
        this.data = data;
        this.cur = cur;
    }

    public StaticNode(int cur){
        this.cur = cur;
    }
}

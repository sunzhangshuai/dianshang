package com.data.structure.stack.interfaces;

/**
 * 单向栈
 *
 * @author sunchen
 */
public interface Stack {
    /**
     * 清空
     */
    void clearStack();

    /**
     * 判断是否为空栈
     *
     * @return
     */
    boolean stackEmpty();

    /**
     * 栈的长度
     *
     * @return
     */
    int stackLength();

    /**
     * 返回栈顶元素
     *
     * @return
     */
    Object getTop();

    /**
     * 插入元素
     *
     * @param e
     */
    void push(Object e);

    /**
     * 删除栈顶元素
     *
     * @return
     */
    Object pop();

    /**
     * 遍历元素
     */
    void stackTraverse();
}

package com.data.structure.stack.interfaces;

/**
 * 双向栈
 *
 * @author sunchen
 */
public interface DoubleStack {

    /**
     * 清空
     *
     * @param i
     */
    void clearStack(int i);

    /**
     * 判断是否为空栈
     *
     * @param i
     * @return
     */
    boolean stackEmpty(int i);

    /**
     * 栈的长度
     *
     * @param i
     * @return
     */
    int stackLength(int i);

    /**
     * 返回栈顶元素
     *
     * @param i
     * @return
     */
    Object getTop(int i);

    /**
     * 插入元素
     *
     * @param e
     * @param i
     */
    void push(Object e, int i);

    /**
     * 删除栈顶元素
     *
     * @param i
     * @return
     */
    Object pop(int i);

    /**
     * 遍历元素
     *
     * @param i
     */
    void stackTraverse(int i);
}

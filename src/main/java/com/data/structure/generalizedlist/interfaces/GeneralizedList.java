package com.data.structure.generalizedlist.interfaces;

/**
 * GeneralizedList:
 *
 * @author sunchen
 * @date 2020/5/24 9:53 上午
 */
public interface GeneralizedList<T> {
    /**
     * 广义表长度
     * @return
     */
    int gListLength();

    /**
     * 广义表深度
     * @return
     */
    int gListDepth();

    /**
     * 广义表是否为空
     * @return
     */
    boolean gListEmpty();

    /**
     * 获取头
     * @return
     */
    T getHead();

    /**
     * 获取尾
     * @return
     */
    T getTail();

    /**
     * 插入e作为广义表的第一元素
     * @param e
     */
    void insertFirstGl(Object e);

    /**
     * 删除广义表的第一元素
     * @return
     */
    Object deleteFirstGl();

    /**
     * 遍历广义表
     */
    void traverseGl();

    /**
     * 生成字符串数组
     * @return
     */
    char[] toArray();
}

package com.data.structure.list.interfaces;

import com.data.structure.list.model.ListNode;

/**
 * 链式
 * @author sunchen
 */
public interface LinkedList {
    /**
     * 置为空表
     * @return 结果
     */
    boolean clearList ();

    /**
     * 将结点插在第一个位置
     * @param e 节点
     */
    void insFirst (ListNode e);

    /**
     * 将第一个位置删除
     */
    void delFirst ();

    /**
     * 将结点拼接在链表尾部
     * @param s 节点
     */
    void append (ListNode s);

    /**
     * 尾删
     * @return 删除的节点
     */
    ListNode remove ();

    /**
     * 将s插入在p所指结点之前
     * @param p 节点
     * @param s 节点
     */
    void insBefore (ListNode p, ListNode s);

    /**
     * 将s插入在p所指结点之后
     * @param p 节点
     * @param s 节点
     */
    void insAfter (ListNode p, ListNode s);

    /**
     * 将p所指向的结点的值更新为e
     * @param p 节点
     * @param e 值
     */
    void setCurElem (ListNode p, Object e);

    /**
     * 返回结点的值
     * @param p 节点
     * @return 值
     */
    Object getCurElem (ListNode p);

    /**
     * 链表是否为空
     * @return 结果
     */
    boolean listEmpty();

    /**
     * 返回链表的长度
     * @return 长度
     */
    int listLength ();

    /**
     * 返回第一个结点的位置
     * @return
     */
    ListNode getHead ();

    /**
     * 返回最后一个结点的位置
     * @return 位置
     */
    ListNode getLast ();

    /**
     * 返回结点的直接前驱
     * @param p 节点
     * @return 前驱
     */
    ListNode priorPos (ListNode p);

    /**
     * 返回结点的直接后继
     * @param p 节点
     * @return 后继
     */
    ListNode nextPos (ListNode p);

    /**
     * 返回第i个结点的值
     * @param i 位置
     * @return 值
     */
    ListNode locatePos(int i);

    /**
     * 查找满足compare条件的第一个node
     * @param e 值
     * @return 位置
     */
    ListNode locateElem(Object e);

    /**
     * 遍历
     */
    void listTraverse ();
}

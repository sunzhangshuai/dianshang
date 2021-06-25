package com.data.structure.list.impl;

import com.data.structure.list.interfaces.SequenceList;
import com.data.structure.list.model.StaticNode;

/**
 * 静态链表：有一个数组，存了结点的值和下一个结点的下标，通过下标将链表串了起来
 * @author sunchen
 */
public class StaticLinkedList implements SequenceList {

    /**
     * 数组大小
     */
    private final int MAXSIZE = 1000;

    /**
     * 记录数组中第一个可以被占用的下标
     */
    int unUsed;

    /**
     * 链表实际的长度
     */
    int length;

    /**
     * 静态数组
     */
    StaticNode[] data;

    public StaticLinkedList() {
        data = new StaticNode[MAXSIZE];
        //初始化数组
        for (int i = 1; i < MAXSIZE-1; i++) {
            data[i] = new StaticNode(i+1);
        }
        data[0] = new StaticNode(0);
        data[MAXSIZE-1] = new StaticNode(0);
        //记录第一个可以被占用的下标
        unUsed = 1;
    }


    @Override
    public void clearList() {
        for (int i = 1; i < MAXSIZE-1; i++) {
            data[i].cur = i + 1;
        }
        data[0].cur = 0;
        data[MAXSIZE-1].cur = 0;
        unUsed = 1;
    }

    @Override
    public boolean listEmpty() {
        return listLength() == 0;
    }

    @Override
    public int listLength() {
        return length;
    }

    @Override
    public Object getElem(int index) {
        if (index < 0 && index > length) {
            throw new RuntimeException("index不合法");
        }
        // data[0].cur记录链表的第一个结点
        int cur = data[0].cur;
        while (index > 0) {
            cur = data[cur].cur;
            index--;
        }
        return data[cur].data;
    }

    @Override
    public int locateElement(Object e) {
        int cur = data[0].cur;
        while (cur != 0 && !data[cur].data.equals(e)) {
            cur = data[cur].cur;
        }
        return cur;
    }

    /**
     * 往第index个位置插入e
     */
    @Override
    public void listInsert(int pos, Object e) {
        if (pos < 1 || pos > length + 1) {
            throw new RuntimeException("pos位置不合法");
        }
        if (length + 1 > MAXSIZE) {
            throw new RuntimeException("静态链表已经满了");
        }
        //找第pos个位置的下标和第pos-1个位置的下标
        int cur = data[0].cur;
        int pre = 0;
        while (cur != 0 && pos > 1) {
            pre = cur;
            cur = data[cur].cur;
            pos--;
        }
        //新结点
        int newIndex = unUsed;
        unUsed = data[newIndex].cur;

        data[newIndex].data = e;
        // 建立新关系
        data[newIndex].cur = data[pre].cur;
        data[pre].cur = newIndex;
        length++;
    }

    @Override
    public Object listDelete(int pos) {
        //删除第pos位置的结点
        if (pos < 1) {
            throw new RuntimeException("插入的位置不合法");
        }
        if (listEmpty()) {
            throw new RuntimeException("静态链表为空");
        }
        int cur = data[0].cur;
        int pre = 0;
        while (cur != 0 && pos > 1) {
            pre = cur;
            cur = data[cur].cur;
            pos--;
        }

        Object e = data[cur].cur;
        data[pre].cur = data[cur].cur;

        data[cur].cur = unUsed;
        unUsed = cur;

        length--;
        return e;
    }

    @Override
    public Object priorElem(Object e) {
        int cur = data[0].cur;
        if (cur == 0) {
            throw new RuntimeException("没有前驱");
        }
        while (cur != 0 && !data[data[cur].cur].data.equals(e)) {
            cur = data[cur].cur;
        }
        return data[cur].data;
    }

    @Override
    public Object nextElem(Object e) {
        int cur = locateElement(e);
        if (data[cur].cur == 0) {
            throw new RuntimeException("没有后继");
        }
        return data[data[cur].cur].data;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public void listTraverse() {
        int cur = data[0].cur;
        while (cur != 0) {
            visit(data[cur].data);
            cur = data[cur].cur;
        }
    }

    public void visit(Object e) {
        System.out.println(e);
    }
}

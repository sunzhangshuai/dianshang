package com.data.structure.list.impl;

import com.data.structure.list.interfaces.SequenceList;

/**
 * 顺序表基本操作
 * @author wenba
 */
public class SequenceListImpl implements SequenceList {

    public Object[] elementData;

    public static int LIST_INIT_SIZE = 100;

    public static int LIST_INCREMENT = 10;

    public int listSize;

    public int length;

    public SequenceListImpl() {
        this.elementData = new Object[LIST_INIT_SIZE];
        this.listSize = LIST_INIT_SIZE;
    }

    @Override
    public void clearList() {
        for (int i = 0; i < length; i++) {
            elementData[i] = null;
        }
        length = 0;
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
    public Object getElem(int index) {
        if (index < 1 || index > listSize) {
            throw new IndexOutOfBoundsException("数组越界");
        }
        return elementData[index - 1];
    }

    @Override
    public int locateElement(Object e) {
        if (e == null) {
            for (int i = 0; i < length; i++) {
                if (elementData[i] == null) {
                    return i + 1;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (elementData[i].equals(e)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    @Override
    public Object priorElem(Object e) {

        if (e == null) {
            if (elementData[0] == null) {
                throw new RuntimeException(null + "没有前驱");
            }
            for (int i = 1; i < length; i++) {
                if (elementData[i] == null) {
                    return elementData[i - 1];
                }
            }
        } else {
            if (elementData[0].equals(e)) {
                throw new RuntimeException(e + "没有前驱");
            }
            for (int i = 1; i < length; i++) {
                if (elementData[i].equals(e)) {
                    return elementData[i - 1];
                }
            }
        }
        throw new RuntimeException("没有找到该元素" + e);
    }

    @Override
    public Object nextElem(Object e) {
        if (e == null) {
            if (elementData[length - 1] == null) {
                throw new RuntimeException(null + "没有后继");
            }
            for (int i = 0; i < length; i++) {
                if (elementData[i] == null) {
                    return elementData[i + 1];
                }
            }
        } else {
            if (elementData[length - 1].equals(e)) {
                throw new RuntimeException(e + "没有后继");
            }
            for (int i = 0; i < length; i++) {
                if (elementData[i].equals(e)) {
                    return elementData[i + 1];
                }
            }
        }
        throw new RuntimeException("没有找到该元素" + e);
    }

    @Override
    public void listInsert(int index, Object e) {
        // 检查index合法性
        if (index < 1 || index > length + 1) {
            throw new RuntimeException("index:" + index + "不合法");
        }
        //判断是否需要扩容
        ensureCapacity(length + 1);
        //从index开始，每个元素向后移动一位
        for (int i = length - 1; i >= index - 1; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index - 1] = e;
        length++;
    }

    @Override
    public Object listDelete(int index) {
        if (index < 1 || index > listSize) {
            throw new RuntimeException("index:" + index + "不合法");
        }
        Object oldValue = elementData[index - 1];
        if (length - 1 - index - 1 >= 0) {
            System.arraycopy(elementData, index - 1 + 1, elementData, index - 1, length - 1 - index - 1);
        }
        elementData[length - 1] = null;
        return oldValue;
    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public void listTraverse() {
        for (int i = 0; i < length; i++) {
            visit(elementData[i]);
        }
    }

    public void visit(Object e) {
        System.out.println(e);
    }

    public void ensureCapacity(int capacity) {
        if (capacity < elementData.length) {
            return;
        }

        listSize = listSize + LIST_INCREMENT;
        if (listSize < capacity) {
            listSize = capacity;
        }
        Object[] newArray = new Object[listSize];
        if (length >= 0) {
            System.arraycopy(elementData, 0, newArray, 0, length);
        }
        elementData = newArray;

    }
}

package com.data.structure.list.op;

import com.data.structure.list.impl.SequenceListImpl;

/**
 * 顺序表扩展
 * @author sunchen
 */
public class SequenceListExtend extends SequenceListImpl {

    /**
     * 批量删除
     * @param index
     * @param count
     */
    public  void deleteBatch (int index, int count) {
        if (index < 1 || count < 1 || count + index > length + 1) {
            throw new RuntimeException("删除个数超出范围");
        }
        for (int i = index+count-1; i < elementData.length; i++) {
            elementData[i-count] = elementData[i];
        }
        length -= count;
    }
    // index是位序 1 3 4 2 6 8  1 4 8 8

    /**
     * 批量插入
     * @param index
     * @param insertArray
     */
    public void insertBatch (int index, Object[] insertArray) {
        //检查index
        if (index < 1 || index > elementData.length + 1) {
            throw new RuntimeException("index不合法");
        }
        int count = insertArray.length;
        //扩容
        ensureCapacity(count + elementData.length);
        //数据搬移
        for (int i = elementData.length-1; i >= index-1; i--) {
            elementData[i+count] = elementData[i];
        }
        //插入
        for (int j = 0; j < index+count-1;j++) {
            elementData[index-1] = insertArray[j];
        }
        length += count;
    }


}

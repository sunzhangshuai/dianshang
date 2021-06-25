package com.data.structure.list.interfaces;

/**
 * 顺序
 * @author sunchen
 */
public interface SequenceList {
    /**
     * 清空表
     */
    void clearList();

    /**
     * 是否为空
     *
     * @return 结果
     */
    boolean listEmpty();

    /**
     * 获取表长度
     *
     * @return 长度
     */
    int listLength();

    /**
     * 根据位置获取元素
     *
     * @param index 位置
     * @return 元素
     */
    Object getElem(int index);

    /**
     * 根据元素获取位置
     *
     * @param e 元素
     * @return 位置
     */
    int locateElement(Object e);

    /**
     * 插入元素
     *
     * @param index 位置
     * @param e     元素
     */
    void listInsert(int index, Object e);

    /**
     * 删除元素
     *
     * @param index 位置
     * @return
     */
    Object listDelete(int index);

    /**
     * 遍历表
     */
    void listTraverse();

    /**
     * 获取元素前驱
     *
     * @param e 元素
     * @return 前驱
     */
    Object priorElem(Object e);

    /**
     * 获取元素后继
     *
     * @param e 元素
     * @return 后继
     */
    Object nextElem(Object e);

    /**
     * 输出数组
     * @return
     */
    Object[] toArray();


}

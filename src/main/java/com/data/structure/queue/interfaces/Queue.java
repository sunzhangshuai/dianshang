package com.data.structure.queue.interfaces;

/**
 * 单向队列
 *
 * @author sunchen
 */
public interface Queue {
    /**
     * 清空队列
     *
     * @return
     */
    boolean clearQueue();

    /**
     * 判断队列是否为空
     *
     * @return
     */
    boolean queueEmpty();

    /**
     * 获取队列的长度
     *
     * @return
     */
    int queueLength();

    /**
     * 获取队头
     *
     * @return
     */
    Object getHead();

    /**
     * 入队
     *
     * @param e
     * @return
     */
    boolean enQueue(Object e);

    /**
     * 出队
     *
     * @return
     */
    Object deQueue();

    /**
     * 队列遍历
     */
    void queueTraverse();
}

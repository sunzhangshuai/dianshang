package com.data.structure.queue.impl;

import com.data.structure.queue.interfaces.Queue;
import com.data.structure.queue.model.QueueNode;

/**
 * 单链队列
 *
 * @author wenba
 */
public class SingleLinkedQueueImpl implements Queue {

    QueueNode front;
    QueueNode rear;

    public SingleLinkedQueueImpl() {
    }

    @Override
    public boolean clearQueue() {
        while (front != null) {
            deQueue();
        }
        return true;
    }

    @Override
    public boolean queueEmpty() {
        return front == null;
    }

    @Override
    public int queueLength() {
        int length = 0;
        QueueNode queueNode = front;
        while (queueNode != null) {
            length++;
            queueNode = queueNode.next;
        }
        return length;
    }

    @Override
    public Object getHead() {
        return front.data;
    }

    @Override
    public boolean enQueue(Object e) {
        //创建一个结点
        QueueNode queueNode = new QueueNode(e);
        if (front == null) {
            front = rear = queueNode;
        } else {
            rear.next = queueNode;
            rear = queueNode;
        }
        return true;
    }

    @Override
    public Object deQueue() {
        if (front == null) {
            throw new RuntimeException("队列已经空了");
        }
        // 队列中只有一个元素
        QueueNode oldNode = front;
        if (front == rear) {
            front = rear = null;
        } else {
            front = front.next;
        }
        return oldNode.data;
    }

    @Override
    public void queueTraverse() {
        QueueNode queueNode = front;
        while (queueNode != null) {
            visit(queueNode.data);
            queueNode = queueNode.next;
        }
    }

    public void visit(Object e) {
        System.out.println(e);
    }
}

package com.data.structure.queue.impl;

import com.data.structure.queue.interfaces.DoubleQueue;
import com.data.structure.queue.model.QueueNode;

/**
 * 双向链队
 *
 * @author wenba
 */
public class DoubleLinkedQueueImpl implements DoubleQueue {

    public QueueNode rear;
    public QueueNode front;

    public DoubleLinkedQueueImpl() {
    }

    @Override
    public boolean clearQueue() {
        while (front != null) {
            deQueue(0);
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
    public Object getHead(int i) {
        if (front == rear) {
            throw new RuntimeException("空表，没有表头");
        }
        return i == 0 ? front.data : rear.data;
    }

    @Override
    public boolean enQueue(Object e, int i) {
        //先判断是否是空表，如果是空表：
        QueueNode queueNode = new QueueNode(e);
        if (front == null) {
            front = rear = queueNode;
            return true;
        }
        //尾进
        if (i == 0) {
            queueNode.prev = rear;
            rear.next = queueNode;
            rear = queueNode;
        } else {
            //头进
            queueNode.next = front.next;
            front.next.prev = queueNode;
            front = queueNode;
        }
        return true;
    }

    @Override
    public Object deQueue(int i) {
        //如果是空表
        if (front == null) {
            throw new RuntimeException("队列已经空了");
        }
        QueueNode oldNode = front;
        if (front == rear) {
            rear = front = null;
            return oldNode.data;
        }
        //i=0 头出
        if (i == 0) {
            front.next.prev = null;
            front = front.next;
        } else {
            oldNode = rear;
            rear.prev.next = null;
            rear = rear.prev;
        }
        return oldNode.data;
    }

    @Override
    public void queueTraverse(int i) {
        if (i == 0) {
            QueueNode queueNode = front;
            while (queueNode != null) {
                visit(queueNode);
                queueNode = queueNode.next;
            }
        } else {
            QueueNode queueNode = rear;
            while (queueNode != null) {
                visit(queueNode);
                queueNode = queueNode.prev;
            }
        }
    }

    public void visit(QueueNode queueNode) {

    }
}

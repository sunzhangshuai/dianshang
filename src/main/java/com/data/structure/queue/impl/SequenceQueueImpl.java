package com.data.structure.queue.impl;

import com.data.structure.queue.interfaces.Queue;
import org.springframework.stereotype.Service;

/**
 * 循环队列
 *
 * @author wenba
 */
@Service
public class SequenceQueueImpl implements Queue {

    static int MAXSIZE = 100;
    public Object[] base;
    int front;
    int rear;

    public SequenceQueueImpl() {
        base = new Object[MAXSIZE];
    }

    @Override
    public boolean clearQueue() {
        return false;
    }

    @Override
    public boolean queueEmpty() {
        return front == rear;
    }

    @Override
    public int queueLength() {
        return (rear - front + MAXSIZE) % MAXSIZE;
    }

    @Override
    public Object getHead() {
        return base[rear];
    }

    @Override
    public boolean enQueue(Object e) {
        //判断队列是否满了，如果已经满了，不能入队了
        if ((rear + 1 + MAXSIZE) % MAXSIZE == front) {
            return false;
        }
        base[rear] = e;
        rear = getNextPos(rear);
        return true;
    }

    @Override
    public Object deQueue() {
        if (rear == front) {
            return false;
        }
        Object value = base[front];
        front = getNextPos(front);
        return value;
    }

    @Override
    public void queueTraverse() {
        for (int i = front; i < queueLength(); i++) {
            if (front < MAXSIZE) {
                visit(base[front]);
            } else {
                visit(base[front - MAXSIZE]);
            }
        }
    }

    public int getNextPos(int pos) {
        return pos = (pos + 1) % MAXSIZE;
    }

    public void visit(Object e) {
        System.out.println(e);
    }
}

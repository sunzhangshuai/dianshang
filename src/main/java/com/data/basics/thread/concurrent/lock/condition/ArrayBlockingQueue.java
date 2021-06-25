package com.data.basics.thread.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ArrayBlockingQueue: 多路condition
 *
 * @author sunchen
 * @date 2020/7/3 4:24 下午
 */
public class ArrayBlockingQueue {
    /**
     * 放的下标
     */
    int putIndex;

    /**
     * 取的下标
     */
    int getIndex;

    /**
     * 目前数组中有多少数
     */
    int count;

    /**
     * 存放数据的数组
     */
    int[] array ;

    /**
     * 阻塞队列的长度
     */
    int length;

    int maxLength = 100;

    Lock lock = new ReentrantLock();
    Condition conditionEmpty = lock.newCondition();
    Condition conditionFull = lock.newCondition();

    public ArrayBlockingQueue() {
        array = new int[maxLength];
        length = maxLength;
    }

    public void put(int value) {
        lock.lock();
        try {
            if (count == length) {
                try {
                    conditionFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            array[putIndex++] = value;
            if (putIndex == length) {
                putIndex = 0;
            }
            count++;
            conditionEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public int get() {
        int value;
        lock.lock();
        try {
            if (count == 0) {
                try {
                    conditionEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value = array[getIndex++];
            if (getIndex == length) {
                getIndex = 0;
            }
            count--;
            conditionFull.signal();
        }finally {
            lock.unlock();
        }
        return value;
    }


}

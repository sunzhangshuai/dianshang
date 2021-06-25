package com.data.basics.thread.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadAndWriteTest:
 * 读读不互斥，读写互斥，写写互斥
 *
 * @author sunchen
 * @date 2020/7/3 1:58 下午
 */
public class ReadAndWriteTest {
    static Queue queue = new Queue();
    public static void main(String[] args) {
        for (int i = 1; i <=3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                       queue.get();
                    }
                }
            }).start();
        }
        for (int i = 1; i <=3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        queue.put(new Random().nextInt(10000));
                    }

                }
            }).start();
        }
    }
}

class Queue {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    int data;

    public int get() {
        Lock lock = this.lock.readLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备读了");
            Thread.sleep((long) (Math.random()*1000));
            System.out.println(Thread.currentThread().getName()+"已经读到了" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return data;

    }

    public void put(int data) {
        Lock lock = this.lock.writeLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备写了");
            Thread.sleep((long) (Math.random()*1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName()+"已经写完了" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

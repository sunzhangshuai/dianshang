package com.data.basics.thread.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockTest:
 *
 * @author sunchen
 * @date 2020/7/3 1:42 下午
 */
public class LockTest {
    public static void main(String[] args) {
        final OutPut outPut = new OutPut();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outPut.output("sunchen");
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outPut.output("zhangshuai");
                }
            }
        });
        thread2.start();
    }
}

class OutPut{
    Lock lock = new ReentrantLock();
    public void output (String name) {
        lock.lock();
        try {
            for (char c : name.toCharArray()) {
                System.out.print(c);
            }
            System.out.println("\n");
        }finally {
            lock.unlock();
        }

    }
}

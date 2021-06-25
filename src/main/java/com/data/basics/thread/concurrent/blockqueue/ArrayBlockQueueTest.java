package com.data.basics.thread.concurrent.blockqueue;

import com.data.basics.thread.concurrent.lock.condition.ArrayBlockingQueue;

/**
 * ArrayBlockQueueTest:
 *
 * @author sunchen
 * @date 2020/7/3 11:44 下午
 */
public class ArrayBlockQueueTest {
    public static void main(String[] args) {
        final java.util.concurrent.ArrayBlockingQueue<Integer> queue = new java.util.concurrent.ArrayBlockingQueue<Integer>(3);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println(Thread.currentThread() + "准备放数据");
                            queue.put(1);
                            System.out.println(Thread.currentThread() + "已经放了一个数据，当前一共有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread() + "准备取数据");
                        queue.take();
                        System.out.println(Thread.currentThread() + "已经取了一个数据，当前一共有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }
}

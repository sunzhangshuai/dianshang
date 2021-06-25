package com.data.basics.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * ParseLogTest:
 * 面试题一：
 *
 * @author sunchen
 * @date 2020/7/3 7:21 下午
 */
public class ParseLogTest {

    public static void main(String[] args) {
        final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            parseLog(queue.take());
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 16; i++) {
            try {
                queue.put(i+"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void parseLog(String name){
        System.out.println(System.currentTimeMillis()/1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

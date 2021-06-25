package com.data.basics.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * Test:
 * 面试题二
 *
 * @author sunchen
 * @date 2020/7/3 8:49 下午
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        final Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String ii = null;
                    try {
                        semaphore.acquire();
                        ii = queue.take();
                        String outPut = doSome(ii);
                        System.out.println(Thread.currentThread().getName()+":"+outPut);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            queue.put(i+"");
        }
    }

    public static String doSome(String i) throws InterruptedException {
        Thread.sleep(1000);
        String outPut = i + "-" + (System.currentTimeMillis()/1000);
        return outPut;
    }
}

package com.data.basics.thread.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreTest:
 *
 * @author sunchen
 * @date 2020/7/3 5:27 下午
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "进入" +
                            "当前已经有" + (3 - semaphore.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long) (Math.random()*100000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "准备离开" + "当前已经有" + (3 - semaphore.availablePermits()) + "个并发");
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "已经离开" + "当前已经有" + (3 - semaphore.availablePermits()) + "个并发");
                }
            };
            executorService.execute(runnable);
        }
    }
}

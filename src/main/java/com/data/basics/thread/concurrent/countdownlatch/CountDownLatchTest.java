package com.data.basics.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatchTest:
 *
 * @author sunchen
 * @date 2020/7/3 6:13 下午
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正准备接收到了命令");
                    try {
                        cdOrder.await();
                        System.out.println(Thread.currentThread().getName() + "已经接收到了命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() + "回应命令处理结束");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + "即将发布起跑命令");
            cdOrder.countDown();
            System.out.println(Thread.currentThread().getName() + "已经发布起跑命令");
            cdAnswer.await();
            System.out.println(Thread.currentThread().getName() + "收到了所有响应结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

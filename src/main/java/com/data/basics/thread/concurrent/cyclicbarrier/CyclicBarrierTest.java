package com.data.basics.thread.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrierTest:
 *
 * @author sunchen
 * @date 2020/7/3 5:51 下午
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 1; i <= 3; i++) {
            final int task = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("第" + task + "个人即将到达第一个集合点,当前已经有" +
                                (cyclicBarrier.getNumberWaiting()+1) + "人到达");
                        System.out.println("test"+ cyclicBarrier.getNumberWaiting());
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("第" + task + "个人即将到达第二个集合点,当前已经有" +
                                (cyclicBarrier.getNumberWaiting()+1) + "人到达");
                        System.out.println("test"+ cyclicBarrier.getNumberWaiting());
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("第" + task + "个人即将到达第三个集合点,当前已经有" +
                                (cyclicBarrier.getNumberWaiting()+1) + "人到达");
                        cyclicBarrier.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.execute(runnable);
        }
    }
}

package com.data.basics.thread.exceutors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors:
 *
 * @author sunchen
 * @date 2020/6/17 2:04 下午
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        //创建线程池的便捷方法：一般不要使用这种方式创建线程，因为这种方式，会使用无界的任务队列
        //为避免OOM,我们应该使用ThreadPoolExecutor的构造方法手动指定队列的最大长度
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(512), new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        //任务队列总有占满的时候，这时如果再提交任务，RejectedExecutionHandler为我们提供了控制方式，如下
        // 拒绝策略                 拒绝行为
        // AbortPolicy            抛出RejectedExecutionException
        // DisCardPolicy          什么都不做，直接忽略
        // DisCardOldestPolicy    丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置
        // CallerRunsPolicy       直接由提交任务者执行这个任务
        //1.创建固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //2.创建只有一个线程的线程池
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //3.创建不限线程数上限的线程池，
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //线程池的工作顺序：
        //corePoolSize ----- > 任务队列-------> maximunPoolSize----->拒绝策略

        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("bobming");
            }
        }, 1, 2,TimeUnit.SECONDS);

    }
}

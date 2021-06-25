package com.data.basics.thread.concurrent.exectuor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FutureAndCallTest:
 * 有返回结果
 * @author sunchen
 * @date 2020/7/3 1:10 下午
 */
public class FutureAndCallableTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> submit = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "sunchen bisheng";
            }
        });
        try {
            String result = submit.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /**
         * 同时提交一组任务，哪个先返回就先接收哪个
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return seq;
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> take = completionService.take();
                System.out.println("-----"+take.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

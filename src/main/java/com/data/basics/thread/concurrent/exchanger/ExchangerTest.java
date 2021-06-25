package com.data.basics.thread.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExchangerTest:
 *
 * @author sunchen
 * @date 2020/7/3 6:52 下午
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<String>();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data1 = "sunchen";
                System.out.println("线程"+Thread.currentThread().getName()+
                        "正要把数据"+data1+"换出去");
                try {
                    Thread.sleep((long) (Math.random()*10000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程"+Thread.currentThread().getName()+
                            "换回来的数据："+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String data1 = "zhangshuai";
                System.out.println("线程"+Thread.currentThread().getName()+
                        "正要把数据"+data1+"换出去");
                try {
                    Thread.sleep((long) (Math.random()*10000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程"+Thread.currentThread().getName()+
                            "换回来的数据："+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

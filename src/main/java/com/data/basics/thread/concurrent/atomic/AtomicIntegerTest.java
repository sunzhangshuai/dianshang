package com.data.basics.thread.concurrent.atomic;

import com.data.basics.constructor.Father;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicInteger:
 *
 * @author sunchen
 * @date 2020/7/1 1:59 下午
 */
public class AtomicIntegerTest {
    static AtomicInteger i = new AtomicInteger(0);
    static AtomicIntegerArray array = new AtomicIntegerArray(10);
    static AtomicIntegerFieldUpdater<Father> ff = AtomicIntegerFieldUpdater.newUpdater(Father.class, "b");


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Father father = new Father(3);
                    ff.addAndGet(father, 2);
                    System.out.println(father.b);
                }
            });
    }
}

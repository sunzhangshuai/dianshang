package com.data.basics.thread.concurrent.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * TraditionalCommunication:
 * 用阻塞队列实现通信
 *
 * @author sunchen
 * @date 2020/6/29 1:55 下午
 */
public class BlockQueueCommunication {

    /**
     * 子线程执行3次，主线程执行5次，如此循环10次
     *
     * @param args
     */
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.sub(i);
                }
            }
        }).start();
        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }


    static class Business {
        ArrayBlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        ArrayBlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
        {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void sub(int i) {

            try {
                queue1.put(1);
                for (int j = 1; j <= 3; j++) {
                    System.out.println("sub sequence " + j + "loop of" + i);
                }
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        public  void main(int i) {
            try{
                queue2.put(1);
                for (int j = 1; j <= 5; j++) {
                    System.out.println("main sequence " + j + "loop of" + i);
                }
                queue1.take();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}

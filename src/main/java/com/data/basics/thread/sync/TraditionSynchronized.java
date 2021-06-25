package com.data.basics.thread.sync;

/**
 * TraditionSynchronized:
 *
 * @author sunchen
 * @date 2020/6/22 5:58 下午
 */
public class TraditionSynchronized {
    public static void main(String[] args) {
        final OutPut outPut = new OutPut();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outPut.output("sunchen");
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outPut.output("zhangshuai");
                }
            }
        });
        thread2.start();
    }



    static class OutPut{
        public  void output (String name) {
            for (char c : name.toCharArray()) {
                System.out.print(c);
            }
            System.out.println("\n");
        }
    }
}

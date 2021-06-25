package com.data.basics.thread.threadlocal;

/**
 * MultiDataShare:
 * 实现多线程共享数据-设计4个线程，其中两个线程每次对j增加1，其他两个线程每次对j减少1
 *
 * @author sunchen
 * @date 2020/7/1 10:31 上午
 */
public class MultiDataShare {
    public static void main(String[] args) {
        final Business business = new Business();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        business.sub();
                    }

                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        business.add();
                    }

                }
            }).start();
        }
    }

    static class Business {
        private int j;

        public synchronized void sub() {

            j--;
            System.out.println(Thread.currentThread().getName() + "对j减1:" + j);
        }

        public synchronized void add() {

            j++;
            System.out.println(Thread.currentThread().getName() + "对j加1:" + j);

        }

    }
}

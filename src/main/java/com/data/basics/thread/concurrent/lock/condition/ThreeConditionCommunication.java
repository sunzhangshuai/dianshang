package com.data.basics.thread.concurrent.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionCommunication:
 *
 * @author sunchen
 * @date 2020/7/3 3:37 下午
 */
public class ThreeConditionCommunication {
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    business.three(i);
                }
            }
        }).start();
        for (int i = 1; i <= 10; i++) {
            business.main(i);
        }
    }


    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        int should = 1;
        public void three(int i) {
            lock.lock();
            try {
                while (should != 3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 3; j++) {
                    System.out.println("three sequence " + j + "loop of" + i);
                }
                should = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }

        public void sub(int i) {
            lock.lock();
            try {
                while (should != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 2; j++) {
                    System.out.println("sub sequence " + j + "loop of" + i);
                }
                should = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }


        }

        public  void main(int i) {
            lock.lock();
            try {
                while (should != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 5; j++) {
                    System.out.println("main sequence " + j + "loop of" + i);
                }
                should = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }

        }

    }
}

package com.data.basics.object;

import java.lang.ref.WeakReference;

/**
 * WeakReferenceTest:
 *
 * @author sunchen
 * @date 2020/7/7 2:31 下午
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<Student> m = new WeakReference<Student>(new Student());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}

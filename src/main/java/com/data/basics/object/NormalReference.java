package com.data.basics.object;

import java.io.IOException;

/**
 * NormalReference:
 *
 * @author sunchen
 * @date 2020/7/7 2:05 下午
 */
public class NormalReference {
    public static void main(String[] args) throws IOException {
        Student student = new Student();
        student = null;
        System.gc();
        System.in.read();
    }
}

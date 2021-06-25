package com.data.basics.object;

import java.io.Serializable;

/**
 * @author sunchen
 */
public class Student implements Serializable {
    public String name;
    /**
     * 学习object的toString方法
     * @return
     */
    @Override
    public String toString() {
        System.out.println("just");
        return "test";
    }

    public static void main(String[] args) {
        Student student = new Student();
        Student s2 = student;
        Student s3 = new Student();
        System.out.println(student.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}

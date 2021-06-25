package com.data.basics.constructor;

import java.util.Objects;

/**
 * @author sunchen
 */
public class Son extends Father implements Comparable {
    private int a;
    private String name;
    private int age;
    public Son(int a){
        super(a);
        this.a = a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Son son = (Son) o;
        return age == son.age &&
                name.equals(son.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Object o) {
        Son son = (Son) o;
        return this.a-son.a;
    }
}

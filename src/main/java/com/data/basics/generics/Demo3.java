package com.data.basics.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo3:
 * 泛型接口用法一
 * @author sunchen
 * @date 2020/6/8 11:11 上午
 */
public interface Demo3<T> {
    /**
     * 展示
     * @param name
     */
    void disPlay(T name);
}

class Demo4 implements Demo3<String>{

    @Override
    public void disPlay(String name) {
        List<Object> l1 = new ArrayList<Object>();
        List<String> l3 = new ArrayList<String>();
        //l1 = l3;
        List<?> l2 = new ArrayList<String>();
        l2 = l3;
    }
}

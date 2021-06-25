package com.data.basics.generics;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Demo1:
 * 泛型类
 * @author sunchen
 * @date 2020/6/8 11:04 上午
 */
public class Demo1<T> {
    T name;
    public void setName(T name){
        this.name = name;
    }
    public T getName(){
        return name;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Demo1<String> demo1 = new Demo1<String>();
        demo1.setName("sunchen");
        //demo1.setName(124);
        Class<? extends Demo1> aClass = demo1.getClass();
        Method method = aClass.getMethod("setName", Object.class);
        method.invoke(demo1, 123);
    }
}

//package com.data.basics.object;
//
//import com.data.basics.inner.Person;
//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
///**
// * Teacher:
// *
// * @author sunchen
// * @date 2020/6/11 11:13 上午
// */
//public class Teacher implements Serializable {
//    public String name;
//    public Student student;
//
//    @Override
//    public Person clone()  {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            ObjectOutputStream oo = new ObjectOutputStream(bos);
//            oo.writeObject(this);
//            ByteInputStream bis = new ByteInputStream();
//            ObjectInputStream oi = new ObjectInputStream(bis);
//            Person o = (Person) oi.readObject();
//            return o;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}

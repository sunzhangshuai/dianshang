//package com.data.jvm;
//
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.Opcodes;
//
///**
// * MethodAreaTest:
// *
// * @author sunchen
// * @date 2020/7/10 8:52 下午
// */
//public class MethodAreaTest extends ClassLoader {
//    public static void main(String[] args) {
//        int j = 0;
//        try {
//            MethodAreaTest methodAreaTest = new MethodAreaTest();
//            for (int i = 0; i < 10000; i++,j++) {
//                ClassWriter cw = new ClassWriter(0);
//                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
//                byte[] bytes = cw.toByteArray();
//                methodAreaTest.defineClass("Class" + i, bytes, 0, bytes.length);
//            }
//        } catch (Exception e) {
//            System.out.println(j);
//        } finally {
//            System.out.println(j+"---------");
//        }
//    }
//}

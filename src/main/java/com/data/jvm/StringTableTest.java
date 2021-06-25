package com.data.jvm;

/**
 * StringTableTest:
 *
 * @author sunchen
 * @date 2020/7/11 1:01 下午
 */
public class StringTableTest {
    public static void main(String[] args) {
        int i = 0;
        long start = System.currentTimeMillis();
        try {
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
            System.out.println("--------------"+(System.currentTimeMillis() - start)/1000);
        }

    }
}

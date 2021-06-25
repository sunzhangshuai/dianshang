package com.data.basics.object;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SoftReference:
 *
 * @author sunchen
 * @date 2020/7/7 2:09 下午
 */
public class SoftReferenceTest {
    public static void main(String[] args) throws IOException {
        soft();
    }

    public static void soft() throws IOException {
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        List<SoftReference<byte[]>> list = new ArrayList<SoftReference<byte[]>>();
        ReferenceQueue<byte[]> queue = new ReferenceQueue<byte[]>();
        for (int i = 0; i < 5; i++) {
            byte[] bytes = new byte[1024*1024*4];
            SoftReference<byte[]> softReference = new SoftReference<byte[]>(bytes,queue);
            list.add(softReference);
            System.out.println(softReference.get());
        }
        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null) {
            list.remove(poll);
            poll = queue.poll();
        }
        System.in.read();
    }
}
